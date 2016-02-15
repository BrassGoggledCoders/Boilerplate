package xyz.brassgoggledcoders.boilerplate.mod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.classloading.FMLForgePlugin;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.common.CommonProxy;
import xyz.brassgoggledcoders.boilerplate.lib.common.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.mod.items.ItemDebuggerStick;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ModLogger;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.helpers.RegistryHelper;

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

	public static boolean DEBUGGER_STICK;

	public static ItemDebuggerStick ITEM_DEBUG_STICK;

	@SidedProxy(clientSide = "xyz.brassgoggledcoders.boilerplate.lib.client.ClientProxy", serverSide = "xyz.brassgoggledcoders.boilerplate.lib.common.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Instance("boilerplate")
	public static Boilerplate instance;

	public static ModLogger logger;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = new ModLogger(ModInfo.ID);
		Configuration config = BoilerplateLib.getInstance().config(event);
		BoilerplateLib.getInstance().preInit(event);
		DEBUGGER_STICK = config.get("debugging", "activateDebuggingStickOfDoom", false, "True to enable").getBoolean();

		if (DEBUGGER_STICK || !FMLForgePlugin.RUNTIME_DEOBF)
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
		BoilerplateLib.getInstance().init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		BoilerplateLib.getInstance().postInit(event);
		logger.info("GNU Terry Prachett");
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
	public ModLogger getLogger()
	{
		return logger;
	}

	@Override
	public String getClientProxyPath()
	{
		// TODO
		return "boilerplate.lib.client.ClientProxy";
	}

	@Override
	public String getCommonProxyPath()
	{
		return "boilerplate.lib.common.CommonProxy";
	}
}
