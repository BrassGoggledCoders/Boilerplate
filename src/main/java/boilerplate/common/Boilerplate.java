/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.common;

import boilerplate.common.baseclasses.items.ItemDebuggerStick;
import boilerplate.common.utils.ModLogger;
import boilerplate.common.utils.helpers.RegistryHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.classloading.FMLForgePlugin;
import net.minecraftforge.common.config.Configuration;

/**
 * @author Surseance
 *
 */
@Mod(modid = Boilerplate.MODID, name = Boilerplate.NAME, version = Boilerplate.VERSION, dependencies = Boilerplate.DEPENDENCIES)
public class Boilerplate
{
	public final static String MODID = "boilerplate";
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

	public static ItemDebuggerStick ITEM_DEBUG_STICK;

	@SidedProxy(clientSide = "boilerplate.client.ClientProxy", serverSide = "boilerplate.common.CommonProxy")
	public static CommonProxy proxy;

	@Instance("boilerplate")
	public static Boilerplate instance;

	public static ModLogger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = new ModLogger(Boilerplate.MODID);
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		// TODO: particles config option on client only
		trailParticles = config.get("general", "numberOfParticlesInDonorTrails", 0, "0 to disable").getInt();
		debuggerStick = config.get("debugging", "activateDebuggingStickOfDoom", false, "True to enable").getBoolean();

		if(debuggerStick || !FMLForgePlugin.RUNTIME_DEOBF)
		{
			ITEM_DEBUG_STICK = new ItemDebuggerStick();
			RegistryHelper.registerItem(ITEM_DEBUG_STICK, MODID);
			logger.info("The Debugging Stick of Doom is active!");
		}

		config.save();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		FMLCommonHandler.instance().bus().register(new ForgeEventHandler());
		proxy.registerRenderHandlers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		logger.info("GNU Terry Prachett");
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
	}
}
