package xyz.brassgoggledcoders.boilerplate.module.dependencies;

import xyz.brassgoggledcoders.boilerplate.module.ModuleHandler;

public class ModuleDependency implements IDependency {
	public String moduleName;

	public ModuleDependency(String moduleName) {
		this.moduleName = moduleName;
	}

	@Override
	public boolean isMet(ModuleHandler moduleHandler) {
		return moduleHandler.isModuleEnabled(this.moduleName);
	}

	@Override
	public String notMetMessage() {
		return "Module with name: " + moduleName + " is not active";
	}
}
