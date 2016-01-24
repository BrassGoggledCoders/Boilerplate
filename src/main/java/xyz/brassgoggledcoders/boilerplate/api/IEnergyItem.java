/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package xyz.brassgoggledcoders.boilerplate.api;

//import cofh.api.energy.IEnergyContainerItem;

/**
 * The Interface IEnergyItem. Just a wrapper for {@link IEnergyContainerItem}
 *
 * @author warlordjones
 * TODO: RF Stuff
 */
public interface IEnergyItem //extends IEnergyContainerItem
{

	/**
	 * Gets the max send.
	 *
	 * @return the max send
	 */
	public short getMaxSend();
}
