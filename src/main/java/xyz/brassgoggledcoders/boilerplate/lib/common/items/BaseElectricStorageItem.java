
package xyz.brassgoggledcoders.boilerplate.lib.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.lib.client.utils.GuiColors;

import java.util.List;

/**
 * @author warlordjones
 *
 */
public class BaseElectricStorageItem extends BaseElectricItem
{
	public BaseElectricStorageItem(String texturePath, String name, int maxEnergy, int maxReceive, int maxSend)
	{
		super(texturePath, name, maxEnergy, maxReceive, maxSend);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityplayer, List<String> list, boolean flag)
	{
		super.addInformation(stack, entityplayer, list, flag);
		if (this.maxSend > 0)
		{
			if ((stack.getTagCompound() != null) && stack.getTagCompound().getBoolean("canCharge"))
			{
				list.add(GuiColors.GREEN + "Charging items in inventory...");
				list.add(GuiColors.GREEN + "Sneak + Right Click to turn off.");
			}
			else
				list.add("Sneak + Right Click to charge items in your inventory.");
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (player.isSneaking() && (this.maxSend > 0))
		{
			NBTTagCompound tag = stack.getTagCompound();

			if (tag == null)
				tag = new NBTTagCompound();

			tag.setBoolean("canCharge", !tag.getBoolean("canCharge"));

			stack.setTagCompound(tag);
		}
		return stack;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	{
		if (entity instanceof EntityPlayer)
		{
			NBTTagCompound tag = stack.getTagCompound();

			if ((tag != null) && tag.getBoolean("canCharge"))
			{
				EntityPlayer player = (EntityPlayer) entity;
				if (this.maxSend > 0)
				{
					int energy = Math.min(this.maxSend, this.getEnergyStored(stack));
					int maxEnergy = energy;

					ItemStack[] mainInv = player.inventory.mainInventory;

					for (ItemStack element : mainInv)
						if ((element != null) && (element != stack) && (element.getItem() instanceof IEnergyItem))
						{
							IEnergyItem container = (IEnergyItem) element.getItem();

							energy -= container.receiveEnergy(element, energy, false);

							if (energy == 0)
								break;
						}

					if ((maxEnergy - energy) > 0)
						this.extractEnergy(stack, maxEnergy - energy, false);
				}
			}
		}
	}
}
