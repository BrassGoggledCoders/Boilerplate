package xyz.brassgoggledcoders.boilerplate.modules;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.config.ConfigEntry;
import xyz.brassgoggledcoders.boilerplate.config.Type;
import xyz.brassgoggledcoders.boilerplate.registries.IRegistryHolder;

import java.util.HashMap;

/**
 * @author SkySom
 */
public class ModuleHandler
{
	private HashMap<String, Module> modules = new HashMap<String, Module>();
	private IRegistryHolder registryHolder;

	public ModuleHandler(IBoilerplateMod mod)
	{
		this.registryHolder = mod.getRegistryHolder();
	}

	public void preInit(FMLPreInitializationEvent event)
	{
		for (Module module : getModules().values())
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

		for (Module module : getModules().values())
		{
			if (module.getIsActive())
			{
				module.preInit(event);
			}
		}
	}

	public void init(FMLInitializationEvent event)
	{
		for (Module module : getModules().values())
		{
			if (module.getIsActive())
			{
				module.init(event);
			}
		}
	}

	public void postInit(FMLPostInitializationEvent event)
	{
		for (Module module : getModules().values())
		{
			if (module.getIsActive())
			{
				module.postInit(event);
			}
		}
	}

	public void clientInit(FMLInitializationEvent event)
	{
		for (Module module : getModules().values())
		{
			if (module.getIsActive())
			{
				module.clientInit(event);
			}
		}
	}

	public void configureModules()
	{
		for (Module module : getModules().values())
		{
			this.registryHolder.getConfigRegistry().addEntry(module.getName(),
					new ConfigEntry("Module", module.getName() + " Enabled", Type.BOOLEAN, "true"));
			module.setIsActive(this.registryHolder.getConfigRegistry().getBoolean(module.getName(), true));
		}
	}

	public HashMap<String, Module> getModules()
	{
		return modules;
	}

	public void addModule(Module module)
	{
		modules.put(module.getName(), module);
	}

	public Module getModule(String name)
	{
		return modules.get(name);
	}

	public boolean isModuleEnabled(String name)
	{
		return getModule(name) != null && getModule(name).getIsActive();
	}

}
