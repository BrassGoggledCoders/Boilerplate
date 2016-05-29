package xyz.brassgoggledcoders.boilerplate.utils;

import cofh.api.energy.IEnergyHandler;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author SkySom
 */
public class ComparatorUtils
{
	public static int scaleStoredEnergyTo(int scale, IEnergyHandler energyHandler)
	{
		return scaleStoredEnergyTo(scale, energyHandler, null);
	}

	public static int scaleStoredEnergyTo(int scale, IEnergyHandler energyHandler, EnumFacing direction)
	{
		return scaleTo(scale, energyHandler.getEnergyStored(direction), energyHandler.getMaxEnergyStored(direction));
	}

	public static int scaleSingleFluidLevelTo(int scale, IFluidHandler fluidHandler)
	{
		return scaleSingleFluidLevelTo(scale, fluidHandler, null);
	}

	public static int scaleSingleFluidLevelTo(int scale, IFluidHandler fluidHandler, EnumFacing direction)
	{
		if (fluidHandler.getTankInfo(direction) != null && fluidHandler.getTankInfo(direction)[0] != null)
		{
			FluidStack fluidStack = fluidHandler.getTankInfo(direction)[0].fluid;
			if (fluidStack != null)
			{
				return scaleTo(scale, fluidStack.amount, fluidHandler.getTankInfo(direction)[0].capacity);
			}
		}
		return 0;
	}

	public static int scaleTo(int scale, int currentLevel, int topLevel)
	{
		return (int) (scale * currentLevel / (float) topLevel);
	}
}
