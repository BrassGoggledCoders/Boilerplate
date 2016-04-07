package xyz.brassgoggledcoders.boilerplate.lib.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.client.ClientHelper;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.IHasModel;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.StringUtils;

import java.util.List;

/**
 * @author warlordjones
 *
 */
public class ItemBase extends Item implements IHasModel
{
	String texturePath = "";
	String name;

	public ItemBase()
	{
		this("");
	}

	public ItemBase(String name)
	{
		this(name, "");
	}

	public ItemBase(String name, String texturePath)
	{
		super();
		this.name = name;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		if(!texturePath.isEmpty() && !texturePath.endsWith("/") && !texturePath.endsWith("\\"))
		{
			this.texturePath = texturePath + "/";
		}
		this.setCreativeTab(BoilerplateLib.getMod().getCreativeTab());
	}

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

	@Override
	public String[] getResourceLocations()
	{
		return new String[] {texturePath + name};
	}
}
