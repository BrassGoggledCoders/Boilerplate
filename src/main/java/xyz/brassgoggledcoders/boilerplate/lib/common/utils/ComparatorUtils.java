package xyz.brassgoggledcoders.boilerplate.lib.common.utils;

import cofh.api.energy.IEnergyHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

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

	public static int scaleIItemHandlerLevelTo(int scale, IItemHandler itemHandler)
	{
		if(itemHandler != null)
		{
			int numItemStacks = 0;
			float redstoneLevel = 0.0F;

			for (int slotNum = 0; slotNum < itemHandler.getSlots(); ++slotNum)
			{
				ItemStack itemstack = itemHandler.getStackInSlot(slotNum);

				if (itemstack != null)
				{
					redstoneLevel += (float)itemstack.stackSize / (float) itemstack.getMaxStackSize();
					++numItemStacks;
				}
			}
			redstoneLevel = redstoneLevel / (float)itemHandler.getSlots();
			return MathHelper.floor_float(redstoneLevel * (float)scale) + (numItemStacks > 0 ? 1 : 0);
		}
		return 0;
	}

	public static int scaleTo(int scale, int currentLevel, int topLevel)
	{
		return (int) (scale * currentLevel / (float) topLevel);
	}
}
