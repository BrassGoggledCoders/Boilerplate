package xyz.brassgoggledcoders.boilerplate.lib.common.containers.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

import xyz.brassgoggledcoders.boilerplate.lib.common.tileentities.IOnSlotChanged;

public class SlotChanged extends Slot
{
	protected IOnSlotChanged iOnSlotChanged;

	public SlotChanged(IInventory iInventory, int slotIndex, int posX, int posY)
	{
		super(iInventory, slotIndex, posX, posY);
		if (iInventory instanceof IOnSlotChanged)
		{
			iOnSlotChanged = (IOnSlotChanged) iInventory;
		}
	}

	@Override
	public void onSlotChanged()
	{
		super.onSlotChanged();
		if (iOnSlotChanged != null)
		{
			iOnSlotChanged.onSlotChanged(this);
		}
	}
}
