package xyz.brassgoggledcoders.boilerplate.utils;

import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;

/**
 * @author Surseance
 */
public class Utils {
	public static IBoilerplateMod getCurrentMod() {
		Object activeMod = Loader.instance().activeModContainer().getMod();
		if(activeMod instanceof IBoilerplateMod)
			return (IBoilerplateMod) activeMod;
		else {
			FMLLog.bigWarning("Mods using Boilerplate must have their mod class extend IBoilerplateMod!", "");
			return null;
		}
	}

	public static void attemptLogErrorToCurrentMod(String logString) {
		IBoilerplateMod mod = Utils.getCurrentMod();
		if(mod != null)
			mod.getLogger().error(logString);
	}
}
