package xyz.brassgoggledcoders.boilerplate.module;

import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.config.ConfigEntry;
import xyz.brassgoggledcoders.boilerplate.config.Type;
import xyz.brassgoggledcoders.boilerplate.module.dependencies.IDependency;
import xyz.brassgoggledcoders.boilerplate.registries.ConfigRegistry;
import xyz.brassgoggledcoders.boilerplate.registries.IRegistryHolder;
import xyz.brassgoggledcoders.boilerplate.utils.ClassLoading;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * @author SkySom
 */
public class ModuleHandler {
	private SortedMap<String, IModule> modules = new TreeMap<>();
	private IRegistryHolder registryHolder;
	private IBoilerplateMod mod;

	private Stream<IModule> moduleStream;

	public ModuleHandler(IBoilerplateMod mod, ASMDataTable asmDataTable) {
		this.mod = mod;
		this.registryHolder = mod.getRegistryHolder();
		this.modules = this.getModules(asmDataTable);
	}

	public void preInit(FMLPreInitializationEvent event) {
		this.modules.values().stream().filter(IModule::getIsActive).forEachOrdered(module -> module.preInit(event));
	}

	public void init(FMLInitializationEvent event) {
		this.modules.values().stream().filter(IModule::getIsActive).forEachOrdered(module -> module.init(event));
	}

	public void postInit(FMLPostInitializationEvent event) {
		this.modules.values().stream().filter(IModule::getIsActive).forEachOrdered(module -> module.postInit(event));
	}

	public void setupModules() {
		for(IModule module : getModules().values()) {
			this.getConfig().addEntry(module.getName(), new ModuleConfigEntry(module));
			module.setIsActive(this.getConfig().getBoolean(module.getName(), module.getActiveDefault()));
			module.setMod(this.mod);
		}

		this.modules.values().stream().filter(IModule::getIsActive).forEach(this::checkDependencies);
	}

	private void checkDependencies(IModule module) {
		module.getDependencies(new ArrayList<>())
				.forEach(dependency -> printModuleDependencyOutcome(module, dependency));
		if(module.getIsActive()) {
			this.mod.getLogger().info("Module " + module.getName() + " has successfully loaded");
		}
	}

	private void printModuleDependencyOutcome(IModule module, IDependency dependency) {
		if(!dependency.isMet(this)) {
			this.mod.getLogger().error("Module " + module.getName() + " did not load due to issue: " +
					dependency.notMetMessage());
			module.setIsActive(false);
		}
	}

	public SortedMap<String, IModule> getModules() {
		return modules;
	}

	public void addModule(IModule module) {
		modules.put(module.getName(), module);
	}

	public IModule getModule(String name) {
		return modules.get(name);
	}

	public boolean isModuleEnabled(String name) {
		return getModule(name) != null && getModule(name).getIsActive();
	}

	private ConfigRegistry getConfig() {
		return this.registryHolder.getConfigRegistry();
	}

	private TreeMap<String, IModule> getModules(@Nonnull ASMDataTable asmDataTable) {
		TreeMap<String, IModule> moduleMap = new TreeMap<>();
		List<IModule> moduleList = ClassLoading.getInstances(asmDataTable, Module.class, IModule.class);
		moduleList.sort(new ModuleComparator());
		for(IModule module : moduleList) {
			for(Annotation annotation : module.getClass().getDeclaredAnnotations()) {
				if(annotation instanceof Module) {
					if(((Module) annotation).mod().equals(mod.getID())) {
						moduleMap.put(module.getName(), module);
					}
				}
			}
		}
		return moduleMap;
	}

	private static class ModuleConfigEntry extends ConfigEntry {
		public ModuleConfigEntry(IModule module) {
			super("Module", module.getName() + " enabled", Type.BOOLEAN, module.getActiveDefault() + "");
		}
	}
}
