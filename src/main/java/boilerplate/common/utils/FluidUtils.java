package boilerplate.common.utils;

import net.minecraft.item.ItemStack;

import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class FluidUtils
{
	public static ItemStack fillFluidContainer(FluidTank tank, ItemStack containerIn)
	{
		if (tank.getFluidAmount() > 0)
		{
			if (FluidContainerRegistry.isEmptyContainer(containerIn))
			{
				ItemStack filledContainer = FluidContainerRegistry.fillFluidContainer(tank.getFluid(), containerIn);
				if (filledContainer != null)
				{
					FluidStack fs = FluidContainerRegistry.getFluidForFilledItem(filledContainer);
					if ((fs.amount <= tank.getFluidAmount()))
					{
						tank.drain(fs.amount, true);

						return filledContainer;
					}
				}
			}
		}
		return null;
	}

	public static ItemStack drainFluidContainer(FluidTank tank, ItemStack containerIn)
	{
		if (FluidContainerRegistry.isFilledContainer(containerIn))
		{
			FluidStack fs = FluidContainerRegistry.getFluidForFilledItem(containerIn);
			if ((fs != null) && (tank.getFluidAmount() + fs.amount <= tank.getCapacity()))
			{
				ItemStack emptyContainer = FluidContainerRegistry.drainFluidContainer(containerIn);
				if ((emptyContainer != null) && (tank.fill(fs, true) == fs.amount))
					return emptyContainer;
			}
		}
		return null;
	}
}
