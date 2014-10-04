/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.client.utils;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

/**
 * @author Surseance
 * 
 */
public class EffectUtils
{
	public static void sparkle(final World world, final int x, final int y, final int z, final String particleName)
	{
		final Random random = world.rand;
		final double offset = 0.0625D;

		for (int amount = 0; amount < 6; amount++)
		{
			double dx = x + random.nextFloat();
			double dy = y + random.nextFloat();
			double dz = z + random.nextFloat();

			if ((amount == 0) && (!world.isBlockNormalCubeDefault(x, y + 1, z, false)))
			{
				dy = y + 1 + offset;
			}

			if ((amount == 1) && (!world.isBlockNormalCubeDefault(x, y - 1, z, false)))
			{
				dy = (y + 0) - offset;
			}

			if ((amount == 2) && (!world.isBlockNormalCubeDefault(x, y, z + 1, false)))
			{
				dz = z + 1 + offset;
			}

			if ((amount == 3) && (!world.isBlockNormalCubeDefault(x, y, z - 1, false)))
			{
				dz = (z + 0) - offset;
			}

			if ((amount == 4) && (!world.isBlockNormalCubeDefault(x + 1, y, z, false)))
			{
				dx = x + 1 + offset;
			}

			if ((amount == 5) && (!world.isBlockNormalCubeDefault(x - 1, y, z, false)))
			{
				dx = (x + 0) - offset;
			}

			if ((dx < x) || (dx > (x + 1)) || (dy < 0.0D) || (dy > (y + 1)) || (dz < z) || (dz > (z + 1)))
			{
				world.spawnParticle(particleName, dx, dy, dz, -1.0D, 1.0D, -1.0D);
				// Steamcraft.proxy.smokeFX(world, dx, dy, dz, FXSmoke.class);
			}
		}
	}

	public static void blockSparkle(World world, int x, int y, int z, int count)
	{
		if (!world.isRemote)
			return;

		for (; count < 10; ++count)
		{
			double startX = x + world.rand.nextFloat();
			double startY = y + (world.rand.nextFloat() * 1.0F);
			double startZ = z + world.rand.nextFloat();

			double endX = world.rand.nextGaussian() * 0.02D;
			double endY = world.rand.nextGaussian() * 0.02D;
			double endZ = world.rand.nextGaussian() * 0.02D;

			EntityFlameFX ef = new EntityFlameFX(world, startX, startY, startZ, endX, endY, endZ);
			ef.setRBGColorF(0.45F, 0.0F, 0.35F);
			ef.setParticleTextureIndex(82);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(ef);
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

			EntityFlameFX ef = new EntityFlameFX(world, startX, startY, startZ, endX, endY, endZ);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(ef);
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
			particle = new EntitySmokeFX(world, dx, dy, dz, velX, velY, velZ, scale);
		}

		final Minecraft mc = Minecraft.getMinecraft();
		final double distX = mc.renderViewEntity.posX - particle.posX;
		final double distY = mc.renderViewEntity.posY - particle.posY;
		final double distZ = mc.renderViewEntity.posZ - particle.posZ;
		int display = mc.gameSettings.particleSetting;

		if ((display == 1) && (particle.worldObj.rand.nextInt(3) == 0))
		{
			display = 2;
		}
		if ((display <= 1) && (((distX * distX) + (distY * distY) + (distZ * distZ)) <= 4096.0D))
		{
			mc.effectRenderer.addEffect(particle);
		}
	}

	public static void setGLColorFromInt(int color)
	{
		float red = ((color >> 16) & 255) / 255.0F;
		float green = ((color >> 8) & 255) / 255.0F;
		float blue = (color & 255) / 255.0F;
		GL11.glColor4f(red, green, blue, 1.0F);
	}
}
