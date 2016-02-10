
package boilerplate.common.baseclasses.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

import boilerplate.common.utils.Utils;

/**
 * @author warlordjones
 *
 */
public class BlockCustomStairs extends BlockStairs
{
	public BlockCustomStairs(Block block)
	{
		super(block, 0);
		this.setCreativeTab(Utils.getCurrentMod().getCreativeTab());
		this.useNeighborBrightness = true;
	}

	public BlockCustomStairs(Block block, int metadata)
	{
		super(block, metadata);
		this.setCreativeTab(Utils.getCurrentMod().getCreativeTab());
		this.useNeighborBrightness = true;
	}
}
