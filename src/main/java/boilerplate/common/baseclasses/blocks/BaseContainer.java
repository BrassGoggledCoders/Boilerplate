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
package boilerplate.common.baseclasses.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import boilerplate.common.baseclasses.BaseTileWithInventory;

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
