package xyz.brassgoggledcoders.boilerplate.module;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.module.dependencies.IDependency;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface IModule {
	String getName();

	List<IDependency> getDependencies(List<IDependency> dependencies);

	void preInit(FMLPreInitializationEvent event);

	void init(FMLInitializationEvent event);

	void postInit(FMLPostInitializationEvent event);

	void setIsActive(Boolean isActive);

	@Nullable
	String getClientProxyPath();

	@Nullable
	String getServerProxyPath();

	@Nullable
	IModuleProxy getModuleProxy();

	void setModuleProxy(@Nonnull IModuleProxy moduleProxy);

	boolean getIsActive();

	boolean getActiveDefault();

	void setMod(@Nonnull IBoilerplateMod mod);

	void setModuleHandler(@Nonnull ModuleHandler moduleHandler);
}
