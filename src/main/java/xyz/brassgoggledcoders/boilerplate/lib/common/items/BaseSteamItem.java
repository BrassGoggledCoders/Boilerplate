package xyz.brassgoggledcoders.boilerplate.lib.common.items;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;

/**
 * @author Skylar on 9/2/2015.
 */
public class BaseSteamItem extends ItemBase implements IFluidContainerItem
{
	// TODO: Render Stuff
	public int maxSteam;

	public BaseSteamItem(String texturePath, String name, int maxSteam)
	{
		super(texturePath, name);
		this.maxSteam = maxSteam;
	}

	// Returns True if can the amount is consumed
	protected boolean consumeSteamFromCanister(ItemStack container, int amount)
	{
		boolean canConsume = false;
		if (container != null && container.getItem() instanceof IFluidContainerItem)
		{
			IFluidContainerItem iFluidContainerItem = (IFluidContainerItem) container.getItem();
			if (iFluidContainerItem.getFluid(container).amount > amount)
			{
				iFluidContainerItem.drain(container, amount, true);
				canConsume = true;
			}
		}
		return canConsume;
	}

	@Override
	public FluidStack getFluid(ItemStack container)
	{
		if ((container.getTagCompound() == null) || !container.getTagCompound().hasKey("Fluid"))
			return null;
		return FluidStack.loadFluidStackFromNBT(container.getTagCompound().getCompoundTag("Fluid"));
	}

	@Override
	public int getCapacity(ItemStack container)
	{
		return this.maxSteam;
	}

	@Override
	public int fill(ItemStack container, FluidStack resource, boolean doFill)
	{
		if (resource == null)
			return 0;

		if (!doFill)
		{
			if ((container.getTagCompound() == null) || !container.getTagCompound().hasKey("Fluid"))
				return Math.min(this.maxSteam, resource.amount);

			FluidStack stack = FluidStack.loadFluidStackFromNBT(container.getTagCompound().getCompoundTag("Fluid"));

			if (stack == null)
				return Math.min(this.maxSteam, resource.amount);

			if (!stack.isFluidEqual(resource))
				return 0;

			return Math.min(this.maxSteam - stack.amount, resource.amount);
		}

		if (container.getTagCompound() == null)
			container.setTagCompound(new NBTTagCompound());

		if (!container.getTagCompound().hasKey("Fluid"))
		{
			NBTTagCompound fluidTag = resource.writeToNBT(new NBTTagCompound());

			if (this.maxSteam < resource.amount)
			{
				fluidTag.setInteger("Amount", this.maxSteam);
				container.getTagCompound().setTag("Fluid", fluidTag);
				return this.maxSteam;
			}

			container.getTagCompound().setTag("Fluid", fluidTag);
			return resource.amount;
		}

		NBTTagCompound fluidTag = container.getTagCompound().getCompoundTag("Fluid");
		FluidStack stack = FluidStack.loadFluidStackFromNBT(fluidTag);

		if (!stack.isFluidEqual(resource))
			return 0;

		int filled = this.maxSteam - stack.amount;
		if (resource.amount < filled)
		{
			stack.amount += resource.amount;
			filled = resource.amount;
		}
		else
			stack.amount = this.maxSteam;

		container.getTagCompound().setTag("Fluid", stack.writeToNBT(fluidTag));

		return filled;
	}

	@Override
	public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain)
	{
		if ((container.getTagCompound() == null) || !container.getTagCompound().hasKey("Fluid"))
			return null;

		FluidStack stack = FluidStack.loadFluidStackFromNBT(container.getTagCompound().getCompoundTag("Fluid"));

		if (stack == null)
			return null;

		stack.amount = Math.min(stack.amount, maxDrain);

		if (doDrain)
		{
			if (maxDrain >= this.maxSteam)
			{
				container.getTagCompound().removeTag("Fluid");

				if (container.getTagCompound().hasNoTags())
					container.setTagCompound(null);

				return stack;
			}

			NBTTagCompound fluidTag = container.getTagCompound().getCompoundTag("Fluid");
			fluidTag.setInteger("Amount", fluidTag.getInteger("Amount") - stack.amount);
			container.getTagCompound().setTag("Fluid", fluidTag);
		}

		return stack;
	}

	public int getFluidAmount(ItemStack stack)
	{
		FluidStack fluid = this.getFluid(stack);

		if (fluid == null)
			return 0;

		return fluid.amount;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack)
	{
		return 1.0D - ((double) this.getFluidAmount(stack) / this.getCapacity(stack));
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		return true;
	}
}
