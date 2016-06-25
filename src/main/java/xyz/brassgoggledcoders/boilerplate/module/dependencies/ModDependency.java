package xyz.brassgoggledcoders.boilerplate.module.dependencies;

import net.minecraftforge.fml.common.Loader;
import xyz.brassgoggledcoders.boilerplate.module.ModuleHandler;

public class ModDependency implements IDependency {
	public String modid;

	public ModDependency(String modid) {
		this.modid = modid;
	}

	@Override
	public boolean isMet(ModuleHandler moduleHandler) {
		return Loader.isModLoaded(this.modid);
	}

	@Override
	public String notMetMessage() {
		return "Mod with modid: " + modid + " was not found";
	}
}
