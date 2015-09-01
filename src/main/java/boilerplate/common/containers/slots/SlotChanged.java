package boilerplate.common.containers.slots;

import boilerplate.common.tiles.IOnSlotChange;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Skylar on 8/31/2015.
 */
public class SlotChanged extends Slot
{
	protected IOnSlotChange iOnSlotChange;

	public SlotChanged(IInventory iInventory, int slotIndex, int posX, int posY)
	{
		super(iInventory, slotIndex, posX, posY);
		if (iInventory instanceof IOnSlotChange)
		{
			iOnSlotChange = (IOnSlotChange)iInventory;
		}
	}

	@Override
	public void onSlotChange(ItemStack itemStack, ItemStack itemStack2)
	{
		if (iOnSlotChange != null)
		{
			iOnSlotChange.onSlotChange(this, itemStack, itemStack2);
		}
		super.onSlotChange(itemStack, itemStack2);
	}
}
