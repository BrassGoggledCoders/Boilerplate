
package boilerplate.api;

import cofh.api.energy.IEnergyContainerItem;

/**
 * The Interface IEnergyItem. Just a wrapper for {@link IEnergyContainerItem}
 *
 * @author warlordjones
 */
public interface IEnergyItem extends IEnergyContainerItem
{

	/**
	 * Gets the max send.
	 *
	 * @return the max send
	 */
	public short getMaxSend();
}
