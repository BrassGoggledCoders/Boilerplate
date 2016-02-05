
package boilerplate.common.utils;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

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

	public static boolean canPlayerBreakBlock(World world, EntityPlayer player, int x, int y, int z)
	{
		return !getBlockUnbreakable(world, x, y, z) && player.capabilities.allowEdit;
	}

	public static boolean getBlockUnbreakable(World world, int x, int y, int z)
	{
		return world.getBlock(x, y, z).getBlockHardness(world, x, y, z) == -1;
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
	 * @param arrays
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
}
