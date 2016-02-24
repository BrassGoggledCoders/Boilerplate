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
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ModLogger;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.Utils;

public class BoilerplateLib
{
	public static String VERSION = "@VERSION@";
	private static BoilerplateLib instance = null;

	public boolean colorblind;
	public ModLogger logger;
	public IBoilerplateMod mod;
	public GuiHandler guiHandler;
	public PacketHandler packetHandler;
	public CompatibilityHandler compatibilityHandler;
	public CommonProxy proxy;

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
		this.compatibilityHandler = new CompatibilityHandler(logger);
		this.packetHandler = new PacketHandler(mod.getID());
	}

	public Configuration config(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		colorblind = config.get("general", "colorblindSupport", false, "True to enable").getBoolean();
		compatibilityHandler.configureModCompat(config);
		config.save();
		return config;
	}

	public void preInit(FMLPreInitializationEvent event)
	{
		String packageString = this.getClass().getPackage().toString().replace("package", "").trim();
		proxy = Utils.createProxy(packageString + ".client.ClientProxy", packageString + ".common.CommonProxy");
		compatibilityHandler.preInit(event);
	}

	public void init(FMLInitializationEvent event)
	{
		if (colorblind && FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
			MinecraftForge.EVENT_BUS.register(new ClientEventsHandler());
		}
		proxy.initCompatibilityHandler(compatibilityHandler, event);
	}

	public void postInit(FMLPostInitializationEvent event)
	{
		compatibilityHandler.postInit(event);
	}
}
