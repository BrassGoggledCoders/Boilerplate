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
package boilerplate.common.baseclasses;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public abstract class BaseProjectileEntity extends Entity implements IProjectile
{
	protected int xTile = -1;
	protected int yTile = -1;
	protected int zTile = -1;

	protected Block inTile;

	protected Entity shootingEntity;

	protected int timeTillDeath;
	protected int flyTime;

	protected int accuracy;

	public BaseProjectileEntity(World world)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.5F, 0.5F);
	}

	public BaseProjectileEntity(World world, double dx, double dy, double dz)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.5F, 0.5F);
		this.setPosition(dx, dy, dz);
		this.yOffset = 0.0F;
	}

	public BaseProjectileEntity(World world, EntityLivingBase shooter, EntityLivingBase target, float frotY, float frotP)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = shooter;

		this.posY = (shooter.posY + shooter.getEyeHeight()) - 0.10000000149011612D;
		double dx = target.posX - shooter.posX;
		double dy = (target.boundingBox.minY + (target.height / 3.0F)) - this.posY;
		double dz = target.posZ - shooter.posZ;
		double magnitude = MathHelper.sqrt_double((dx * dx) + (dz * dz));

		if(magnitude >= 1.0E-7D)
		{
			float fx = (float) ((Math.atan2(dz, dx) * 180.0D) / Math.PI) - 90.0F;
			float fy = (float) -((Math.atan2(dy, magnitude) * 180.0D) / Math.PI);
			double dlx = dx / magnitude;
			double dlz = dz / magnitude;
			this.setLocationAndAngles(shooter.posX + dlx, this.posY, shooter.posZ + dlz, fx, fy);
			this.yOffset = 0.0F;
			float height = (float) magnitude * 0.2F;
			this.setThrowableHeading(dx, dy + height, dz, frotY, frotP);
		}
	}

	public BaseProjectileEntity(World world, EntityLivingBase shooter, int damage, int accuracy)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = shooter;

		this.setSize(0.5F, 0.5F);
		this.setLocationAndAngles(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
		this.posX -= MathHelper.cos((this.rotationYaw / 180.0F) * (float) Math.PI) * 0.16F;
		this.posY -= 0.10000000149011612D;
		this.posZ -= MathHelper.sin((this.rotationYaw / 180.0F) * (float) Math.PI) * 0.16F;
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = -MathHelper.sin((this.rotationYaw / 180.0F) * (float) Math.PI)
				* MathHelper.cos((this.rotationPitch / 180.0F) * (float) Math.PI);
		this.motionZ = MathHelper.cos((this.rotationYaw / 180.0F) * (float) Math.PI)
				* MathHelper.cos((this.rotationPitch / 180.0F) * (float) Math.PI);
		this.motionY = -MathHelper.sin((this.rotationPitch / 180.0F) * (float) Math.PI);
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 1F, accuracy);
		this.accuracy = accuracy;
	}

	@Override
	public void setThrowableHeading(double dx, double dy, double dz, float frotY, float frotP)
	{
		int accuracy = this.accuracy;
		float f2 = MathHelper.sqrt_double((dx * dx) + (dy * dy) + (dz * dz));
		dx /= f2;
		dy /= f2;
		dz /= f2;
		dx += (this.rand.nextGaussian() * 0.0034999998323619365D * frotP * accuracy) / 5;
		dy += (this.rand.nextGaussian() * 0.0034999998323619365D * frotP * accuracy) / 5;
		dz += (this.rand.nextGaussian() * 0.0034999998323619365D * frotP * accuracy) / 5;
		dx *= frotY;
		dy *= frotY;
		dz *= frotY;
		this.motionX = dx;
		this.motionY = dy;
		this.motionZ = dz;
		float magnitude = MathHelper.sqrt_double((dx * dx) + (dz * dz));
		this.prevRotationYaw = this.rotationYaw = (float) ((Math.atan2(dx, dz) * 180D) / 3.1415927410125732D);
		this.prevRotationPitch = this.rotationPitch = (float) ((Math.atan2(dy, magnitude) * 180D) / 3.1415927410125732D);
		this.timeTillDeath = 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double dx, double dy, double dz, float frotY, float frotP, int i)
	{
		this.setPosition(dx, dy, dz);
		this.setRotation(frotY, frotP);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setVelocity(double dx, double dy, double dz)
	{
		this.motionX = dx;
		this.motionY = dy;
		this.motionZ = dz;

		if((this.prevRotationPitch == 0.0F) && (this.prevRotationYaw == 0.0F))
		{
			float magnitude = MathHelper.sqrt_double((dx * dx) + (dz * dz));
			this.prevRotationYaw = this.rotationYaw = (float) ((Math.atan2(dx, dz) * 180D) / 3.1415927410125732D);
			this.prevRotationPitch = this.rotationPitch = (float) ((Math.atan2(dy, magnitude) * 180D) / 3.1415927410125732D);
		}
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if(this.flyTime > 1000)
			this.setDead();

		if((this.prevRotationPitch == 0.0F) && (this.prevRotationYaw == 0.0F))
		{
			float magnitude = MathHelper.sqrt_double((this.motionX * this.motionX) + (this.motionZ * this.motionZ));
			this.prevRotationYaw = this.rotationYaw = (float) ((Math.atan2(this.motionX, this.motionZ) * 180D) / 3.1415927410125732D);
			this.prevRotationPitch = this.rotationPitch = (float) ((Math.atan2(this.motionY, magnitude) * 180D) / 3.1415927410125732D);
		}
		this.flyTime++;

		Vec3 posVector = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		Vec3 velVector = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition mop = this.worldObj.rayTraceBlocks(posVector, velVector, false);

		posVector = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		velVector = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

		if(mop != null)
			velVector = Vec3.createVectorHelper(mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord);

		Entity entity = null;
		List<?> entList = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox
				.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
		double distance = 0.0D;

		for(int listSize = 0; listSize < entList.size(); listSize++)
		{
			Entity collidableEnt = (Entity) entList.get(listSize);

			if(!collidableEnt.canBeCollidedWith() || ((collidableEnt == this.shootingEntity) && (this.flyTime < 5)))
				continue;

			float amount = 0.3F;
			AxisAlignedBB aaBB = collidableEnt.boundingBox.expand(amount, amount, amount);
			MovingObjectPosition objectInVector = aaBB.calculateIntercept(posVector, velVector);

			if(objectInVector == null)
				continue;

			double distanceToObject = posVector.distanceTo(objectInVector.hitVec);

			if((distanceToObject < distance) || (distance == 0.0D))
			{
				entity = collidableEnt;
				distance = distanceToObject;
			}
		}

		if(entity != null)
			mop = new MovingObjectPosition(entity);

		if(mop != null)
			if(mop.entityHit != null)
			{
					this.onHitEntity(mop.entityHit);
					this.motionX *= 0.10000000149011612D;
					this.motionY *= 0.10000000149011612D;
					this.motionZ *= 0.10000000149011612D;
					this.flyTime = 0;
					this.setDead();
			}
			else
			{
				this.xTile = mop.blockX;
				this.yTile = mop.blockY;
				this.zTile = mop.blockZ;
				this.inTile = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);

				onHitBlock(inTile, mop);
			}

		this.posX += this.motionX * 3.0D;
		this.posY += this.motionY * 3.0D;
		this.posZ += this.motionZ * 3.0D;
		float magnitude = MathHelper.sqrt_double((this.motionX * this.motionX) + (this.motionZ * this.motionZ));
		this.rotationYaw = (float) ((Math.atan2(this.motionX, this.motionZ) * 180D) / 3.1415927410125732D);

		for(this.rotationPitch = (float) ((Math.atan2(this.motionY, magnitude) * 180D) / 3.1415927410125732D); (this.rotationPitch - this.prevRotationPitch) < -180F; this.prevRotationPitch -= 360F)
		{
		}

		for(; (this.rotationPitch - this.prevRotationPitch) >= 180F; this.prevRotationPitch += 360F)
		{
		}

		for(; (this.rotationYaw - this.prevRotationYaw) < -180F; this.prevRotationYaw -= 360F)
		{
		}

		for(; (this.rotationYaw - this.prevRotationYaw) >= 180F; this.prevRotationYaw += 360F)
		{
		}

		this.rotationPitch = this.prevRotationPitch + ((this.rotationPitch - this.prevRotationPitch) * 0.2F);
		this.rotationYaw = this.prevRotationYaw + ((this.rotationYaw - this.prevRotationYaw) * 0.2F);
		float speed = 0.99F;

		if(this.handleWaterMovement())
			this.setDead();

		this.motionX *= speed;
		this.motionY *= speed;
		this.motionZ *= speed;
		this.setPosition(this.posX, this.posY, this.posZ);

		if(this.worldObj.isRemote)
		{
			this.worldObj.spawnParticle("explode", this.posX, this.posY, this.posZ, 0, 0, 0);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		tagCompound.setShort("xTile", (short) this.xTile);
		tagCompound.setShort("yTile", (short) this.yTile);
		tagCompound.setShort("zTile", (short) this.zTile);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompound)
	{
		this.xTile = tagCompound.getShort("xTile");
		this.yTile = tagCompound.getShort("yTile");
		this.zTile = tagCompound.getShort("zTile");
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize() { return 0.0F; }

	@Override
	public boolean canAttackWithItem() { return false; }

	public abstract void onHitEntity(Entity entity);

	public abstract void onHitBlock(Block block, MovingObjectPosition mop);
}
