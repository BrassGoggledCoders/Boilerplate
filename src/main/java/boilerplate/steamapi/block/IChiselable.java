/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.steamapi.block;

import net.minecraft.block.Block;

// TODO: Auto-generated Javadoc
/**
 * The Interface IChiselable.
 *
 * @author warlordjones
 */
public interface IChiselable
{
	
	/**
	 * Gets the chiseled variant.
	 *
	 * @return the block created from chiseling
	 */
	public Block getChiseledVariant();

	/**
	 * Gets the chiseled variant meta.
	 *
	 * @return the metadata of the block created from chiseling
	 */
	public int getChiseledVariantMeta();
}
