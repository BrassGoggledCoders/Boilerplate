package xyz.brassgoggledcoders.boilerplate;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.client.guis.GuiHandler;
import xyz.brassgoggledcoders.boilerplate.config.ConfigEntry;
import xyz.brassgoggledcoders.boilerplate.config.Type;
import xyz.brassgoggledcoders.boilerplate.modules.ModuleHandler;
import xyz.brassgoggledcoders.boilerplate.network.PacketHandler;
import xyz.brassgoggledcoders.boilerplate.proxies.CommonProxy;
import xyz.brassgoggledcoders.boilerplate.registries.BaseRegistry;
import xyz.brassgoggledcoders.boilerplate.registries.ConfigRegistry;
import xyz.brassgoggledcoders.boilerplate.utils.ModLogger;
import xyz.brassgoggledcoders.boilerplate.utils.Utils;

public class BoilerplateLib
{
	public static String VERSION = "@VERSION@";
	private static BoilerplateLib instance = null;

	private ModLogger logger;
	private IBoilerplateMod mod;
	private GuiHandler guiHandler;
	private PacketHandler packetHandler;
	private ModuleHandler moduleHandler;
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
		this.logger = new ModLogger(mod.getID());
		this.guiHandler = new GuiHandler(mod);
		this.moduleHandler = new ModuleHandler(mod);
		this.packetHandler = new PacketHandler(mod.getID());
	}

	public void postInit(FMLPostInitializationEvent event)
	{
		getModuleHandler().postInit(event);
		for(BaseRegistry registry: BaseRegistry.getAllRegistries())
		{
			registry.postInit();
		}
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

	public static ModuleHandler getModuleHandler()
	{
		return getInstance().moduleHandler;
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
