package xyz.brassgoggledcoders.boilerplate.module.dependencies;

import net.minecraftforge.classloading.FMLForgePlugin;
import xyz.brassgoggledcoders.boilerplate.module.ModuleHandler;

public class DevDependency implements IDependency{
	@Override
	public boolean isMet(ModuleHandler moduleHandler) {
		return !FMLForgePlugin.RUNTIME_DEOBF;
	}
}
