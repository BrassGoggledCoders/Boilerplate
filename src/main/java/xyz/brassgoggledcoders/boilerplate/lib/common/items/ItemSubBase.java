package xyz.brassgoggledcoders.boilerplate.lib.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.ItemRegistry;

import java.util.List;

public class ItemSubBase extends ItemBase
{
	public List<String> metaNames;

	public ItemSubBase(String name, List<String> metaNames)
	{
		this("", name, metaNames);
	}

	public ItemSubBase(String texturePath, String name, List<String> metaNames)
	{
		super(texturePath, name);
		this.metaNames = metaNames;
		this.setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		return getUnlocalizedName() + "." + getMetaName(itemstack.getItemDamage());
	}

	public String getMetaName(int meta)
	{
		return metaNames.get(meta);
	}

	public int getMeta(String name)
	{
		if(metaNames.contains(name))
		{
			return metaNames.indexOf(name);
		}
		return 0;
	}

	public int getNumberOfSubItems()
	{
		return metaNames.size();
	}

	public ItemStack getStackByName(String metaName, int count)
	{
		return new ItemStack(ItemRegistry.getItem(this.getUnlocalizedName()), count, getMeta(metaName));
	}

	public ItemStack getStackByName(String name)
	{
		return getStackByName(name, 1);
	}

	@Override
	public String[] getResourceLocations()
	{
		int numberOfSubItems = getNumberOfSubItems();
		String[] locations = new String[getNumberOfSubItems()];
		for(int i = 0; i < numberOfSubItems; i++)
		{
			locations[i] = texturePath + name + "_" + getMetaName(i);
		}
		return locations;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list)
	{
		for (int i = 0; i < getNumberOfSubItems(); i++)
		{
			ItemStack stack = new ItemStack(item, 1, i);
			list.add(stack);
		}
	}
}
