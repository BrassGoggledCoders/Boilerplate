package xyz.brassgoggledcoders.boilerplate;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.client.guis.GuiHandler;
import xyz.brassgoggledcoders.boilerplate.module.ModuleHandler;
import xyz.brassgoggledcoders.boilerplate.network.PacketHandler;
import xyz.brassgoggledcoders.boilerplate.proxies.CommonProxy;
import xyz.brassgoggledcoders.boilerplate.registries.BaseRegistry;
import xyz.brassgoggledcoders.boilerplate.registries.IRegistryHolder;
import xyz.brassgoggledcoders.boilerplate.registries.RegistryHolder;
import xyz.brassgoggledcoders.boilerplate.utils.ClassLoading;
import xyz.brassgoggledcoders.boilerplate.utils.ModLogger;

public abstract class BoilerplateModBase implements IBoilerplateMod {
	private String modid;
	private String name;
	private String version;
	protected CreativeTabs creativeTab;
	protected ModLogger logger;
	protected GuiHandler guiHandler;
	protected PacketHandler packetHandler;
	protected ModuleHandler moduleHandler;
	protected IRegistryHolder registryHolder;
	protected CommonProxy boilerplateProxy;

	public boolean allHandlersCreated = false;

	public BoilerplateModBase(String modid, String name, String version, CreativeTabs creativeTab) {
		this.modid = modid;
		this.name = name;
		this.version = version;
		this.creativeTab = creativeTab;
		this.logger = new ModLogger(modid);
		this.packetHandler = new PacketHandler(modid);
		this.boilerplateProxy = ClassLoading.createProxy("xyz.brassgoggledcoders.boilerplate.proxies.ClientProxy",
				"xyz.brassgoggledcoders.boilerplate.proxies.CommonProxy");
	}

	public void preInit(FMLPreInitializationEvent event) {
		this.getBoilerplateProxy().setMod(this);

		this.createHandlers(event);
		this.modPreInit(event);
		this.afterModuleConstruct(event);

		this.getBoilerplateProxy().registerEvents();

		for(BaseRegistry registry : this.getRegistryHolder().getAllRegistries())
			registry.preInit();
	}

	public void createHandlers(FMLPreInitializationEvent event) {
		if(!allHandlersCreated) {
			this.guiHandler = new GuiHandler(this);
			this.registryHolder = new RegistryHolder(this, event.getModConfigurationDirectory());

			this.moduleHandler = new ModuleHandler(this, event.getAsmData());
			this.moduleHandler.configureModules();
			this.moduleHandler.preInit(event);

			allHandlersCreated = true;
		}
	}

	protected void modPreInit(FMLPreInitializationEvent event) {

	}

	protected void afterModuleConstruct(FMLPreInitializationEvent event) {

	}

	public void init(FMLInitializationEvent event) {
		this.modInit(event);
		this.moduleHandler.init(event);
		for(BaseRegistry registry : this.getRegistryHolder().getAllRegistries())
			registry.init();
	}

	protected void modInit(FMLInitializationEvent event) {

	}

	public void postInit(FMLPostInitializationEvent event) {
		this.modPostInit(event);
		moduleHandler.postInit(event);
		for(BaseRegistry registry : this.getRegistryHolder().getAllRegistries())
			registry.postInit();
	}

	protected void modPostInit(FMLPostInitializationEvent event) {

	}

	@Override
	public CreativeTabs getCreativeTab() {
		return this.creativeTab;
	}

	@Override
	public String getID() {
		return this.modid;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getVersion() {
		return this.version;
	}

	@Override
	public String getPrefix() {
		return this.getID() + ":";
	}

	@Override
	public ModLogger getLogger() {
		return this.logger;
	}

	@Override
	public GuiHandler getGuiHandler() {
		return this.guiHandler;
	}

	@Override
	public PacketHandler getPacketHandler() {
		return this.packetHandler;
	}

	@Override
	public IRegistryHolder getRegistryHolder() {
		return registryHolder;
	}

	@Override
	public ModuleHandler getModuleHandler() {
		return this.moduleHandler;
	}

	@Override
	public CommonProxy getBoilerplateProxy() {
		return this.boilerplateProxy;
	}
}
