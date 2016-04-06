package xyz.brassgoggledcoders.boilerplate.lib.common.tileentities;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fluids.*;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.Selectors;

import java.util.List;

public abstract class TileEntityFluidBase extends TileEntityBase implements IFluidHandler
{
	private FluidTank tank;

	public TileEntityFluidBase()
	{
		tank = new FluidTank(getCapacity());
	}

	public abstract int getCapacity();

	public abstract int getTransferRate();

	private boolean pullFluidFrom(EnumFacing enumFacing)
	{
		IFluidHandler fluidHandler = this.getFluidHandlerForTransfer(EnumFacing.UP);
		boolean dirty = false;
		if(getTank().getFluidAmount() < getTank().getCapacity())
		{
			if(fluidHandler != null)
			{
				if(getTank().getFluidAmount() > 0)
				{
					FluidStack canPull = getTank().getFluid().copy();
					canPull.amount = getTank().getCapacity() - getTank().getFluidAmount();
					canPull.amount = Math.min(canPull.amount, getTransferRate());
					FluidStack drained = fluidHandler.drain(EnumFacing.DOWN, canPull, true);
					if(drained != null && drained.amount > 0)
					{
						getTank().fill(drained, true);
						dirty = true;
					}
				} else
				{
					FluidTankInfo[] infos = fluidHandler.getTankInfo(EnumFacing.DOWN);
					if(infos != null)
					{
						for (FluidTankInfo info : infos) {
							if(info.fluid != null && info.fluid.amount > 0)
							{
								if(canFill(EnumFacing.UP, info.fluid.getFluid()))
								{
									FluidStack canPull = info.fluid.copy();
									canPull.amount = Math.min(getTransferRate(), canPull.amount);
									FluidStack drained = fluidHandler.drain(EnumFacing.DOWN, canPull, true);
									if(drained != null && drained.amount > 0)
									{
										getTank().fill(drained, true);
										dirty = true;
									}
								}
							}
						}
					}
				}
			}
		}

		return dirty;
	}

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

	public IFluidHandler getFluidHandlerForTransfer(EnumFacing enumfacing)
	{
		return getFluidHandlerAtPosition(this.getWorld(), getPos().offset(enumfacing));
	}

	public IFluidHandler getFluidHandlerAtPosition(World world, BlockPos blockPos)
	{
		IFluidHandler fluidHandler = null;
		TileEntity tileEntity = world.getTileEntity(blockPos);

		if (tileEntity != null)
		{
			if (tileEntity instanceof IFluidHandler)
			{
				fluidHandler = (IFluidHandler)tileEntity;
			}
		}

		if (fluidHandler == null)
		{
			float sensitivity = 0.49f;
			List<Entity> list = world.getEntitiesInAABBexcluding(null, AxisAlignedBB.fromBounds(
					blockPos.getX() + sensitivity, blockPos.getY() + sensitivity, blockPos.getZ() + sensitivity,
					blockPos.getX() + 1 - sensitivity, blockPos.getY() + 1 - sensitivity, blockPos.getZ() + 1 - sensitivity),
					Selectors.IFLUID_HANDLER_ENTITIES);

			if (list.size() > 0)
			{
				fluidHandler = (IFluidHandler)list.get(world.rand.nextInt(list.size()));
			}
		}

		return fluidHandler;
	}
}
