package xyz.brassgoggledcoders.boilerplate.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import xyz.brassgoggledcoders.boilerplate.tileentities.BaseTileWithInventory;

public class BaseContainer extends Container
{
	private BaseTileWithInventory baseTile;

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return this.baseTile.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i)
	{
		Slot slot = this.getSlot(i);

		if ((slot != null) && slot.getHasStack())
		{
			ItemStack itemstack = slot.getStack();
			ItemStack result = itemstack.copy();

			if (i >= 36)
			{
				if (!this.mergeItemStack(itemstack, 0, 36, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack, 36, 36 + this.getTile().getSizeInventory(), false))
			{
				return null;
			}

			if (itemstack.stackSize == 0)
			{
				slot.putStack(null);
			}
			else
			{
				slot.onSlotChanged();
			}
			slot.onPickupFromSlot(player, itemstack);
			return result;
		}
		return null;
	}

	public BaseTileWithInventory getTile()
	{
		return this.baseTile;
	}

	public void setTile(BaseTileWithInventory tileent)
	{
		this.baseTile = tileent;
	}
}
