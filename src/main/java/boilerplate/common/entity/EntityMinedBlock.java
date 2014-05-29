/**
 * This class was created by <Surseance> as a part of the
 * EnderGloves mod for Minecraft.
 *
 * This mod is registered under the WTFPL v2.0. Please read the
 * COPYING.WTFPL file for more details.
 *
 * File created @[May 25, 2014, 3:05:32 PM]
 */
package boilerplate.common.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 * Email: surseance@autistici.org
 *
 */
public class EntityMinedBlock extends Entity
{
	public Block block;
	public int metadata;

	public float scale;

	public EntityMinedBlock(World world)
	{
		super(world);
		this.preventEntitySpawning = true;
		this.renderDistanceWeight = 20.0D;
		this.scale = 0.9F;
	}

	//public EntityMinedBlock(World world, double x, double y, double z, Block block)
	//{
	//	this(world, x, y, z, block, 0);
	//}

	public EntityMinedBlock(World world, double x, double y, double z, Block block, int md)
	{
		super(world);
		this.block = block;
		this.metadata = md;
		this.preventEntitySpawning = true;
		// this.setSize(0.98F, 0.98F);
		this.yOffset = this.height / 2.0F; 
		this.setPosition(x, y, z);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
		this.scale = 0.9F;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	protected void entityInit() {} 

	@Override
	public boolean canBeCollidedWith()
	{
		return false;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.getBlock() != null)
		{
			if ((this.worldObj.getWorldTime() % 1) == 0)
				this.scale -= 0.0625F;

			if (this.scale <= 0.0F)
				this.setDead();
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return null;
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		tagCompound.setByte("Tile", (byte) Block.getIdFromBlock(this.block));
		tagCompound.setInteger("TileID", Block.getIdFromBlock(this.block));
		tagCompound.setByte("Data", (byte) this.metadata);

		tagCompound.setFloat("Scale", this.scale);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tagCompound)
	{
		if (tagCompound.hasKey("TileID", 99))
			this.block = Block.getBlockById(tagCompound.getInteger("TileID"));
		else
			this.block = Block.getBlockById(tagCompound.getByte("Tile") & 255);

		this.metadata = tagCompound.getByte("Data") & 255;
		this.scale = tagCompound.getFloat("Scale");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public float getShadowSize()
	{
		return 0.0F;
	}

	@SideOnly(Side.CLIENT)
	public World getWorldObj()
	{
		return this.worldObj;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean canRenderOnFire()
	{
		return false;
	}

	public Block getBlock()
	{
		return this.block;
	}
}
