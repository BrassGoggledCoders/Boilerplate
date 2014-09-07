package boilerplate.common.baseclasses;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class BaseContainer extends Container
{
	private static BaseTileWithInventory tileent;

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return true;
	}
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
	  Slot slot = getSlot(i);

	  if(slot != null && slot.getHasStack()) {
	    ItemStack itemstack = slot.getStack();
	    ItemStack result = itemstack.copy();

	    if(i >= 36) {
	      if(!mergeItemStack(itemstack, 0, 36, false)) {
	        return null;
	      }
	    } else if(!mergeItemStack(itemstack, 36, 36 + BaseContainer.getTile().getSizeInventory(), false)) {
	      return null;
	    }

	    if(itemstack.stackSize == 0) {
	      slot.putStack(null);
	    } else {
	      slot.onSlotChanged();
	    }
	    slot.onPickupFromSlot(player, itemstack);
	    return result;
	  }
	  return null;
	}
	public static BaseTileWithInventory getTile()
	{
		return tileent;
	}
	public static void setTile(BaseTileWithInventory tileent)
	{
		BaseContainer.tileent = tileent;
	}
}
