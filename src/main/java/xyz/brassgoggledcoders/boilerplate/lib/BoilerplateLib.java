package xyz.brassgoggledcoders.boilerplate.lib;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import xyz.brassgoggledcoders.boilerplate.lib.client.events.ClientEventsHandler;
import xyz.brassgoggledcoders.boilerplate.lib.common.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ModLogger;

public class BoilerplateLib
{
	public static String VERSION = "@VERSION@";

	public static boolean COLORBLIND;
	public static ModLogger LOGGER;
	public static IBoilerplateMod MOD;

	public BoilerplateLib(IBoilerplateMod mod)
	{

	}

	public Configuration config(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		COLORBLIND = config.get("general", "colorblindSupport", false, "True to enable").getBoolean();
		config.save();
		return config;
	}

	public void preInit(FMLPreInitializationEvent event)
	{

	}

	public void init(FMLInitializationEvent event)
	{
		if (COLORBLIND && FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
			MinecraftForge.EVENT_BUS.register(new ClientEventsHandler());
		}
	}

	public void postInit(FMLPostInitializationEvent event)
	{

	}
}
