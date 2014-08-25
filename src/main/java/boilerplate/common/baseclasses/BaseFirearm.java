/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.common.baseclasses;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * @author warlordjones
 * 
 */
public abstract class BaseFirearm extends RootItem
{
	protected int damage;
	protected short reloadTime;
	protected boolean twoAmmo;

	protected Item ammo;
	protected Item ammo2;

	protected String fireSound;
	protected String reloadSound;

	public BaseFirearm(int damage, int reloadTime, Item ammo, Item ammo2, String fireSound, String reloadSound)
	{
		super();
		this.damage = damage;
		this.reloadTime = (short) reloadTime;
		this.twoAmmo = ammo2 != null;

		this.ammo = ammo;
		this.ammo2 = ammo2;

		this.fireSound = fireSound;
		this.reloadSound = reloadSound;
		this.setMaxStackSize(1);
		this.setFull3D();
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	{
		if((entity instanceof EntityPlayer) && (((EntityPlayer) entity).getCurrentEquippedItem() == stack))
		{
			EntityPlayer player = (EntityPlayer) entity;

			if(!stack.hasTagCompound())
				stack.setTagCompound(new NBTTagCompound());

			NBTTagCompound tag = stack.getTagCompound();

			if(tag.getShort("reloadTime") > 0)
			{
				tag.setShort("reloadTime", (short) (tag.getShort("reloadTime") - 1));
				stack.setTagCompound(tag);

				if(tag.getShort("reloadTime") == 10)
					world.playSoundAtEntity(player, this.reloadSound, 0.8F, 1.0F);

			}
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		NBTTagCompound tag = stack.getTagCompound();

		if((tag.getShort("reloadTime") == 0) && player.inventory.hasItem(Items.gunpowder) && player.inventory.hasItem(this.ammo))
			if(this.twoAmmo)
			{
				if(player.inventory.hasItem(this.ammo2))
					this.shotBullet(stack, world, player);
				player.inventory.consumeInventoryItem(this.ammo2);
			}
			else
				this.shotBullet(stack, world, player);
		return stack;
	}

	protected abstract void shotBullet(ItemStack stack, World world, EntityPlayer player);
}
