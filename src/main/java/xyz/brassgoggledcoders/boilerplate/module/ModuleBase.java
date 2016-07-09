package xyz.brassgoggledcoders.boilerplate.module;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.module.dependencies.IDependency;
import xyz.brassgoggledcoders.boilerplate.registries.*;
import xyz.brassgoggledcoders.boilerplate.utils.ModLogger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class ModuleBase implements IModule {
	private boolean isActive = true;
	private ModuleHandler moduleHandler;
	private IRegistryHolder registryHolder;
	private IBoilerplateMod mod;
	private IModuleProxy moduleProxy;

	@Override
	public List<IDependency> getDependencies(List<IDependency> dependencies) {
		return dependencies;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		this.config(this.registryHolder.getConfigRegistry());
		this.registerBlocks(this.registryHolder.getConfigRegistry(), this.registryHolder.getBlockRegistry());
		this.registerItems(this.registryHolder.getConfigRegistry(), this.registryHolder.getItemRegistry());
		this.registerEntities(this.registryHolder.getConfigRegistry(), this.registryHolder.getEntityRegistry());
		if(this.moduleProxy != null) {
			this.moduleProxy.preInit(event);
		}
	}

	@Override
	public void init(FMLInitializationEvent event) {
		if(this.moduleProxy != null) {
			this.moduleProxy.init(event);
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		if(this.moduleProxy != null) {
			this.moduleProxy.postInit(event);
		}
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
	@Nullable
	public String getClientProxyPath() {
		return "";
	}

	@Override
	@Nullable
	public String getServerProxyPath() {
		return "";
	}

	@Override
	@Nullable
	public IModuleProxy getModuleProxy() {
		return moduleProxy;
	}

	@Override
	public void setModuleProxy(@Nonnull IModuleProxy moduleProxy) {
		this.moduleProxy = moduleProxy;
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
	public void setMod(@Nonnull IBoilerplateMod mod) {
		this.registryHolder = mod.getRegistryHolder();
		this.mod = mod;
	}

	@Override
	public IBoilerplateMod getMod() {
		return this.mod;
	}

	@Override
	public void setModuleHandler(@Nonnull ModuleHandler moduleHandler) {
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
