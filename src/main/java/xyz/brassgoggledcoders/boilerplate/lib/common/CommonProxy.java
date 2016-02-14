package xyz.brassgoggledcoders.boilerplate.lib.common;

import net.minecraft.util.StatCollector;

public class CommonProxy
{
	public void registerRenderHandlers()
	{
	}

	public String translate(String text)
	{
		return StatCollector.translateToLocal("boilerplate." + text);
	}
}
