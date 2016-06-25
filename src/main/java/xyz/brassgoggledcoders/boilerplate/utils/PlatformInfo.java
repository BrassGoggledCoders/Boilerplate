package xyz.brassgoggledcoders.boilerplate.utils;

import net.minecraft.launchwrapper.Launch;

public class PlatformInfo {
	public static boolean isDev() {
		return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
	}
}

