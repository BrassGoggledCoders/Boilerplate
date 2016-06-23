package xyz.brassgoggledcoders.boilerplate.module.dependencies;

import xyz.brassgoggledcoders.boilerplate.module.ModuleHandler;

public interface IDependency {
	boolean isMet(ModuleHandler moduleHandler);
}
