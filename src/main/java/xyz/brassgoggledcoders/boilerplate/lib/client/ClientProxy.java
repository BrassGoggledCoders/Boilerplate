package xyz.brassgoggledcoders.boilerplate.lib.client;

import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
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
}
