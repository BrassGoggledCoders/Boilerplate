package boilerplate.steamapi.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IModule
{
	// Localized Name
	public String getName();

	// Unlocalized Name
	public String getModuleId();

	// The Effect this module has
	public boolean applyModuleEffect(World world, EntityPlayer player, ItemStack stack);

	/**
	 * Whenever applyArmorEffect returns true, this amount of steam will be
	 * consumed from canisters in the player's inventory. The effect will not be
	 * run if the player does not have enough steam.
	 */
	public int getSteamConsumedOnEffect();

	/**
	 * Whenever applyArmorEffect returns true, this amount of RF will be
	 * consumed from electric storage items in the player's inventory. The
	 * effect will not be run if the player does not have enough RF.
	 */
	public int getEnergyConsumedOnEffect();
}
