package xyz.brassgoggledcoders.boilerplate.common.utils.helpers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraftforge.oredict.OreDictionary;

public class OreDictHelper
{
	public static void registerOre(String name, Block ore, int meta)
	{
		OreDictionary.registerOre(name, new ItemStack(ore, 1, meta));
	}

	public static void registerOre(String name, Item ore, int meta)
	{
		OreDictionary.registerOre(name, new ItemStack(ore, 1, meta));
	}

	public static void registerOreWithAlts(Block ore, String... names)
	{
		registerOreWithAlts(ore, 0, names);
	}

	public static void registerOreWithAlts(Block ore, int meta, String... names)
	{
		registerOreWithAlts(new ItemStack(ore, 1, meta), names);
	}

	public static void registerOreWithAlts(Item ore, String... names)
	{
		registerOreWithAlts(ore, 0, names);
	}

	public static void registerOreWithAlts(Item ore, int meta, String... names)
	{
		registerOreWithAlts(new ItemStack(ore, 1, meta), names);
	}

	public static void registerOreWithAlts(ItemStack ore, String... names)
	{
		for (String name : names)
		{
			OreDictionary.registerOre(name, ore);
		}
	}
}
