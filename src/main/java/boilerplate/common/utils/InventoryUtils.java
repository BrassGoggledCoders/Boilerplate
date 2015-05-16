/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.common.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author Surseance
 *
 */
public class InventoryUtils
{
	public static InventoryEnderChest getPlayerEnderChest(EntityPlayer p)
	{
		return p.getInventoryEnderChest();
	}

	public static boolean addItemStackToInventory(IInventory inv, ItemStack is)
	{
		if (!is.isItemDamaged())
		{
			int stackSize;
			do
			{
				stackSize = is.stackSize;
				is.stackSize = storePartially(inv, is);
			} while ((is.stackSize > 0) && (is.stackSize < stackSize));

			return is.stackSize < stackSize;
		}

		int slot = getFirstEmptySlot(inv, is);

		if (slot >= 0)
		{
			inv.setInventorySlotContents(slot, ItemStack.copyItemStack(is));
			is.stackSize = 0;
			return true;
		}

		return false;
	}

	public static int storePartially(IInventory inv, ItemStack is)
	{
		Item item = is.getItem();
		int size = is.stackSize;

		if (is.getMaxStackSize() == 1) // Not stackable
		{
			int freeSlot = getFirstEmptySlot(inv, is);

			if (freeSlot < 0)
			{
				return size;
			}

			if (inv.getStackInSlot(freeSlot) == null)
			{
				inv.setInventorySlotContents(freeSlot, ItemStack.copyItemStack(is));
			}

			return 0;
		}

		int freeSlot = getNonFilledStack(inv, is);

		if (freeSlot < 0)
		{
			freeSlot = getFirstEmptySlot(inv, is);
		}
		if (freeSlot < 0)
		{
			return size;
		}

		if (inv.getStackInSlot(freeSlot) == null)
		{
			inv.setInventorySlotContents(freeSlot, new ItemStack(item, 0, is.getItemDamage()));
		}

		int canStore = size;

		if (canStore > (inv.getStackInSlot(freeSlot).getMaxStackSize() - inv.getStackInSlot(freeSlot).stackSize))
		{
			canStore = inv.getStackInSlot(freeSlot).getMaxStackSize() - inv.getStackInSlot(freeSlot).stackSize;
		}
		if (canStore > (inv.getInventoryStackLimit() - inv.getStackInSlot(freeSlot).stackSize))
		{
			canStore = inv.getInventoryStackLimit() - inv.getStackInSlot(freeSlot).stackSize;
		}

		if (canStore == 0)
		{
			return size;
		}
		else
		{
			size -= canStore;
			inv.getStackInSlot(freeSlot).stackSize += canStore;
			return size;
		}
	}

	public static int getNonFilledStack(IInventory inv, ItemStack is)
	{
		for (int slot = 0; slot < inv.getSizeInventory(); slot++)
		{
			ItemStack stackInSlot = inv.getStackInSlot(slot);

			if ((stackInSlot != null) && (stackInSlot.getItem() == is.getItem()) && stackInSlot.isStackable()
					&& (stackInSlot.stackSize < stackInSlot.getMaxStackSize()) && (stackInSlot.stackSize < inv.getInventoryStackLimit())
					&& (!stackInSlot.getHasSubtypes() || (stackInSlot.getItemDamage() == is.getItemDamage())))
			{
				return slot;
			}
		}

		return -1;
	}

	public static int getFirstEmptySlot(IInventory inv, ItemStack is)
	{
		for (int slot = 0; slot < inv.getSizeInventory(); slot++)
		{
			if (inv.getStackInSlot(slot) == null)
			{
				return slot;
			}
		}

		return -1;
	}

	public static int isInPlayerInventory(EntityPlayer player, Item item)
	{
		for (int slot = 0; slot < player.inventory.mainInventory.length; slot++)
		{
			if ((player.inventory.mainInventory[slot] != null) && (player.inventory.mainInventory[slot].getItem() == item))
			{
				return slot;
			}
		}

		return -1;
	}

	public static boolean isInvEmpty(IInventory inv, ItemStack is)
	{
		return addItemStackToInventory(inv, is);
	}

	public static int isInInventory(InventoryBasic inventory, ItemStack stack)
	{
		for (int slot = 0; slot < inventory.getSizeInventory(); slot++)
		{
			if ((inventory.getStackInSlot(slot) != null) && (inventory.getStackInSlot(slot) == stack))
			{
				return slot;
			}
		}

		return -1;
	}

	public static ItemStack getItemStackInInventory(EntityPlayer player, ItemStack stack)
	{
		for (ItemStack element : player.inventory.mainInventory)
		{
			if (element == stack)
			{
				return element;
			}
		}
		return null;
	}
}
