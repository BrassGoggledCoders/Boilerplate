/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package xyz.brassgoggledcoders.boilerplate.common.modcompat;

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

	public boolean areRequirementsMet() {
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
