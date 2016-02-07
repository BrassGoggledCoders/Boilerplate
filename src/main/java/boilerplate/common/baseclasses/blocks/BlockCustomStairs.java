
package boilerplate.common.baseclasses.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

import boilerplate.common.IBoilerplateMod;

/**
 * @author warlordjones
 *
 */
public class BlockCustomStairs extends BlockStairs
{
	public BlockCustomStairs(Block block, IBoilerplateMod mod)
	{
		super(block, 0);
		this.setCreativeTab(mod.getCreativeTab());
		this.useNeighborBrightness = true;
	}

	public BlockCustomStairs(Block block, int metadata, IBoilerplateMod mod)
	{
		super(block, metadata);
		this.setCreativeTab(mod.getCreativeTab());
		this.useNeighborBrightness = true;
	}
}
