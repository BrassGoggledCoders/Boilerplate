package xyz.brassgoggledcoders.boilerplate.lib.common.items;

import cofh.api.energy.IEnergyContainerItem;

//import cofh.api.energy.IEnergyContainerItem;

/**
 * The Interface IEnergyItem. Just a wrapper for {@link IEnergyContainerItem}
 *
 * @author warlordjones TODO: RF Stuff
 */
public interface IEnergyItem extends IEnergyContainerItem
{

	/**
	 * Gets the max send.
	 *
	 * @return the max send
	 */
	short getMaxSend();
}
