package xyz.brassgoggledcoders.boilerplate.modules;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public abstract class Module
{
	private boolean isActive = true;
	private ModuleHandler handler;

	public abstract String getName();

	public boolean areRequirementsMet()
	{
		return true;
	}

	public String dependencies()
	{
		return "";
	}

	public void preInit(FMLPreInitializationEvent fmlPreInitializationEvent)
	{

	}

	public void init(FMLInitializationEvent fmlInitializationEvent)
	{

	}

	public void postInit(FMLPostInitializationEvent fmlPostInitializationEvent)
	{

	}

	public void clientInit(FMLInitializationEvent event)
	{

	}

	public void setIsActive(Boolean isActive)
	{
		this.isActive = isActive;
	}

	public boolean getIsActive()
	{
		return isActive;
	}

	public boolean isOtherModuleActive(String name)
	{
		return handler.isModuleEnabled(name);
	}

	public void setModuleHandler(ModuleHandler handler)
	{
		this.handler = handler;
	}
}
