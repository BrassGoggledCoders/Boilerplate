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
 * File created @ [Mar 12, 2014, 5:46:41 PM]
 */
package boilerplate.steamapi.machines;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * The Interface IMachine.
 * 
 * @author Surseance (Johnny Eatmon)
 */
public interface IMachine
{
	/**
	 * Checks if is active.
	 * 
	 * @return true, if is active
	 */
	public abstract boolean isActive();

	/**
	 * Gets the facing direction.
	 * 
	 * @return the facing direction
	 */
	public abstract int getFacingDirection();

	/**
	 * Sets the facing direction.
	 * 
	 * @param paramInt
	 *            the new facing direction
	 */
	public abstract void setFacingDirection(int paramInt);

	/**
	 * 
	 * 
	 * @param paramNBTTagCompound the param nbt tag compound
	 */
	public abstract void writeTag(NBTTagCompound tagCompound);

	/**
	 * B.
	 * 
	 * @param paramNBTTagCompound
	 *            the param nbt tag compound
	 */
	public abstract void readTag(NBTTagCompound tagCompound);

	/**
	 * Write packet.
	 * 
	 * @param outputStream the param data output stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public abstract void writePacket(DataOutputStream outputStream) throws IOException;

	/**
	 * Read packet.
	 * 
	 * @param inputStream the param data input stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public abstract void readPacket(DataInputStream inputStream) throws IOException;

	/**
	 * Checks if is solid on side.
	 * 
	 * @param direction
	 *            the param forge direction
	 * @return true, if is solid on side
	 */
	public abstract boolean isSolidOnSide(ForgeDirection direction);

	/**
	 * On break.
	 */
	public abstract void onBreak();

	/**
	 * On block placed by.
	 * 
	 * @param world the param world
	 * @param x the param int1
	 * @param y the param int2
	 * @param z the param int3
	 * @param entityLiving the param entity living
	 */
	public abstract void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving);

	/**
	 * On activated.
	 * 
	 * @param player the param entity player
	 * @param paramInt the param int
	 * @param hitX the param float1
	 * @param hitY the param float2
	 * @param hitZ the param float3
	 * @return true, if successful
	 */
	public abstract boolean onActivated(EntityPlayer player, int paramInt, float hitX, float hitY, float hitZ);

	/**
	 * Gets the block dropped.
	 * 
	 * @return the block dropped
	 */
	public abstract ArrayList<Block> getBlockDropped();

	/**
	 * Gets the hardness.
	 * 
	 * @return the hardness
	 */
	public abstract float getHardness();
}
