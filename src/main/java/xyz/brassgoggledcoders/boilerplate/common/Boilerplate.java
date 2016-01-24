/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package xyz.brassgoggledcoders.boilerplate.common;

import xyz.brassgoggledcoders.boilerplate.client.events.ClientEventsHandler;
import xyz.brassgoggledcoders.boilerplate.common.items.ItemDebuggerStick;
import xyz.brassgoggledcoders.boilerplate.common.utils.ModLogger;
import xyz.brassgoggledcoders.boilerplate.common.utils.helpers.RegistryHelper;
import net.minecraftforge.classloading.FMLForgePlugin;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * @author Surseance
 *
 */
@Mod(modid = Boilerplate.MODID, name = Boilerplate.NAME, version = Boilerplate.VERSION, dependencies = Boilerplate.DEPENDENCIES)
public class Boilerplate
{
	public final static String MODID = "xyz/brassgoggledcoders/boilerplate";
	public final static String NAME = "Boilerplate";
	public final static String VERSION = "@VERSION@";
	public final static String DEPENDENCIES = "after:BuildCraft|Core; after:TConstruct; after:ForgeMultipart;" +
			"after:MineFactoryReloaded";
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
		logger = new ModLogger(Boilerplate.MODID);
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		// TODO: particles config option on client only
		trailParticles = config.get("general", "numberOfParticlesInDonorTrails", 0, "0 to disable").getInt();
		colorblind = config.get("general", "colorblindSupport", false, "True to enable").getBoolean();
		debuggerStick = config.get("debugging", "activateDebuggingStickOfDoom", false, "True to enable").getBoolean();

		if(debuggerStick || !FMLForgePlugin.RUNTIME_DEOBF)
		{
			ITEM_DEBUG_STICK = new ItemDebuggerStick();
			RegistryHelper.registerItem(ITEM_DEBUG_STICK, MODID);
			logger.info("The Debugging Stick of Doom is active!");
		}

		config.save();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
		MinecraftForge.EVENT_BUS.register(new ClientEventsHandler());
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
}
