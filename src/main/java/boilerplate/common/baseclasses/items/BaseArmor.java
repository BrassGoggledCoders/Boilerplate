
package boilerplate.common.baseclasses.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.client.ClientHelper;
import boilerplate.common.utils.StringUtils;

/**
 * @author Surseance
 *
 */
public class BaseArmor extends ItemArmor
{
	String textureName;
	String prefix;

	public BaseArmor(ArmorMaterial mat, int type, String textureName, String prefix)
	{
		super(mat, 0, type);
		this.setMaxStackSize(1);
		this.textureName = textureName;
		this.prefix = prefix;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String stuff)
	{
		return slot == 2 ? this.prefix + "textures/models/armor/" + this.textureName + "_2.png"
				: this.prefix + "textures/models/armor/" + this.textureName + "_1.png";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(this.prefix + "armor/" + this.getUnlocalizedName().substring(5));
	}

	@SuppressWarnings("all")
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack parO1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4)
	{
		if (!StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc").contains("item."))
		{
			if (ClientHelper.isShiftKeyDown())
			{
				this.getWrappedDesc(list);
			}
			else
			{
				list.add(ClientHelper.shiftForInfo);
			}
		}
	}

	@SuppressWarnings("all")
	public void getWrappedDesc(List list)
	{
		String[] wrappedDesc = StringUtils.wrap(StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc"), 30);
		for (String element : wrappedDesc)
		{
			list.add(element.trim());
		}
	}
}
