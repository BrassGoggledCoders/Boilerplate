/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.steamapi.item;

/**
 * @author warlordjones
 * 
 */
public interface IArmorModule extends IModule
{
	// 0 is helmet, 1 is plate, 2 is legs and 3 is boots. Return -1 for any
	// piece
	public int getApplicablePiece();

	// The type of effect (see below)
	public EnumArmorEffectType getArmorEffectType();

	//
	public static enum EnumArmorEffectType
	{
		ONTICK, DEFENSIVE, HUD, /* SC2 Use Only */SPECIAL
	}

	/**
	 * 40 Weight = One level of slowness applied to the player. Doesn't work :P
	 */
	public int getModuleWeight();
}
