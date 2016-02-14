package xyz.brassgoggledcoders.boilerplate.lib.common.modcompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import xyz.brassgoggledcoders.boilerplate.mod.Boilerplate;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ILogger;

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
		for (Map.Entry<String, ModCompat> entry : modCompatEnabled.entrySet())
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
		for (ModCompat modCompat : getModCompatEnabled().values())
		{
			if (!modCompat.areRequirementsMet() && modCompat.getIsActive())
			{
				modCompat.setIsActive(false);
				logger.error("Requirements are not met for " + modCompat.getName() + ". Deactivating");
			}
			if (modCompat.getIsActive())
			{
				logger.info("Loading " + modCompat.getName() + " module");
			}
		}

		for (ModCompat modCompat : getModCompatEnabled().values())
		{
			if (modCompat.getIsActive())
			{
				modCompat.preInit(event);
			}
		}
	}

	public void init(FMLInitializationEvent event)
	{
		for (ModCompat modCompat : getModCompatEnabled().values())
		{
			if (modCompat.getIsActive())
			{
				modCompat.init(event);
			}
		}
	}

	public void postInit(FMLPostInitializationEvent event)
	{
		for (ModCompat modCompat : getModCompatEnabled().values())
		{
			if (modCompat.getIsActive())
			{
				modCompat.postInit(event);
			}
		}
	}

	public void clientInit(FMLInitializationEvent event)
	{
		for (ModCompat modCompat : getModCompatEnabled().values())
		{
			if (modCompat.getIsActive())
			{
				modCompat.clientInit(event);
			}
		}
	}

	public Configuration configureModCompat(Configuration configuration)
	{
		for (ModCompat modCompat : getModCompatEnabled().values())
		{
			modCompat.setIsActive(configuration.get("ModCompat", modCompat.getName() + " Enabled", true).getBoolean(true));
		}
		return configuration;
	}
}
