package xyz.brassgoggledcoders.boilerplate.common;

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
