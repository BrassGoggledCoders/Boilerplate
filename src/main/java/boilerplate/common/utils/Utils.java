/**
 * This class was created by <Surseance> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ 23-May-2014
 */
package boilerplate.common.utils;

import java.util.Random;

import net.minecraft.world.World;

// TODO: Auto-generated Javadoc
/**
 * The Class Utils.
 */
public class Utils
{

	/**
	 * Rand int.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the int
	 */
	public static int randInt(final int min, final int max)
	{
		final Random rand = new Random();

		final int randomNum = rand.nextInt(max - min + 1) + min;

		return randomNum;
	}
	/**
	 * Plays a sound at the given location. It's an ugly method, that's why I
	 * moved it here.
	 *
	 * @param world
	 *            - the world. What else?
	 * @param x
	 *            - block xCoord
	 * @param y
	 *            - block yCoord
	 * @param z
	 *            - block zCoord
	 * @param sound
	 *            - sound name
	 */
	public static void playSFX(World world, int x, int y, int z, String sound)
	{
		world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, sound, 1.0F,
				world.rand.nextFloat() * 0.4F + 0.8F);
	}
}
