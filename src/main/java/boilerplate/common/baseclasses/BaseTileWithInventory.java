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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import boilerplate.steamapi.IUniversallyWrenchable;

/**
 * Basic machine class.Every machine that has an inventory should extend this.
 *
 * @author Decebaldecebal
 *
 */
public abstract class BaseTileWithInventory extends TileEntity implements ISidedInventory, IUniversallyWrenchable
{
	public ItemStack[] inventory;

	public BaseTileWithInventory(byte invSize)
	{
		inventory = new ItemStack[invSize];
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

		NBTTagList nbttaglist = (NBTTagList) tag.getTag("Items");
		inventory = new ItemStack[getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if ((b0 >= 0) && (b0 < inventory.length))
				inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < inventory.length; ++i)
			if (inventory[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}

		tag.setTag("Items", nbttaglist);
	}

	@Override
	public int getSizeInventory()
	{
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return inventory[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (inventory[par1] != null)
		{
			ItemStack var3;

			if (inventory[par1].stackSize <= par2)
			{
				var3 = inventory[par1];
				inventory[par1] = null;
				return var3;
			}
			else
			{
				var3 = inventory[par1].splitStack(par2);

				if (inventory[par1].stackSize == 0)
					inventory[par1] = null;

				return var3;
			}
		}
		else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (inventory[par1] != null)
		{
			ItemStack var2 = inventory[par1];
			inventory[par1] = null;
			return var2;
		}
		else
			return null;
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		inventory[par1] = par2ItemStack;

		if ((par2ItemStack != null) && (par2ItemStack.stackSize > getInventoryStackLimit()))
			par2ItemStack.stackSize = getInventoryStackLimit();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1)
	{
		return null;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j)
	{
		return false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j)
	{
		return false;
	}

	@Override
	public String getInventoryName()
	{
		return "";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return true;
	}

	@Override
	public void openInventory()
	{
	}

	@Override
	public void closeInventory()
	{
	}
	//TODO
	@Override
	public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int side)
	{
		return false;
	}

	@Override
	public short getFacing()
	{
		return 0;
	}

	@Override
	public void setFacing(short facing)
	{

	}

	@Override
	public boolean wrenchCanRemove(EntityPlayer entityPlayer)
	{
		return true;
	}

	@Override
	public float getWrenchDropRate()
	{
		return 1;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer)
	{
		return new ItemStack(this.getBlockType());
	}

	@Override
	public ItemStack dismantleBlock(EntityPlayer player, World world, int x, int y, int z, boolean returnBlock)
	{
		return new ItemStack(this.getBlockType());
	}

	@Override
	public boolean canDismantle(EntityPlayer player, World world, int x, int y, int z)
	{
		return true;
	}
}
