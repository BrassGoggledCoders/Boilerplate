package xyz.brassgoggledcoders.boilerplate.lib.common.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.*;

public abstract class TileEntityFluidBase extends TileEntityBase implements IFluidHandler
{
	private FluidTank tank;

	public TileEntityFluidBase()
	{
		tank = new FluidTank(getCapacity());
	}

	public abstract int getCapacity();

	@Override
	public int fill(EnumFacing from, FluidStack resource, boolean doFill)
	{
		return getTank().fill(resource, doFill);
	}

	@Override
	public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain)
	{
		if (resource == null || !resource.isFluidEqual(getTank().getFluid()))
		{
			return null;
		}
		return getTank().drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain)
	{
		return getTank().drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(EnumFacing from, Fluid fluid)
	{
		return true;
	}

	@Override
	public boolean canDrain(EnumFacing from, Fluid fluid)
	{
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(EnumFacing from)
	{
		return new FluidTankInfo[] { getTank().getInfo() };
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound)
	{
		super.readFromNBT(nbtTagCompound);
		if(getTank() == null)
		{
			tank = new FluidTank(getCapacity());
		}
		getTank().readFromNBT(nbtTagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound)
	{
		super.writeToNBT(nbtTagCompound);
		getTank().writeToNBT(nbtTagCompound);
	}

	public FluidTank getTank()
	{
		return tank;
	}
}
