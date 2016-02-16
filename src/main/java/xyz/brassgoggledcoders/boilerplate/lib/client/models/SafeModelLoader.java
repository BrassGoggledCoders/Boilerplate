package xyz.brassgoggledcoders.boilerplate.lib.client.models;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;

public class SafeModelLoader
{
	public static void loadBlockModel(Block block)
	{
		loadBlockModel(block, 0);
	}

	public static void loadBlockModel(Block block, int metadata)
	{
		loadBlockModel(block, metadata, block.getUnlocalizedName().substring(5));
	}

	public static void loadBlockModel(Block block, int metadata, String override)
	{
		loadItemModel(Item.getItemFromBlock(block), metadata, override);
	}

	public static void loadItemModel(Item item)
	{
		loadItemModel(item, 0);
	}

	public static void loadItemModel(Item item, int metadata)
	{
		loadItemModel(item, metadata, item.getUnlocalizedName().substring(5));
	}

	public static void loadItemModel(Item item, int metadata, String override)
	{
		BoilerplateLib.proxy.loadItemModel(item, metadata, override);
	}
}
