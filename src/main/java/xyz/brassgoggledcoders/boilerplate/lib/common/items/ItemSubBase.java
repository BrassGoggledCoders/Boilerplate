package xyz.brassgoggledcoders.boilerplate.lib.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.ItemRegistry;

import java.security.InvalidParameterException;
import java.util.List;

public class ItemSubBase extends ItemBase
{
	List<String> metaNames;

	public ItemSubBase(String name, List<String> metaNames)
	{
		this(name, "", metaNames);
	}

	public ItemSubBase(String name, String textureBase, List<String> metaNames)
	{
		super(name, textureBase);
		this.metaNames = metaNames;
		setMaxDamage(metaNames.size());
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if(meta < 0 || meta >= getMaxMeta())
		{
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + metaNames.get(meta);
	}

	@Override
	public void getSubItems(Item item, CreativeTabs creativeTabs, List<ItemStack> list)
	{
		for(int meta = 0; meta < getMaxMeta(); ++meta)
		{
			list.add(new ItemStack(item, 1, meta));
		}
	}

	public int getMaxMeta()
	{
		return metaNames.size();
	}

	public ItemStack getStackByName(String name, int count)
	{
		if(metaNames.contains(name))
		{
			return new ItemStack(ItemRegistry.getItem(this.getUnlocalizedName()), count, metaNames.indexOf(name));
		}
		throw new InvalidParameterException(name + " could not be found.");
	}

	public ItemStack getStackByName(String name)
	{
		return getStackByName(name, 1);
	}

	@Override
	public String[] getResourceLocations()
	{
		String[] locations = new String[getMaxMeta()];
		for(int i = 0; i < getMaxMeta(); i++)
		{
			locations[i] = texturePath + metaNames.get(i) + "_" + name;
		}
		return locations;
	}
}
