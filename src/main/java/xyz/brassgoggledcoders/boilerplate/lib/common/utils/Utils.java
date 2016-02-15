package xyz.brassgoggledcoders.boilerplate.lib.common.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import xyz.brassgoggledcoders.boilerplate.lib.common.IBoilerplateMod;

import java.util.Random;

/**
 * @author Surseance
 *
 */
public class Utils
{
	public static final Random RANDOM = new Random();

	public static int randInt(final int min, final int max)
	{
		return RANDOM.nextInt((max - min) + 1) + min;
	}

	public static void playSFX(World world, int x, int y, int z, String sound)
	{
		world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, sound, 1.0F, (world.rand.nextFloat() * 0.4F) + 0.8F);
	}

	public static boolean canPlayerBreakBlock(World world, EntityPlayer player, BlockPos blockPos)
	{
		return !getBlockUnbreakable(world, blockPos) && player.capabilities.allowEdit;
	}

	public static boolean getBlockUnbreakable(World world, BlockPos blockPos)
	{
		return world.getBlockState(blockPos).getBlock().getBlockHardness(world, blockPos) == -1;
	}

	public static void spawnEntityAtCoords(World world, Entity entity, int x, int y, int z)
	{
		EntityLiving entityliving = (EntityLiving) entity;
		entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
		entityliving.rotationYawHead = entityliving.rotationYaw;
		entityliving.renderYawOffset = entityliving.rotationYaw;
		world.spawnEntityInWorld(entity);
		entityliving.playLivingSound();
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
}
