package xyz.brassgoggledcoders.boilerplate.utils;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.Boilerplate;

public class PositionUtils {

	private static String[] position_pairs = new String[] {"x_y", "x_z", "y_z", "y_x"};

	public static boolean arePositionsAlignedOnTwoAxes(BlockPos first, BlockPos second) {
		for(String pair : position_pairs) {
			String[] split = pair.split("_");
			if((convertStringToPosVal(split[0], first) == convertStringToPosVal(split[0], second))
					&& (convertStringToPosVal(split[1], first) == convertStringToPosVal(split[1], second)))
				return true;
		}

		return false;
	}

	private static int convertStringToPosVal(String pos_name, BlockPos pos) {
		if(pos_name.equalsIgnoreCase("x"))
			return pos.getX();
		else if(pos_name.equalsIgnoreCase("y"))
			return pos.getY();
		else if(pos_name.equalsIgnoreCase("z"))
			return pos.getZ();
		else
			return 0;
	}

	public static boolean isLOSClear(World world, BlockPos first, BlockPos second) {
		Iterator<BlockPos> positions = BlockPos.getAllInBox(first, second).iterator();
		while(positions.hasNext()) {
			BlockPos pos = positions.next();
			// Skip starting positions
			if(pos.equals(first) || pos.equals(second)) {
				Boilerplate.instance.getLogger().devInfo("Skipping " + pos.toString());
				continue;
			}
			Boilerplate.instance.getLogger().devInfo("Checking " + pos.toString());
			if(!world.isAirBlock(pos))
				return false;
		}
		return true;
	}

	public static ArrayList<IBlockState> getBlocksOfTypeNearby(World world, BlockPos pos, IBlockState state) {
		ArrayList<IBlockState> blocks = new ArrayList<IBlockState>();
		for(EnumFacing element : EnumFacing.VALUES) {
			BlockPos off = pos.offset(element);
			if(world.getBlockState(off) == state)
				blocks.add(world.getBlockState(off));
		}
		return blocks;
	}

	public static int getDistanceBetweenPositions(BlockPos clicked_pos, BlockPos saved_pos) {
		return (int) Math.round(clicked_pos.getDistance(saved_pos.getX(), saved_pos.getY(), saved_pos.getZ()));
	}

	public static EnumFacing getFacingFromPositions(BlockPos from, BlockPos to) {
		int x = from.subtract(to).getX();
		int y = from.subtract(to).getY();
		int z = from.subtract(to).getZ();

		if(x > 0) {
			return EnumFacing.EAST;
		}
		else if(x < 0) {
			return EnumFacing.WEST;
		}
		else if(y < 0) {
			return EnumFacing.DOWN;
		}
		else if(y > 0) {
			return EnumFacing.UP;
		}
		else if(z < 0) {
			return EnumFacing.NORTH;
		}
		else if(z > 0) {
			return EnumFacing.SOUTH;
		}
		else {
			// This should never happen!
			return null;
		}
	}
}
