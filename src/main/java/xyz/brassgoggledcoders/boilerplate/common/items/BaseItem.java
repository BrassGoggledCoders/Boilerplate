
package xyz.brassgoggledcoders.boilerplate.common.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import xyz.brassgoggledcoders.boilerplate.client.ClientHelper;
import xyz.brassgoggledcoders.boilerplate.common.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.common.utils.StringUtils;
import xyz.brassgoggledcoders.boilerplate.common.utils.Utils;

/**
 * @author warlordjones
 *
 */
public class BaseItem extends Item
{
	String texturePath = "";
	IBoilerplateMod mod;

	public BaseItem()
	{
		this("");
	}

	public BaseItem(String texturePath)
	{
		super();
		this.mod = Utils.getCurrentMod();
		this.texturePath = texturePath;
		this.setCreativeTab(mod.getCreativeTab());
	}

	// @SideOnly(Side.CLIENT)
	// @Override
	// public void registerIcons(IIconRegister par1IconRegister)
	// {
	// this.itemIcon = par1IconRegister.registerIcon(mod.getPrefix() +
	// texturePath + this.getUnlocalizedName().substring(5));
	// }

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if (stack.getItemDamage() > 0)
		{
			if (!StatCollector.translateToLocal(this.getUnlocalizedName() + "." + stack.getItemDamage() + ".desc").contains("item."))
			{
				if (ClientHelper.isShiftKeyDown())
				{
					this.getWrappedDesc(list, stack);
				}
				else
				{
					list.add(ClientHelper.shiftForInfo);
				}
			}
		}
		else
		{
			if (!StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc").contains("item."))
			{
				if (ClientHelper.isShiftKeyDown())
				{
					this.getWrappedDesc(list, stack);
				}
				else
				{
					list.add(ClientHelper.shiftForInfo);
				}
			}
		}
	}

	public void getWrappedDesc(List<String> list, ItemStack stack)
	{
		String[] wrappedDesc;
		if (stack.getItemDamage() > 0)
		{
			wrappedDesc = StringUtils.wrap(StatCollector.translateToLocal(this.getUnlocalizedName() + "." + stack.getItemDamage() + ".desc"), 35);
		}
		else
		{
			wrappedDesc = StringUtils.wrap(StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc"), 35);
		}
		for (String element : wrappedDesc)
		{
			list.add(element.trim());
		}
	}

	public void getWrappedDescAlt(List<String> list, ItemStack stack)
	{
		String[] wrappedDesc;
		if (stack.getItemDamage() > 0)
		{
			wrappedDesc = StringUtils.wrap(StatCollector.translateToLocal(this.getUnlocalizedName() + "." + stack.getItemDamage() + ".desc"), 35);
		}
		else
		{
			wrappedDesc = StringUtils.wrap(StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc_alt"), 35);
		}
		for (String element : wrappedDesc)
		{
			list.add(element.trim());
		}
	}
}
