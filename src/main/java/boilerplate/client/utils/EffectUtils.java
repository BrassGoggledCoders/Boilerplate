package boilerplate.client.utils;

import java.util.Random;

import net.minecraft.world.World;

public class EffectUtils
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

}
