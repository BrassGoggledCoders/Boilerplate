package xyz.brassgoggledcoders.boilerplate.lib.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.client.ClientHelper;
import xyz.brassgoggledcoders.boilerplate.lib.common.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.StringUtils;

import java.util.List;

/**
 * @author Surseance
 *
 */
public class BaseArmor extends ItemArmor
{
	String textureName;
	IBoilerplateMod mod;

	public BaseArmor(ArmorMaterial mat, int type, String textureName)
	{
		super(mat, 0, type);
		this.mod = BoilerplateLib.getInstance().mod;
		this.setCreativeTab(mod.getCreativeTab());
		this.setMaxStackSize(1);
		this.textureName = textureName;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String stuff)
	{
		return slot == 2 ? mod.getPrefix() + "textures/models/armor/" + this.textureName + "_2.png"
				: mod.getPrefix() + "textures/models/armor/" + this.textureName + "_1.png";
	}

	// @SideOnly(Side.CLIENT)
	// @Override
	// public void registerIcons(IIconRegister par1IconRegister)
	// {
	// this.itemIcon = par1IconRegister.registerIcon(mod.getPrefix() + "armor/"
	// + this.getUnlocalizedName().substring(5));
	// }

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
