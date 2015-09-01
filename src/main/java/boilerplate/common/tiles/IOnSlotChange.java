package boilerplate.common.tiles;

import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Skylar on 8/31/2015.
 */
public interface IOnSlotChange
{
	void onSlotChange(Slot slot, ItemStack itemStack, ItemStack itemStack2);
}
