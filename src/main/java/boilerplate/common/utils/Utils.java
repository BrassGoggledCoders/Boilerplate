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

import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Utils
{
	public static void sparkle(final World world, final int x, final int y,
			final int z, final String particleName)
	{
		final Random random = world.rand;
		final double offset = 0.0625D;

		for (int amount = 0; amount < 6; amount++)
		{
			double dx = x + random.nextFloat();
			double dy = y + random.nextFloat();
			double dz = z + random.nextFloat();

			if ((amount == 0)
					&& (!world.isBlockNormalCubeDefault(x, y + 1, z, false)))
			{
				dy = y + 1 + offset;
			}

			if ((amount == 1)
					&& (!world.isBlockNormalCubeDefault(x, y - 1, z, false)))
			{
				dy = y + 0 - offset;
			}

			if ((amount == 2)
					&& (!world.isBlockNormalCubeDefault(x, y, z + 1, false)))
			{
				dz = z + 1 + offset;
			}

			if ((amount == 3)
					&& (!world.isBlockNormalCubeDefault(x, y, z - 1, false)))
			{
				dz = z + 0 - offset;
			}

			if ((amount == 4)
					&& (!world.isBlockNormalCubeDefault(x + 1, y, z, false)))
			{
				dx = x + 1 + offset;
			}

			if ((amount == 5)
					&& (!world.isBlockNormalCubeDefault(x - 1, y, z, false)))
			{
				dx = x + 0 - offset;
			}

			if ((dx < x) || (dx > x + 1) || (dy < 0.0D) || (dy > y + 1)
					|| (dz < z) || (dz > z + 1))
			{
				world.spawnParticle(particleName, dx, dy, dz, -1.0D, 1.0D,
						-1.0D);
				// Steamcraft.proxy.smokeFX(world, dx, dy, dz, FXSmoke.class);
			}
		}
	}

	public static Material getBlockMaterial(final IBlockAccess world,
			final int x, final int y, final int z)
	{
		if (world.getBlock(x, y, z) != null)
		{
			return world.getBlock(x, y, z).getMaterial();
		}

		return Material.air;
	}

	public static int randInt(final int min, final int max)
	{
		// Usually this can be a field rather than a method variable
		final Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		final int randomNum = rand.nextInt(max - min + 1) + min;

		return randomNum;
	}

	public static void sendToPlayers(final Packet packet, final World world,
			final int x, final int y, final int z, Integer maxDistance)
	{
		if (maxDistance == null)
		{
			maxDistance = Integer.valueOf(128);
		}

		Iterator<?> iterator;

		if (packet != null)
		{
			for (iterator = world.playerEntities.iterator(); iterator.hasNext();)
			{
				final Object player = iterator.next();
				final EntityPlayerMP playerMP = (EntityPlayerMP) player;

				if ((Math.abs(playerMP.posX - x) <= maxDistance.intValue())
						&& (Math.abs(playerMP.posY - y) <= maxDistance
								.intValue())
						&& (Math.abs(playerMP.posZ - z) <= maxDistance
								.intValue()))
				{
					playerMP.playerNetServerHandler.sendPacket(packet);
				}
			}
		}
	}
}
