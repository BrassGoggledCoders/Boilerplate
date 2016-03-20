package xyz.brassgoggledcoders.boilerplate.lib.common.utils;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.common.CommonProxy;
import xyz.brassgoggledcoders.boilerplate.lib.common.IBoilerplateMod;

/**
 * @author Surseance
 *
 */
public class Utils
{
	public static void spawnEntityAtCoords(World world, EntityLiving entity, int x, int y, int z)
	{
		entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
		entity.rotationYawHead = entity.rotationYaw;
		entity.renderYawOffset = entity.rotationYaw;
		world.spawnEntityInWorld(entity);
		entity.playLivingSound();
	}

	/**
	 * This method merges any number of arrays of any count.
	 *
	 * @param arrays multiple arrays of any type
	 * @return merged array
	 */
	public static <T> T[] merge(T[]... arrays)
	{
		// Count the number of arrays passed for merging and the total size of
		// resulting array
		int arrCount = 0;
		int count = 0;
		for (T[] array : arrays)
		{
			arrCount++;
			count += array.length;
		}

		// Create new array and copy all array contents
		T[] mergedArray = (T[]) java.lang.reflect.Array.newInstance(arrays[0][0].getClass(), count);
		int start = 0;
		for (T[] array : arrays)
		{
			System.arraycopy(array, 0, mergedArray, start, array.length);
			start += array.length;
		}
		return mergedArray;
	}

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
		}catch(InstantiationException e)
		{
			e.printStackTrace();
		} catch(IllegalAccessException e)
		{
			e.printStackTrace();
		}
		BoilerplateLib.getLogger().error(clazz.getName() + " did not initialize. Something's gonna break.");
		return null;
	}
}
