package xyz.brassgoggledcoders.boilerplate.module;

import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.config.ConfigEntry;
import xyz.brassgoggledcoders.boilerplate.config.Type;
import xyz.brassgoggledcoders.boilerplate.registries.ConfigRegistry;
import xyz.brassgoggledcoders.boilerplate.registries.IRegistryHolder;
import xyz.brassgoggledcoders.boilerplate.utils.ClassLoading;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;

/**
 * @author SkySom
 */
public class ModuleHandler
{
	private HashMap<String, IModule> modules = new HashMap<String, IModule>();
	private IRegistryHolder registryHolder;
	private IBoilerplateMod mod;

	public ModuleHandler(IBoilerplateMod mod, ASMDataTable asmDataTable)
	{
		this.mod = mod;
		this.registryHolder = mod.getRegistryHolder();
		this.modules = this.getModules(asmDataTable);
	}

	public void preInit(FMLPreInitializationEvent event)
	{
		for (IModule module : getModules().values())
		{
			module.setMod(mod);
			if (!module.areDependenciesMet() && module.getIsActive())
			{
				module.setIsActive(false);
				mod.getLogger().error("Requirements are not met for " + module.getName() + ". Deactivating");
			}
			if (module.getIsActive())
			{
				mod.getLogger().info("Loading " + module.getName() + " module");
			}
		}

		for (IModule module : getModules().values())
		{
			if (module.getIsActive())
			{
				module.preInit(event);
			}
		}
	}

	public void init(FMLInitializationEvent event)
	{
		for (IModule module : getModules().values())
		{
			if (module.getIsActive())
			{
				module.init(event);
			}
		}
	}

	public void postInit(FMLPostInitializationEvent event)
	{
		for (IModule module : getModules().values())
		{
			if (module.getIsActive())
			{
				module.postInit(event);
			}
		}
	}

	public void configureModules()
	{
		for (IModule module : getModules().values())
		{
			this.getConfig().addEntry(module.getName(), new ConfigEntry("Module", module.getName() + " Enabled",
					Type.BOOLEAN, module.getActiveDefault() + ""));
			module.setIsActive(this.getConfig().getBoolean(module.getName(), module.getActiveDefault()));
		}
	}

	public HashMap<String, IModule> getModules()
	{
		return modules;
	}

	public void addModule(IModule module)
	{
		modules.put(module.getName(), module);
	}

	public IModule getModule(String name)
	{
		return modules.get(name);
	}

	public boolean isModuleEnabled(String name)
	{
		return getModule(name) != null && getModule(name).getIsActive();
	}

	private ConfigRegistry getConfig()
	{
		return this.registryHolder.getConfigRegistry();
	}

	private HashMap<String, IModule> getModules(@Nonnull ASMDataTable asmDataTable)
	{
		HashMap<String, IModule> moduleHashMap = new HashMap<>();
		List<IModule> moduleList = ClassLoading.getInstances(asmDataTable, Module.class, IModule.class);
		for(IModule module: moduleList)
		{
			moduleHashMap.put(module.getName(), module);
		}
		return moduleHashMap;
	}
}
