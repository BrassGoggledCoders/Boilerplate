package xyz.brassgoggledcoders.boilerplate.utils;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import xyz.brassgoggledcoders.boilerplate.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.proxies.CommonProxy;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;

/**
 * @author Surseance
 *
 */
public class Utils
{
	public static IBoilerplateMod getCurrentMod()
	{
		Object activeMod = Loader.instance().activeModContainer().getMod();
		if (activeMod instanceof IBoilerplateMod)
		{
			return (IBoilerplateMod) activeMod;
		} else
		{
			FMLLog.bigWarning("Mods using Boilerplate Lib must have their mod class extend IBoilerplateMod!", "");
			return null;
		}
	}

	public static CommonProxy createProxy(String clientString, String serverString)
	{
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		String proxyString = (side == Side.CLIENT) ? clientString : serverString;
		Object proxyObject = createObjectInstance(proxyString);
		if(proxyObject instanceof CommonProxy)
		{
			return (CommonProxy)proxyObject;
		}
		return null;
	}

	public static Object createObjectInstance(String path)
	{
		try
		{
			Class classToGrab;
			classToGrab = Class.forName(path);
			return createObjectInstance(classToGrab);
		} catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		BoilerplateLib.getLogger().error(path + " did not initialize. Something's gonna break.");
		return null;
	}

	public static Object createObjectInstance(Class clazz)
	{
		try
		{
			return clazz.newInstance();
		}catch(InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
		BoilerplateLib.getLogger().error(clazz.getName() + " did not initialize. Something's gonna break.");
		return null;
	}
}
