
package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;

/**
 * @author warlordjones
 *
 */
public class BlockCustomStairs extends BlockStairs
{
	public BlockCustomStairs(Block block)
	{
		super(block.getDefaultState());
		this.setCreativeTab(BoilerplateLib.getInstance().mod.getCreativeTab());
		this.useNeighborBrightness = true;
	}
}
