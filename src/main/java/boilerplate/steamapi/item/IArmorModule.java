/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.steamapi.item;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author warlordjones
 *
 */
public interface IArmorModule
{
	// 0 is helmet, 1 is plate, 2 is legs and 3 is boots. Return -1 for any piece
	public int getApplicablePiece();

	//Localized Name
	public String getName();

	//Unlocalized Name
	public String getModuleId();

	//The Effect this module has
	public boolean applyArmorEffect(World world, EntityPlayer player, ItemStack stack);

	//The type of effect (see below)
	public EnumArmorEffectType getArmorEffectType();

	//NYI
	public ArrayList<IArmorModule> getListOfIncompatibleModules();

	//
	public static enum EnumArmorEffectType
	{
		ONTICK,
		/*DEFENSIVE,*/
		HUD
	}
	/**	Whenever applyArmorEffect returns true, this amount of steam will be consumed from canisters in the player's inventory.
	* 	The effect will not be run if the player does not have enough steam.
	*/
	public int getSteamConsumedOnEffect();

	//NYI
	public int getEnergyConsumedOnEffect();
}
