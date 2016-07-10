package xyz.brassgoggledcoders.boilerplate.utils;

import java.util.Iterator;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import xyz.brassgoggledcoders.boilerplate.Boilerplate;

public class PositionUtils {

	private static String[] position_pairs = new String[] {"x_y", "x_z", "y_z", "y_x"};

	public static boolean arePositionsAlignedOnTwoAxes(BlockPos first, BlockPos second) {
		for(String pair : position_pairs) {
			String[] split = pair.split("_");
			FMLLog.warning(split[0], "");
			FMLLog.warning(split[1], "");
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
}
