/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.steamapi.item;

// TODO: Auto-generated Javadoc
/**
 * The Interface IDefensiveArmorModule.
 *
 * @author warlordjones
 */
public interface IDefensiveArmorModule extends IArmorModule
{
	// ALWAYS return DEFENSIVE as the module type when using this interface!!

	/**
	 * Gets the max damage absorb.
	 *
	 * @return the max damage absorb
	 */
	public int getMaxDamageAbsorb();

	/**
	 * Gets the damage absorb ratio.
	 *
	 * @return the damage absorb ratio
	 */
	public int getDamageAbsorbRatio();

	/**
	 * Gets the armor to display.
	 *
	 * @return the armor to display
	 */
	public int getArmorToDisplay();

}
