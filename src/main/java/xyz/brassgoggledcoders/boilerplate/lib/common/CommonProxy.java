package xyz.brassgoggledcoders.boilerplate.lib.common;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.lib.common.modcompat.CompatibilityHandler;

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
}
