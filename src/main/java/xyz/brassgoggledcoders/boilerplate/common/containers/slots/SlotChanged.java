package xyz.brassgoggledcoders.boilerplate.common.containers.slots;

import xyz.brassgoggledcoders.boilerplate.common.tileentities.IOnSlotChanged;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

/**
 * Created by Skylar on 8/31/2015.
 */
public class SlotChanged extends Slot
{
	protected IOnSlotChanged iOnSlotChanged;

	public SlotChanged(IInventory iInventory, int slotIndex, int posX, int posY)
	{
		super(iInventory, slotIndex, posX, posY);
		if (iInventory instanceof IOnSlotChanged)
		{
			iOnSlotChanged = (IOnSlotChanged)iInventory;
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
