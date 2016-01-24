/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package xyz.brassgoggledcoders.boilerplate.common.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Surseance
 *
 */
public class EntityMinedBlock extends Entity
{
	public Block block;
	public int metadata;
	public boolean doesRotate;

	public float scale;

	public EntityMinedBlock(World world)
	{
		super(world);
		this.preventEntitySpawning = true;
		this.renderDistanceWeight = 20.0D;
		this.scale = 0.9F;
	}

	public EntityMinedBlock(World world, double x, double y, double z, Block block, int md, boolean rotate)
	{
		super(world);
		this.block = block;
		this.metadata = md;
		this.doesRotate = rotate;
		this.preventEntitySpawning = true;
		// this.setSize(0.98F, 0.98F);
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
	protected void entityInit()
	{
	}

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
			{
				this.scale -= 0.0625F;
			}

			if (this.scale <= 0.0F)
			{
				this.setDead();
			}
		}
	}

	/* TODO: Bounding Box
	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return null;
	}
	*/

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
		{
			this.block = Block.getBlockById(tagCompound.getInteger("TileID"));
		}
		else
		{
			this.block = Block.getBlockById(tagCompound.getByte("Tile") & 255);
		}

		this.metadata = tagCompound.getByte("Data") & 255;
		this.scale = tagCompound.getFloat("Scale");
	}

	/* TODO: 1.8 Shadow
	@SideOnly(Side.CLIENT)
	@Override
	public float getShadowSize()
	{
		return 0.0F;
	}
 	*/
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
