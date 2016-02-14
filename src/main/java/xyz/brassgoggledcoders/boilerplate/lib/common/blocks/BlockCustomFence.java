
package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;

import xyz.brassgoggledcoders.boilerplate.lib.common.utils.Utils;

/**
 * @author warlordjones
 *
 */
public class BlockCustomFence extends BlockFence
{
	public BlockCustomFence(Material mat)
	{
		super(/* Utils.getCurrentMod().getPrefix() + type, */ mat);
		this.setCreativeTab(Utils.getCurrentMod().getCreativeTab());
	}
}
