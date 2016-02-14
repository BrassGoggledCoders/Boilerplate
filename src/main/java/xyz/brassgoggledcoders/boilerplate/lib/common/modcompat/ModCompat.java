package xyz.brassgoggledcoders.boilerplate.lib.common.modcompat;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * @author SkySom
 */
public abstract class ModCompat
{
	boolean isActive = true;

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

	public Boolean getIsActive()
	{
		return isActive;
	}
}
