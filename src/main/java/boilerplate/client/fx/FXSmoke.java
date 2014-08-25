/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.client.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Created by Surseance
 * 
 */
public class FXSmoke extends EntityReddustFX
{
	float smokeParticleScale;

	private static final ResourceLocation smoke = new ResourceLocation("sc2","textures/misc/smoke.png");

	public FXSmoke(final World world, final double dx, final double dy, final double dz, final float r, final float g, final float b)
	{
		this(world, dx, dy, dz, r, g, b, 1.0F);
	}

	public FXSmoke(final World world, final double dx, final double dy, final double dz, final float r, final float g, final float b,
			final float scale)
	{
		super(world, dx, dy, dz, 0.0F, 0.0F, 0.0F, scale);
		this.motionX *= 0.10000000149011612D;
		this.motionY *= 0.10000000149011612D;
		this.motionZ *= 0.10000000149011612D;
		this.motionX += r;
		this.motionY += g;
		this.motionZ += b;
		this.particleRed = this.particleGreen = this.particleBlue = (float) (Math.random() * 0.30000001192092896D);
		this.particleScale *= 0.75F;
		this.particleScale *= scale;
		this.smokeParticleScale = this.particleScale;
		this.particleMaxAge = (int) (8.0D / ((Math.random() * 0.8D) + 0.2D));
		this.particleMaxAge = (int) (this.particleMaxAge * scale);
		this.noClip = false;
	}

	@Override
	public void renderParticle(final Tessellator tessellator, final float par2,
			final float par3, final float par4, final float par5,
			final float par6, final float par7)
	{
		float f6 = ((this.particleAge + par2) / this.particleMaxAge) * 32.0F;

		if(f6 < 0.0F)
		{
			f6 = 0.0F;
		}
		if(f6 > 1.0F)
		{
			f6 = 1.0F;
		}

		Minecraft.getMinecraft().renderEngine.bindTexture(smoke);

		this.particleScale = this.smokeParticleScale * f6;
		super.renderParticle(tessellator, par2, par3, par4, par5, par6, par7);
	}

	@Override
	public void onUpdate()
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if(this.particleAge++ >= this.particleMaxAge)
		{
			this.setDead();
		}

		this.setParticleTextureIndex(7 - ((this.particleAge * 8) / this.particleMaxAge));
		this.motionY += 0.004D;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);

		if(this.posY == this.prevPosY)
		{
			this.motionX *= 1.1D;
			this.motionZ *= 1.1D;
		}

		this.motionX *= 0.9599999785423279D;
		this.motionY *= 0.9599999785423279D;
		this.motionZ *= 0.9599999785423279D;

		if(this.onGround)
		{
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}
	}
}
