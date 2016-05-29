package xyz.brassgoggledcoders.boilerplate.modules;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.config.ConfigEntry;
import xyz.brassgoggledcoders.boilerplate.config.Type;
import xyz.brassgoggledcoders.boilerplate.registries.ConfigRegistry;

import java.util.HashMap;

/**
 * @author SkySom
 */
public class ModuleHandler
{
	private HashMap<String, Module> modulesEnabled = new HashMap<String, Module>();

	public HashMap<String, Module> getModulesEnabled()
	{
		return modulesEnabled;
	}

	public void addModule(Module module)
	{
		modulesEnabled.put(module.getName(), module);
	}

	public void preInit(FMLPreInitializationEvent event)
	{
		for (Module module : getModulesEnabled().values())
		{
			if (!module.areRequirementsMet() && module.getIsActive())
			{
				module.setIsActive(false);
				BoilerplateLib.getLogger().error("Requirements are not met for " + module.getName() + ". Deactivating");
			}
			if (module.getIsActive())
			{
				BoilerplateLib.getLogger().info("Loading " + module.getName() + " module");
			}
		}

		for (Module module : getModulesEnabled().values())
		{
			if (module.getIsActive())
			{
				module.preInit(event);
			}
		}
	}

	public void init(FMLInitializationEvent event)
	{
		for (Module module : getModulesEnabled().values())
		{
			if (module.getIsActive())
			{
				module.init(event);
			}
		}
	}

	public void postInit(FMLPostInitializationEvent event)
	{
		for (Module module : getModulesEnabled().values())
		{
			if (module.getIsActive())
			{
				module.postInit(event);
			}
		}
	}

	public void clientInit(FMLInitializationEvent event)
	{
		for (Module module : getModulesEnabled().values())
		{
			if (module.getIsActive())
			{
				module.clientInit(event);
			}
		}
	}

	public Configuration configureModCompat(Configuration configuration)
	{
		for (Module module : getModulesEnabled().values())
		{
			ConfigRegistry.addEntry(module.getName(),
					new ConfigEntry("Module", module.getName() + " Enabled", Type.BOOLEAN, "true"));
			module.setIsActive(ConfigRegistry.getBoolean(module.getName(), true));
		}
		return configuration;
	}

	public Module getModule(String name)
	{
		return modulesEnabled.get(name);
	}

	public boolean isModuleEnabled(String name)
	{
		return getModule(name) != null && getModule(name).getIsActive();
	}

}
