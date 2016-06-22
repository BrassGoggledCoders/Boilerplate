/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 */
package xyz.brassgoggledcoders.boilerplate.utils;

import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockRailBase.EnumRailDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author SkySom
 */
public class BlockUtils {
	public static boolean isRailBlock(IBlockState blockState) {
		boolean isRailBlock;
		isRailBlock = blockState.getBlock() instanceof BlockRailBase;
		isRailBlock &= getRailDirection(blockState) != null;
		return isRailBlock;
	}

	public static EnumRailDirection getRailDirection(IBlockState blockState) {
		EnumRailDirection railDirection = null;
		if(blockState.getBlock() instanceof BlockRailBase) {
			BlockRailBase blockRailBase = (BlockRailBase) blockState.getBlock();
			railDirection = blockState.getValue(blockRailBase.getShapeProperty());
		}
		return railDirection;
	}

	public static boolean canPlayerBreakBlock(World world, EntityPlayer player, BlockPos blockPos) {
		return !getBlockUnbreakable(world, blockPos) && player.capabilities.allowEdit;
	}

	public static boolean getBlockUnbreakable(World world, BlockPos blockPos) {
		IBlockState blockState = world.getBlockState(blockPos);
		return blockState != null && blockState.getBlock().getBlockHardness(blockState, world, blockPos) == -1;
	}
}
