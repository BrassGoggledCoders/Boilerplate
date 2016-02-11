/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package xyz.brassgoggledcoders.boilerplate.common.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import xyz.brassgoggledcoders.boilerplate.client.ClientHelper;
import xyz.brassgoggledcoders.boilerplate.common.utils.StringUtils;

/**
 * @author warlordjones
 *
 */
public class RootItem extends Item
{
	/*
	 * 0 = only on shift. 1 = no shift. 2 = no shift + shift
	 */
	int descMode = 0;

	public Item setDescMode(int mode)
	{
		this.descMode = mode;
		return this;
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if (this.descMode == 0)
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
		else if (this.descMode == 1)
		{
			if (stack.getItemDamage() > 0)
			{
				if (!StatCollector.translateToLocal(this.getUnlocalizedName() + "." + stack.getItemDamage() + ".desc").contains("item."))
				{
					this.getWrappedDesc(list, stack);
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
					this.getWrappedDesc(list, stack);
				}
			}
		}
		else if (this.descMode == 2)
		{
			if (stack.getItemDamage() > 0)
			{
				if (!StatCollector.translateToLocal(this.getUnlocalizedName() + "." + stack.getItemDamage() + ".desc").contains("item."))
				{
					this.getWrappedDesc(list, stack);
				}
			}
			else
			{
				if (!StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc").contains("item."))
				{
					this.getWrappedDesc(list, stack);
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
