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
package boilerplate.common.modcompat;

import boilerplate.common.Boilerplate;
import boilerplate.common.utils.ILogger;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SkySom
 */
public class CompatibilityHandler
{
	ILogger logger;

	public CompatibilityHandler()
	{
		this(Boilerplate.logger);
	}

	public CompatibilityHandler(ILogger logger)
	{
		this.logger = logger;
	}

	private HashMap<String, ModCompat> modCompatEnabled = new HashMap<String, ModCompat>();

	@Deprecated
	public ArrayList<ModCompat> getModCompat()
	{
		ArrayList<ModCompat> arrayList = new ArrayList<ModCompat>();
		for(Map.Entry<String, ModCompat> entry : modCompatEnabled.entrySet())
		{
			arrayList.add(entry.getValue());
		}
		return arrayList;
	}

	public HashMap<String, ModCompat> getModCompatEnabled()
	{
		return modCompatEnabled;
	}

	public void addModCompat(ModCompat modCompat)
	{
		modCompatEnabled.put(modCompat.getName(), modCompat);
	}

	public void preInit(FMLPreInitializationEvent event)
	{
		for(ModCompat modCompat : getModCompatEnabled().values())
		{
			if(!modCompat.areRequirementsMet() && modCompat.getIsActive())
			{
				modCompat.setIsActive(false);
				logger.error("Requirements are not met for " + modCompat.getName() + ". Deactivating");
			}
			if(modCompat.getIsActive())
			{
				logger.info("Loading " + modCompat.getName() + " module");
			}
		}

		for(ModCompat modCompat : getModCompatEnabled().values())
		{
			if(modCompat.getIsActive())
			{
				modCompat.preInit(event);
			}
		}
	}

	public void init(FMLInitializationEvent event)
	{
		for(ModCompat modCompat : getModCompatEnabled().values())
		{
			if(modCompat.getIsActive())
			{
				modCompat.init(event);
			}
		}
	}

	public void postInit(FMLPostInitializationEvent event)
	{
		for(ModCompat modCompat : getModCompatEnabled().values())
		{
			if(modCompat.getIsActive())
			{
				modCompat.postInit(event);
			}
		}
	}

	public void clientInit(FMLInitializationEvent event)
	{
		for(ModCompat modCompat : getModCompatEnabled().values())
		{
			if(modCompat.getIsActive())
			{
				modCompat.clientInit(event);
			}
		}
	}

	public Configuration configureModCompat(Configuration configuration)
	{
		for(ModCompat modCompat: getModCompatEnabled().values()) {
			modCompat.setIsActive(configuration.get("ModCompat", modCompat.getName() + " Enabled", true).getBoolean(true));
		}
		return configuration;
	}
}
