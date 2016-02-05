
package boilerplate.common.utils;

import cofh.api.energy.IEnergyHandler;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author SkySom
 */
public class ComparatorUtils
{
	public static int scaleStoredEnergyTo(int scale, IEnergyHandler energyHandler)
	{
		return scaleStoredEnergyTo(scale, energyHandler, ForgeDirection.UNKNOWN);
	}

	public static int scaleStoredEnergyTo(int scale, IEnergyHandler energyHandler, ForgeDirection direction)
	{
		return scaleTo(scale, energyHandler.getEnergyStored(direction), energyHandler.getMaxEnergyStored(direction));
	}

	public static int scaleSingleFluidLevelTo(int scale, IFluidHandler fluidHandler)
	{
		return scaleSingleFluidLevelTo(scale, fluidHandler, ForgeDirection.UNKNOWN);
	}

	public static int scaleSingleFluidLevelTo(int scale, IFluidHandler fluidHandler, ForgeDirection direction)
	{
		if(fluidHandler.getTankInfo(direction) != null && fluidHandler.getTankInfo(direction)[0] != null)
		{
			FluidStack fluidStack = fluidHandler.getTankInfo(direction)[0].fluid;
			if(fluidStack != null)
			{
				return scaleTo(scale, fluidStack.amount, fluidHandler.getTankInfo(direction)[0].capacity);
			}
		}
		return 0;
	}

	public static int scaleTo(int scale, int currentLevel, int topLevel)
	{
		return (int)(scale * currentLevel / (float)topLevel);
	}
}
