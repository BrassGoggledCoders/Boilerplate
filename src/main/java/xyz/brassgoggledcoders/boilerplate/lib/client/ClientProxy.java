package xyz.brassgoggledcoders.boilerplate.lib.client;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.common.CommonProxy;
import xyz.brassgoggledcoders.boilerplate.lib.common.modcompat.CompatibilityHandler;

/**
 * @author Surseance
 *
 */
public class ClientProxy extends CommonProxy
{
	@Override
	public void initCompatibilityHandler(CompatibilityHandler compatibilityHandler, FMLInitializationEvent event)
	{
		compatibilityHandler.clientInit(event);
		super.initCompatibilityHandler(compatibilityHandler, event);
	}

	@Override
	public String translate(String text)
	{
		return StatCollector.translateToLocal("boilerplate." + text);
	}

	@Override
	public void loadItemModel(Item item, int metadata, String override)
	{
		ModelLoader.setCustomModelResourceLocation(item, metadata,
				new ModelResourceLocation(BoilerplateLib.getInstance().mod.getPrefix() + override, "inventory"));
	}
}
