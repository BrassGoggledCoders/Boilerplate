package boilerplate.steamapi.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IPoweredModuleHelper
{
	int steamToConsume = 0;
	int rfToConsume = 0;

	public void setSteamToConsume(int steamToSet);

	public void setRFToConsume(int rfToSet);

	/**
	 * Call this from applyModuleEffect to consume steam/RF
	 * 
	 * @param player
	 * @param stack
	 * @return
	 */
	public boolean doConsumption(EntityPlayer player, ItemStack stack);
}
