package xyz.brassgoggledcoders.boilerplate;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.client.guis.GuiHandler;
import xyz.brassgoggledcoders.boilerplate.modules.ModuleHandler;
import xyz.brassgoggledcoders.boilerplate.network.PacketHandler;
import xyz.brassgoggledcoders.boilerplate.registries.BaseRegistry;
import xyz.brassgoggledcoders.boilerplate.utils.ModLogger;

public abstract class BoilerplateModBase implements IBoilerplateMod
{
	private String modid;
	private String name;
	private String version;
	private CreativeTabs creativeTab;
	private ModLogger logger;
	private GuiHandler guiHandler;
	private PacketHandler packetHandler;
	private ModuleHandler moduleHandler;

	public BoilerplateModBase(String modid, String name, String version, CreativeTabs creativeTab)
	{
		this.modid = modid;
		this.name = name;
		this.version = version;
		this.creativeTab = creativeTab;
		this.logger = new ModLogger(modid);
		this.packetHandler = new PacketHandler(modid);
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		this.guiHandler = new GuiHandler(this);
		this.moduleHandler = new ModuleHandler(this);
		this.modPreInit(event);

		this.moduleHandler.configureModules();
		this.moduleHandler.preInit(event);

		this.getProxy().registerEvents();
		for(BaseRegistry registry: this.getRegistryHolder().getAllRegistries())
		{
			registry.preInit();
		}
	}

	public abstract void modPreInit(FMLPreInitializationEvent event);

	@Override
	public CreativeTabs getCreativeTab()
	{
		return this.creativeTab;
	}

	@Override
	public String getID()
	{
		return this.modid;
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public String getVersion()
	{
		return this.version;
	}

	@Override
	public String getPrefix()
	{
		return this.getID() + ":";
	}

	@Override
	public ModLogger getLogger()
	{
		return this.logger;
	}

	@Override
	public GuiHandler getGuiHandler()
	{
		return this.guiHandler;
	}

	@Override
	public PacketHandler getPacketHandler()
	{
		return this.packetHandler;
	}
}
