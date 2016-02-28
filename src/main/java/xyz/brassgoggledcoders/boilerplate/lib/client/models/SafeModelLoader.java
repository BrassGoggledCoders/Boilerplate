package xyz.brassgoggledcoders.boilerplate.lib.client.models;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
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
		String name = item.getUnlocalizedName();
		if(name.startsWith("item."))
		{
			name = name.substring(5);
		}
		loadItemModel(item, metadata, name);
	}

	public static void loadItemModel(Item item, int metadata, String override)
	{
		loadItemModel(item, metadata, new ResourceLocation(BoilerplateLib.getMod().getPrefix() + override));
	}

	public static void loadItemModel(Item item, int metadata, ResourceLocation resourceLocation)
	{
		BoilerplateLib.getProxy().loadItemModel(item, metadata, resourceLocation);
	}

	public static void addVariantName(Item item, String... names)
	{
		BoilerplateLib.getProxy().addVariantName(item, names);
	}

	public static void registerItemModelVariant(Item item, int metadata, String itemModelName)
	{
		BoilerplateLib.getProxy().registerItemModelVariant(item, metadata, itemModelName);
	}
}
