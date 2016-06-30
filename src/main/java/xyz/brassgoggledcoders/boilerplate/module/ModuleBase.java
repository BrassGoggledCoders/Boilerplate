package xyz.brassgoggledcoders.boilerplate.module;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.module.dependencies.IDependency;
import xyz.brassgoggledcoders.boilerplate.registries.BlockRegistry;
import xyz.brassgoggledcoders.boilerplate.registries.ConfigRegistry;
import xyz.brassgoggledcoders.boilerplate.registries.EntityRegistry;
import xyz.brassgoggledcoders.boilerplate.registries.IRegistryHolder;
import xyz.brassgoggledcoders.boilerplate.registries.ItemRegistry;
import xyz.brassgoggledcoders.boilerplate.utils.ModLogger;

import java.util.List;

public abstract class ModuleBase implements IModule {
	private boolean isActive = true;
	private ModuleHandler moduleHandler;
	private IRegistryHolder registryHolder;
	private IBoilerplateMod mod;

	@Override
	public List<IDependency> getDependencies(List<IDependency> dependencies) {
		return dependencies;
	}

	@Override
	public void preInit(FMLPreInitializationEvent fmlPreInitializationEvent) {
		this.config(this.registryHolder.getConfigRegistry());
		this.registerBlocks(this.registryHolder.getConfigRegistry(), this.registryHolder.getBlockRegistry());
		this.registerItems(this.registryHolder.getConfigRegistry(), this.registryHolder.getItemRegistry());
		this.registerEntities(this.registryHolder.getConfigRegistry(), this.registryHolder.getEntityRegistry());
	}

	@Override
	public void init(FMLInitializationEvent fmlInitializationEvent) {

	}

	@Override
	public void postInit(FMLPostInitializationEvent fmlPostInitializationEvent) {

	}

	public void config(ConfigRegistry configRegistry) {

	}

	public void registerItems(ConfigRegistry configRegistry, ItemRegistry itemRegistry) {

	}

	public void registerBlocks(ConfigRegistry configRegistry, BlockRegistry blockRegistry) {

	}

	public void registerEntities(ConfigRegistry configRegistry, EntityRegistry entityRegistry) {

	}

	@Override
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public boolean getIsActive() {
		return isActive;
	}

	@Override
	public boolean getActiveDefault() {
		return true;
	}

	@Override
	public void setMod(IBoilerplateMod mod) {
		this.registryHolder = mod.getRegistryHolder();
		this.mod = mod;
	}

	@Override
	public void setModuleHandler(ModuleHandler moduleHandler) {
		this.moduleHandler = moduleHandler;
	}

	public ItemRegistry getItemRegistry() {
		return this.registryHolder.getItemRegistry();
	}

	public BlockRegistry getBlockRegistry() {
		return this.registryHolder.getBlockRegistry();
	}

	public EntityRegistry getEntityRegistry() {
		return this.registryHolder.getEntityRegistry();
	}

	public ConfigRegistry getConfigRegistry() {
		return this.registryHolder.getConfigRegistry();
	}

	public ModLogger getLogger() {
		return this.mod.getLogger();
	}

	public boolean isOtherModuleActive(String name) {
		return moduleHandler.isModuleEnabled(name);
	}
}
