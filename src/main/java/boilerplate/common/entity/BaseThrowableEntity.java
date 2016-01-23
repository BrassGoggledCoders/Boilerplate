/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package boilerplate.common.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public abstract class BaseThrowableEntity extends EntityThrowable
{
	private int xTile = -1;
	private int yTile = -1;
	private int zTile = -1;
	private Block inTile;
	protected boolean inGround;
	public int throwableShake;
	/** The entity that threw this throwable item. */
	private EntityLivingBase thrower;
	private String throwerName;
	private int ticksInGround;
	private int ticksInAir;

	public BaseThrowableEntity(World p_i1776_1_)
	{
		super(p_i1776_1_);
		this.setSize(0.25F, 0.25F);
	}

	/**
	 * Checks if the entity is in range to render by using the past in distance
	 * and comparing it to its average edge length * 64 * renderDistanceWeight
	 * Args: distance
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderDist(double p_70112_1_)
	{
		double d1 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0D;
		d1 *= 64.0D;
		return p_70112_1_ < (d1 * d1);
	}

	public BaseThrowableEntity(World world, EntityLivingBase entity)
	{
		super(world, entity);
	}

	public BaseThrowableEntity(World p_i1778_1_, double p_i1778_2_, double p_i1778_4_, double p_i1778_6_)
	{
		super(p_i1778_1_, p_i1778_2_, p_i1778_4_, p_i1778_6_);
	}

	/* TODO What is this?
	@Override
	protected float func_70182_d()
	{
		return 1.5F;
	}

	@Override
	protected float func_70183_g()
	{
		return 0.0F;
	}
	*/

	/**
	 * Similar to setArrowHeading, it's point the throwable entity to a x, y, z
	 * direction.
	 */
	@Override
	public void setThrowableHeading(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_)
	{
		float f2 = MathHelper.sqrt_double((p_70186_1_ * p_70186_1_) + (p_70186_3_ * p_70186_3_) + (p_70186_5_ * p_70186_5_));
		p_70186_1_ /= f2;
		p_70186_3_ /= f2;
		p_70186_5_ /= f2;
		p_70186_1_ += this.rand.nextGaussian() * 0.007499999832361937D * p_70186_8_;
		p_70186_3_ += this.rand.nextGaussian() * 0.007499999832361937D * p_70186_8_;
		p_70186_5_ += this.rand.nextGaussian() * 0.007499999832361937D * p_70186_8_;
		p_70186_1_ *= p_70186_7_;
		p_70186_3_ *= p_70186_7_;
		p_70186_5_ *= p_70186_7_;
		this.motionX = p_70186_1_;
		this.motionY = p_70186_3_;
		this.motionZ = p_70186_5_;
		float f3 = MathHelper.sqrt_double((p_70186_1_ * p_70186_1_) + (p_70186_5_ * p_70186_5_));
		this.prevRotationYaw = this.rotationYaw = (float) ((Math.atan2(p_70186_1_, p_70186_5_) * 180.0D) / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float) ((Math.atan2(p_70186_3_, f3) * 180.0D) / Math.PI);
		this.ticksInGround = 0;
	}

	/**
	 * Sets the velocity to the args. Args: x, y, z
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void setVelocity(double p_70016_1_, double p_70016_3_, double p_70016_5_)
	{
		this.motionX = p_70016_1_;
		this.motionY = p_70016_3_;
		this.motionZ = p_70016_5_;

		if ((this.prevRotationPitch == 0.0F) && (this.prevRotationYaw == 0.0F))
		{
			float f = MathHelper.sqrt_double((p_70016_1_ * p_70016_1_) + (p_70016_5_ * p_70016_5_));
			this.prevRotationYaw = this.rotationYaw = (float) ((Math.atan2(p_70016_1_, p_70016_5_) * 180.0D) / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) ((Math.atan2(p_70016_3_, f) * 180.0D) / Math.PI);
		}
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{
		this.lastTickPosX = this.posX;
		this.lastTickPosY = this.posY;
		this.lastTickPosZ = this.posZ;
		super.onUpdate();

		if (this.throwableShake > 0)
		{
			--this.throwableShake;
		}

		if (this.inGround)
		{
			if (this.worldObj.getBlockState(this.getPosition()).getBlock() == this.inTile)
			{
				++this.ticksInGround;

				if (this.ticksInGround == 1200)
				{
					this.setDead();
				}

				return;
			}

			this.inGround = false;
			this.motionX *= this.rand.nextFloat() * 0.2F;
			this.motionY *= this.rand.nextFloat() * 0.2F;
			this.motionZ *= this.rand.nextFloat() * 0.2F;
			this.ticksInGround = 0;
			this.ticksInAir = 0;
		}
		else
		{
			++this.ticksInAir;
		}

		Vec3 vec3 = new Vec3(this.posX, this.posY, this.posZ);
		Vec3 vec31 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec3, vec31);
		vec3 = new Vec3(this.posX, this.posY, this.posZ);
		vec31 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

		if (movingobjectposition != null)
		{
			vec31 = new Vec3(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord,
					movingobjectposition.hitVec.zCoord);
		}

		if (!this.worldObj.isRemote)
		{
			Entity entity = null;
			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this,
					this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double d0 = 0.0D;
			EntityLivingBase entitylivingbase = this.getThrower();

			for (Object obj : list)
			{
				Entity entity1 = (Entity) obj;

				if (entity1.canBeCollidedWith() && ((entity1 != entitylivingbase) || (this.ticksInAir >= 5)))
				{
					float f = 0.3F;
					AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand(f, f, f);
					MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);

					if (movingobjectposition1 != null)
					{
						double d1 = vec3.distanceTo(movingobjectposition1.hitVec);

						if ((d1 < d0) || (d0 == 0.0D))
						{
							entity = entity1;
							d0 = d1;
						}
					}
				}
			}

			if (entity != null)
			{
				movingobjectposition = new MovingObjectPosition(entity);
			}
		}

		if (movingobjectposition != null)
		{
			if ((movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) && (this.worldObj
					.getBlockState(movingobjectposition.getBlockPos()).getBlock() == Blocks.portal))
			{
				this.inPortal = true;
			}
			else
			{
				this.onImpact(movingobjectposition);
			}
		}

		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		float f1 = MathHelper.sqrt_double((this.motionX * this.motionX) + (this.motionZ * this.motionZ));
		this.rotationYaw = (float) ((Math.atan2(this.motionX, this.motionZ) * 180.0D) / Math.PI);

		for (this.rotationPitch = (float) ((Math.atan2(this.motionY, f1) * 180.0D) / Math.PI); (this.rotationPitch
				- this.prevRotationPitch) < -180.0F; this.prevRotationPitch -= 360.0F)
		{
			;
		}

		while ((this.rotationPitch - this.prevRotationPitch) >= 180.0F)
		{
			this.prevRotationPitch += 360.0F;
		}

		while ((this.rotationYaw - this.prevRotationYaw) < -180.0F)
		{
			this.prevRotationYaw -= 360.0F;
		}

		while ((this.rotationYaw - this.prevRotationYaw) >= 180.0F)
		{
			this.prevRotationYaw += 360.0F;
		}

		this.rotationPitch = this.prevRotationPitch + ((this.rotationPitch - this.prevRotationPitch) * 0.2F);
		this.rotationYaw = this.prevRotationYaw + ((this.rotationYaw - this.prevRotationYaw) * 0.2F);
		float f2 = 0.99F;
		float f3 = this.getGravityVelocity();

		if (this.isInWater())
		{
			for (int i = 0; i < 4; ++i)
			{
				float f4 = 0.25F;
				this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - (this.motionX * f4),
						this.posY - (this.motionY * f4), this.posZ - (this.motionZ * f4), this.motionX, this.motionY,
						this.motionZ);
			}

			f2 = 0.8F;
		}

		this.motionX *= f2;
		this.motionY *= f2;
		this.motionZ *= f2;
		this.motionY -= f3;
		this.setPosition(this.posX, this.posY, this.posZ);
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound p_70014_1_)
	{
		p_70014_1_.setShort("xTile", (short) this.xTile);
		p_70014_1_.setShort("yTile", (short) this.yTile);
		p_70014_1_.setShort("zTile", (short) this.zTile);
		p_70014_1_.setByte("inTile", (byte) Block.getIdFromBlock(this.inTile));
		p_70014_1_.setByte("shake", (byte) this.throwableShake);
		p_70014_1_.setByte("inGround", (byte) (this.inGround ? 1 : 0));

		if (((this.throwerName == null) || (this.throwerName.length() == 0)) && (this.thrower != null) && (this.thrower instanceof EntityPlayer))
		{
			this.throwerName = this.thrower.getCommandSenderEntity().getName();
		}

		p_70014_1_.setString("ownerName", this.throwerName == null ? "" : this.throwerName);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound p_70037_1_)
	{
		this.xTile = p_70037_1_.getShort("xTile");
		this.yTile = p_70037_1_.getShort("yTile");
		this.zTile = p_70037_1_.getShort("zTile");
		this.inTile = Block.getBlockById(p_70037_1_.getByte("inTile") & 255);
		this.throwableShake = p_70037_1_.getByte("shake") & 255;
		this.inGround = p_70037_1_.getByte("inGround") == 1;
		this.throwerName = p_70037_1_.getString("ownerName");

		if ((this.throwerName != null) && (this.throwerName.length() == 0))
		{
			this.throwerName = null;
		}
	}

	/* TODO: Shadows
	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0F;
	}
	*/

	@Override
	public EntityLivingBase getThrower()
	{
		if ((this.thrower == null) && (this.throwerName != null) && (this.throwerName.length() > 0))
		{
			this.thrower = this.worldObj.getPlayerEntityByName(this.throwerName);
		}

		return this.thrower;
	}
}
