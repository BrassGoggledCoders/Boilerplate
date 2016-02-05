
package boilerplate.common.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;

/**
 * @author SkySom
 *
 */
public class BlockUtils
{
	public static boolean isRailBlock(Block block)
	{
		return block instanceof BlockRailBase;
	}
}
