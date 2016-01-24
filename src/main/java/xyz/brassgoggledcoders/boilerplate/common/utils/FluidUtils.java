package xyz.brassgoggledcoders.boilerplate.common.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import net.minecraft.world.World;
import net.minecraftforge.fluids.*;

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

	public static boolean fillFluidHandlerWithPlayerItem(World world, IFluidHandler handler, EntityPlayer player)
	{
		ItemStack equipped = player.getCurrentEquippedItem();

		if(equipped == null)
		{
			return false;
		}

		FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(equipped);
		if(fluid != null)
		{
			if(handler.fill(null, fluid, false) == fluid.amount || player.capabilities.isCreativeMode)
			{
				if(world.isRemote)
				{
					return true;
				}

				ItemStack filledStack = FluidContainerRegistry.drainFluidContainer(equipped);
				if(!player.capabilities.isCreativeMode)
				{
					if(equipped.stackSize==1)
					{
						player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
						player.inventory.addItemStackToInventory(filledStack);
					}
					else
					{
						equipped.stackSize -= 1;
						if(filledStack != null && !player.inventory.addItemStackToInventory(filledStack))
						{
							player.dropItem(filledStack, false, true);
						}
					}
					player.openContainer.detectAndSendChanges();
					if(player instanceof  EntityPlayerMP)
					{
						((EntityPlayerMP) player).updateCraftingInventory(player.openContainer, player.openContainer.getInventory());
					}
				}
				handler.fill(null, fluid, true);
				return true;
			}
		}
		else if(equipped.getItem() instanceof IFluidContainerItem)
		{
			IFluidContainerItem container = (IFluidContainerItem)equipped.getItem();
			fluid = container.getFluid(equipped);
			if(handler.fill(null, fluid, false)>0)
			{
				if(world.isRemote)
					return true;

				int fill = handler.fill(null, fluid, true);
				if(equipped.stackSize > 1)
				{
					ItemStack emptied = ItemStackUtils.copyStackWithAmount(equipped, 1);
					equipped.stackSize -= 1;
					container.drain(emptied, fill, true);
					if(!player.inventory.addItemStackToInventory(emptied))
					{
						player.dropItem(emptied, false, true);
					}
				}
				else
				{
					container.drain(equipped, fill, true);
				}
				player.openContainer.detectAndSendChanges();
				if(player instanceof EntityPlayerMP)
				{
					((EntityPlayerMP)player).updateCraftingInventory(player.openContainer, player.openContainer.getInventory());
				}
				return true;
			}
		}
		return false;
	}
}
