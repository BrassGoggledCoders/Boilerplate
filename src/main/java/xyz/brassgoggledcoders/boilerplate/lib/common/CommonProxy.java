package xyz.brassgoggledcoders.boilerplate.lib.common;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.lib.common.modcompat.CompatibilityHandler;

import javax.annotation.Resource;

public class CommonProxy
{
	public void initCompatibilityHandler(CompatibilityHandler compatibilityHandler, FMLInitializationEvent event)
	{
		compatibilityHandler.init(event);
	}

	public String translate(String text)
	{
		return "";
	}

	public void loadItemModel(Item item, int metadata, String override)
	{
	}

	public void loadItemModel(Item item, int metadata, ResourceLocation location)
	{
	}

	public void addVariantName(Item item, String... variantNames)
	{

	}

	public void registerItemModelVariant(Item item, int metadata, String itemModelName)
	{

	}

	public void registerEvents()
	{
	}
}
