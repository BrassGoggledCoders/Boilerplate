/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.common.utils.interfaceimpl;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * @author SkySom
 */
public class IInventoryImpl implements IInventory
{
	private ItemStack[] inventoryItemStacks;
	private String inventoryName;

	public IInventoryImpl(int inventorySize, String inventoryName)
	{
		this.setInventoryItemStacks(new ItemStack[inventorySize]);
		this.inventoryName = inventoryName;
	}

	@Override
	public int getSizeInventory()
	{
		return this.getInventoryItemStacks().length;
	}

	@Override
	public ItemStack getStackInSlot(int slotIndex)
	{
		return this.getInventoryItemStacks()[slotIndex];
	}

	@Override
	public ItemStack decrStackSize(int slotIndex, int decreaseAmount)
	{
		if (this.getInventoryItemStacks()[slotIndex] != null)
		{
			ItemStack itemStack;

			if (this.getInventoryItemStacks()[slotIndex].stackSize <= decreaseAmount)
			{
				itemStack = this.getInventoryItemStacks()[slotIndex];
				this.getInventoryItemStacks()[slotIndex] = null;
				return itemStack;
			}
			else
			{
				itemStack = this.getInventoryItemStacks()[slotIndex].splitStack(decreaseAmount);

				if (this.getInventoryItemStacks()[slotIndex].stackSize == 0)
				{
					this.getInventoryItemStacks()[slotIndex] = null;
				}

				return itemStack;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex)
	{
		if (this.getInventoryItemStacks()[slotIndex] != null)
		{
			ItemStack var2 = this.getInventoryItemStacks()[slotIndex];
			this.getInventoryItemStacks()[slotIndex] = null;
			return var2;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
	{
		this.getInventoryItemStacks()[slotIndex] = itemStack;

		if ((itemStack != null) && (itemStack.stackSize > this.getInventoryStackLimit()))
		{
			itemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName()
	{
		return this.inventoryName;
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return true;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public void markDirty()
	{

	}

	/*
	 * Probably just better to not call this in here.
	 */
	@Override
	public boolean isUseableByPlayer(EntityPlayer entityPlayer)
	{
		return false;
	}

	@Override
	public void openInventory()
	{

	}

	@Override
	public void closeInventory()
	{

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack)
	{
		return slot < this.getSizeInventory() && itemStack != null;
	}

	public void readFromNBT(NBTTagCompound tag)
	{
		NBTTagList nbttaglist = (NBTTagList) tag.getTag("Items");
		this.setInventoryItemStacks(new ItemStack[this.getSizeInventory()]);

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if ((b0 >= 0) && (b0 < this.getSizeInventory()))
			{
				this.getInventoryItemStacks()[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.getSizeInventory(); ++i)
		{
			if (this.getStackInSlot(i) != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.getStackInSlot(i).writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		tag.setTag("Items", nbttaglist);
	}


	public ItemStack[] getInventoryItemStacks()
	{
		return inventoryItemStacks;
	}

	public void setInventoryItemStacks(ItemStack[] inventoryItemStacks)
	{
		this.inventoryItemStacks = inventoryItemStacks;
	}
}
