package boilerplate.common.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;

/**
 * Created by Skylar on 9/7/2015.
 */
public class BlockUtils
{
	public boolean isRailBlock(Block block)
	{
		return block instanceof BlockRailBase;
	}
}
