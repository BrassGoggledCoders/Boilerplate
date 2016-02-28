package xyz.brassgoggledcoders.boilerplate.lib;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import xyz.brassgoggledcoders.boilerplate.lib.client.events.ClientEventsHandler;
import xyz.brassgoggledcoders.boilerplate.lib.client.guis.GuiHandler;
import xyz.brassgoggledcoders.boilerplate.lib.common.CommonProxy;
import xyz.brassgoggledcoders.boilerplate.lib.common.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.lib.common.modcompat.CompatibilityHandler;
import xyz.brassgoggledcoders.boilerplate.lib.common.network.PacketHandler;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.ItemRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.LoadingStage;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ModLogger;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.Utils;

public class BoilerplateLib
{
	public static String VERSION = "@VERSION@";
	private static BoilerplateLib instance = null;

	public boolean colorblind;
	private ModLogger logger;
	private IBoilerplateMod mod;
	private GuiHandler guiHandler;
	private PacketHandler packetHandler;
	private CompatibilityHandler compatibilityHandler;
	private CommonProxy proxy;
	private Configuration config;

	public static BoilerplateLib getInstance()
	{
		if(instance == null)
		{
			IBoilerplateMod modInstance = Utils.getCurrentMod();
			if(modInstance != null)
			{
				instance = new BoilerplateLib(modInstance);
			}
		}
		return instance;
	}

	protected BoilerplateLib()
	{

	}

	protected BoilerplateLib(IBoilerplateMod mod)
	{
		this.mod = mod;
		this.logger = mod.getLogger();
		this.guiHandler = new GuiHandler(mod);
		this.compatibilityHandler = new CompatibilityHandler();
		this.packetHandler = new PacketHandler(mod.getID());
	}

	public void preInitStart(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		colorblind = config.get("general", "colorblindSupport", false, "True to enable").getBoolean();
		getCompatibilityHandler().configureModCompat(config);
		config.save();

		getLogger().info(getMod().getName() + " has BoilerplateLib Version " + VERSION + " installed");
		String packageString = this.getClass().getPackage().toString().replace("package", "").trim();
		String clientProxy = packageString + ".client.ClientProxy";
		String serverProxy = packageString + ".common.CommonProxy";

		proxy = Utils.createProxy(clientProxy, serverProxy);
		getCompatibilityHandler().preInit(event);
	}

	public void preInitEnd(FMLPreInitializationEvent event)
	{
		config.save();
		getProxy().registerEvents();
		ItemRegistry.getInstance().initiateItems();
		ItemRegistry.getInstance().initiateModels();
	}

	public void init(FMLInitializationEvent event)
	{
		ItemRegistry.getInstance().setLoadingStage(LoadingStage.INIT);
		getProxy().initCompatibilityHandler(getCompatibilityHandler(), event);
		ItemRegistry.getInstance().initiateRecipes();
	}

	public void postInit(FMLPostInitializationEvent event)
	{
		ItemRegistry.getInstance().setLoadingStage(LoadingStage.POSTINIT);
		getCompatibilityHandler().postInit(event);
	}

	public static ModLogger getLogger()
	{
		return getInstance().logger;
	}

	public static IBoilerplateMod getMod()
	{
		return getInstance().mod;
	}

	public static GuiHandler getGuiHandler()
	{
		return getInstance().guiHandler;
	}

	public static PacketHandler getPacketHandler()
	{
		return getInstance().packetHandler;
	}

	public static CompatibilityHandler getCompatibilityHandler()
	{
		return getInstance().compatibilityHandler;
	}

	public static CommonProxy getProxy()
	{
		return getInstance().proxy;
	}

	public static Configuration getConfig()
	{
		return getInstance().config;
	}
}
