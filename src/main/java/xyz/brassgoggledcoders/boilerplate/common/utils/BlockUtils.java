/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package xyz.brassgoggledcoders.boilerplate.common.utils;

import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockRailBase.EnumRailDirection;
import net.minecraft.block.state.IBlockState;

/**
 * @author SkySom
 *
 */
public class BlockUtils
{
	public static boolean isRailBlock(IBlockState blockState)
	{
		boolean isRailBlock;
		isRailBlock = blockState.getBlock() instanceof BlockRailBase;
		isRailBlock &= getRailDirection(blockState) != null;
		return isRailBlock;
	}

	public static EnumRailDirection getRailDirection(IBlockState blockState)
	{
		EnumRailDirection railDirection = null;
		if(blockState instanceof BlockRailBase)
		{
			BlockRailBase blockRailBase = (BlockRailBase)blockState.getBlock();
			railDirection = (EnumRailDirection)blockState.getValue(blockRailBase.getShapeProperty());
		}
		return railDirection;
	}
}
