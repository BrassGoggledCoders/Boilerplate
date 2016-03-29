package xyz.brassgoggledcoders.boilerplate.lib.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.client.ClientHelper;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.StringUtils;

import java.util.List;

/**
 * @author warlordjones
 *
 */
public class BaseItem extends Item
{
	String texturePath = "";

	public BaseItem()
	{
		this("");
	}

	public BaseItem(String name)
	{
		this(name, "");
	}

	public BaseItem(String name, String texturePath)
	{
		super();
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.texturePath = texturePath;
		this.setCreativeTab(BoilerplateLib.getMod().getCreativeTab());
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if (stack.getItemDamage() > 0)
		{
			if (!I18n.translateToLocal(this.getUnlocalizedName() + "." + stack.getItemDamage() + ".desc").contains("item."))
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
			if (!I18n.translateToLocal(this.getUnlocalizedName() + ".desc").contains("item."))
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
			wrappedDesc = StringUtils.wrap(I18n.translateToLocal(this.getUnlocalizedName() + "." + stack.getItemDamage() + ".desc"), 35);
		}
		else
		{
			wrappedDesc = StringUtils.wrap(I18n.translateToLocal(this.getUnlocalizedName() + ".desc"), 35);
		}
		for (String element : wrappedDesc)
		{
			list.add(element.trim());
		}
	}
}
