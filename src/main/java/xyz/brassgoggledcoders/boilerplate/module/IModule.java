package xyz.brassgoggledcoders.boilerplate.module;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;

public interface IModule
{
	String getName();

	boolean areDependenciesMet();

	void preInit(FMLPreInitializationEvent event);

	void init(FMLInitializationEvent event);

	void postInit(FMLPostInitializationEvent event);

	void setIsActive(Boolean isActive);

	boolean getIsActive();

	boolean getActiveDefault();

	void setMod(IBoilerplateMod mod);
}
