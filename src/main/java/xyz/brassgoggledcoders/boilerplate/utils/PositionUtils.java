package xyz.brassgoggledcoders.boilerplate.utils;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLLog;

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
}
