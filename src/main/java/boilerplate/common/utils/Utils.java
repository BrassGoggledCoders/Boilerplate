/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
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
		final int randomNum = RANDOM.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public static void playSFX(World world, int x, int y, int z, String sound)
	{
		world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, sound, 1.0F, (world.rand.nextFloat() * 0.4F) + 0.8F);
	}

	public static boolean canPlayerBreakBlock(World world, EntityPlayer player, int x, int y, int z)
	{
		if (getBlockUnbreakable(world, x, y, z) && !player.capabilities.allowEdit)
		{
			return false;
		}
		else
			return true;
	}

	public static boolean getBlockUnbreakable(World world, int x, int y, int z)
	{
		if (world.getBlock(x, y, z).getBlockHardness(world, x, y, z) == -1)
		{
			return true;
		}
		else
			return false;
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
}
