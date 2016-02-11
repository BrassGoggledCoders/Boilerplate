package xyz.brassgoggledcoders.boilerplate.common;

import net.minecraft.creativetab.CreativeTabs;

import net.minecraftforge.classloading.FMLForgePlugin;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;

import xyz.brassgoggledcoders.boilerplate.client.events.ClientEventsHandler;
import xyz.brassgoggledcoders.boilerplate.common.items.ItemDebuggerStick;
import xyz.brassgoggledcoders.boilerplate.common.utils.ModLogger;
import xyz.brassgoggledcoders.boilerplate.common.utils.helpers.RegistryHelper;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = "after:BuildCraft|Core; after:TConstruct; after:ForgeMultipart;after:MineFactoryReloaded")
public class Boilerplate implements IBoilerplateMod
{
	/**
	 * warlordjones - c2e83bd4-e8df-40d6-a639-58ba8b05401e
	 *
	 * decebaldecebal - 5eed1615-0ec9-4f4b-a4c9-58454ad5b04f
	 *
	 * Sky_Som - 27672103-b8c7-400d-8817-49de433336dd
	 *
	 * Snurly - ???
	 */

	public static String[] donors = {};
	public static String[] devs = { "c2e83bd4-e8df-40d6-a639-58ba8b05401e", "5eed1615-0ec9-4f4b-a4c9-58454ad5b04f",
			"27672103-b8c7-400d-8817-49de433336dd" };

	public static int trailParticles;
	public static boolean debuggerStick;
	public static boolean colorblind;

	public static ItemDebuggerStick ITEM_DEBUG_STICK;

	@SidedProxy(clientSide = "xyz.brassgoggledcoders.boilerplate.client.ClientProxy", serverSide = "xyz.brassgoggledcoders.boilerplate.common.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Instance("xyz/brassgoggledcoders/boilerplate")
	public static Boilerplate instance;

	public static ModLogger logger;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = new ModLogger(ModInfo.ID);
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		colorblind = config.get("general", "colorblindSupport", false, "True to enable").getBoolean();
		debuggerStick = config.get("debugging", "activateDebuggingStickOfDoom", false, "True to enable").getBoolean();

		if (debuggerStick || !FMLForgePlugin.RUNTIME_DEOBF)
		{
			ITEM_DEBUG_STICK = new ItemDebuggerStick();
			RegistryHelper.registerItem(ITEM_DEBUG_STICK);
			logger.info("The Debugging Stick of Doom is active!");
		}

		config.save();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
			MinecraftForge.EVENT_BUS.register(new ClientEventsHandler());
		}

		proxy.registerRenderHandlers();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		logger.info("GNU Terry Prachett");
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
	}

	@Override
	public Object getInstance()
	{
		return instance;
	}

	@Override
	public CreativeTabs getCreativeTab()
	{
		return CreativeTabs.tabMisc;
	}

	@Override
	public String getID()
	{
		return ModInfo.ID;
	}

	@Override
	public String getName()
	{
		return ModInfo.NAME;
	}

	@Override
	public String getVersion()
	{
		return ModInfo.VERSION;
	}

	@Override
	public String getPrefix()
	{
		return ModInfo.NAME + ":";
	}

	@Override
	public String getClientProxyPath()
	{
		// TODO
		return "boilerplate.client.ClientProxy";
	}

	@Override
	public String getCommonProxyPath()
	{
		return "boilerplate.common.CommonProxy";
	}
}
