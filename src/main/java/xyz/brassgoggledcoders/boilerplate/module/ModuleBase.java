package xyz.brassgoggledcoders.boilerplate.module;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.registries.BlockRegistry;
import xyz.brassgoggledcoders.boilerplate.registries.ConfigRegistry;
import xyz.brassgoggledcoders.boilerplate.registries.EntityRegistry;
import xyz.brassgoggledcoders.boilerplate.registries.IRegistryHolder;
import xyz.brassgoggledcoders.boilerplate.registries.ItemRegistry;
import xyz.brassgoggledcoders.boilerplate.utils.ModLogger;

public abstract class ModuleBase implements IModule {
	private boolean isActive = true;
	private ModuleHandler moduleHandler;
	private IRegistryHolder registryHolder;
	private IBoilerplateMod mod;

	@Override
	public boolean areDependenciesMet() {
		return true;
	}

	@Override
	public void preInit(FMLPreInitializationEvent fmlPreInitializationEvent) {

	}

	@Override
	public void init(FMLInitializationEvent fmlInitializationEvent) {

	}

	@Override
	public void postInit(FMLPostInitializationEvent fmlPostInitializationEvent) {

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
		this.moduleHandler = mod.getModuleHandler();
		this.registryHolder = mod.getRegistryHolder();
		this.mod = mod;
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
