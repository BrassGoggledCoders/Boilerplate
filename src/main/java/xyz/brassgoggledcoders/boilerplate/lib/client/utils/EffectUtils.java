package xyz.brassgoggledcoders.boilerplate.lib.client.utils;

import java.util.Random;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;
import xyz.brassgoggledcoders.boilerplate.lib.client.ClientHelper;

/**
 * @author Surseance
 *
 */
public class EffectUtils
{
	private static EntityFlameFX.Factory flameFactory;
	private static EntitySmokeFX.Factory smokeFactory;

	public static EntityFlameFX.Factory getFlameFactory()
	{
		if (flameFactory == null)
		{
			flameFactory = new EntityFlameFX.Factory();
		}

		return flameFactory;
	}

	public static EntitySmokeFX.Factory getSmokeFactory()
	{
		if (smokeFactory == null)
		{
			smokeFactory = new EntitySmokeFX.Factory();
		}

		return smokeFactory;
	}

	public static void sparkle(final World world, final BlockPos blockPos, final EnumParticleTypes particleType)
	{
		final Random random = world.rand;
		final double offset = 0.0625D;

		for (int amount = 0; amount < 6; amount++)
		{
			double dx = blockPos.getX() + random.nextFloat();
			double dy = blockPos.getY() + random.nextFloat();
			double dz = blockPos.getZ() + random.nextFloat();

			if ((amount == 0) && (!world.isBlockNormalCube(blockPos.up(), false)))
			{
				dy = blockPos.getY() + 1 + offset;
			}

			if ((amount == 1) && (!world.isBlockNormalCube(blockPos.down(), false)))
			{
				dy = blockPos.getY() - offset;
			}

			if ((amount == 2) && (!world.isBlockNormalCube(blockPos.south(), false)))
			{
				dz = blockPos.getZ() + 1 + offset;
			}

			if ((amount == 3) && (!world.isBlockNormalCube(blockPos.north(), false)))
			{
				dz = blockPos.getY() - offset;
			}

			if ((amount == 4) && (!world.isBlockNormalCube(blockPos.east(), false)))
			{
				dx = blockPos.getX() + 1 + offset;
			}

			if ((amount == 5) && (!world.isBlockNormalCube(blockPos.west(), false)))
			{
				dx = blockPos.getX() - offset;
			}

			if ((dx < blockPos.getX()) || (dx > (blockPos.getX() + 1)) || (dy < 0.0D) || (dy > (blockPos.getY() + 1)) || (dz < blockPos.getZ())
					|| (dz > (blockPos.getZ() + 1)))
			{
				world.spawnParticle(particleType, dx, dy, dz, -1.0D, 1.0D, -1.0D);
			}
		}
	}

	public static void blockSparkle(World world, int x, int y, int z, int count)
	{
		if (!world.isRemote)
		{
			return;
		}

		for (; count < 10; ++count)
		{
			double startX = x + world.rand.nextFloat();
			double startY = y + (world.rand.nextFloat() * 1.0F);
			double startZ = z + world.rand.nextFloat();

			double endX = world.rand.nextGaussian() * 0.02D;
			double endY = world.rand.nextGaussian() * 0.02D;
			double endZ = world.rand.nextGaussian() * 0.02D;

			EntityFlameFX ef = getEntityFlameFX(world, startX, startY, startZ, endX, endY, endZ);
			ef.setRBGColorF(0.45F, 0.0F, 0.35F);
			ef.setParticleTextureIndex(82);
			ClientHelper.effectRenderer().addEffect(ef);
		}
	}

	public static void blockFlameFX(World world, int x, int y, int z, int count)
	{
		for (; count < 10; ++count)
		{
			double startX = x + world.rand.nextFloat();
			double startY = y + (world.rand.nextFloat() * 1.0F);
			double startZ = z + world.rand.nextFloat();

			double endX = world.rand.nextGaussian() * 0.02D;
			double endY = world.rand.nextGaussian() * 0.02D;
			double endZ = world.rand.nextGaussian() * 0.02D;

			EntityFlameFX ef = getEntityFlameFX(world, startX, startY, startZ, endX, endY, endZ);
			ClientHelper.effectRenderer().addEffect(ef);
		}
	}

	private static EntityFX particle;

	public static void displayFX(final String name, final World world, final double dx, final double dy, final double dz, final double velX,
			final double velY, final double velZ, final float scale)
	{
		particle = null;

		/** An example of adding an EntityFX class. */
		if (name.equals("smoke"))
		{
			particle = getEntitySmokeFX(world, dx, dy, dz, velX, velY, velZ);
		}

		final double distX = ClientHelper.viewEntity().posX - particle.posX;
		final double distY = ClientHelper.viewEntity().posY - particle.posY;
		final double distZ = ClientHelper.viewEntity().posZ - particle.posZ;
		int display = ClientHelper.settings().particleSetting;

		if ((display == 1) && (particle.worldObj.rand.nextInt(3) == 0))
		{
			display = 2;
		}
		if ((display <= 1) && (((distX * distX) + (distY * distY) + (distZ * distZ)) <= 4096.0D))
		{
			ClientHelper.effectRenderer().addEffect(particle);
		}
	}

	public static EntityFlameFX getEntityFlameFX(World worldIn, double xPos, double yPos, double zPox, double xVec, double yVec, double zVec)
	{
		return (EntityFlameFX) getFlameFactory().getEntityFX(0, worldIn, xPos, yPos, zPox, xVec, yVec, zVec);
	}

	public static EntitySmokeFX getEntitySmokeFX(World worldIn, double xPos, double yPos, double zPox, double xVec, double yVec, double zVec)
	{
		return (EntitySmokeFX) getSmokeFactory().getEntityFX(0, worldIn, xPos, yPos, zPox, xVec, yVec, zVec);
	}

	public static void setGLColorFromInt(int color)
	{
		float red = ((color >> 16) & 255) / 255.0F;
		float green = ((color >> 8) & 255) / 255.0F;
		float blue = (color & 255) / 255.0F;
		GL11.glColor4f(red, green, blue, 1.0F);
	}
}
