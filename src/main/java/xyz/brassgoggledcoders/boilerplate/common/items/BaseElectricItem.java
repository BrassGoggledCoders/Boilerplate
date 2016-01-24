/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package xyz.brassgoggledcoders.boilerplate.common.items;

import xyz.brassgoggledcoders.boilerplate.api.IEnergyItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author decebaldecebal
 *
 */
public abstract class BaseElectricItem extends RootItem implements IEnergyItem
{
	protected int maxEnergy;
	protected short maxReceive;
	protected short maxSend;

	public BaseElectricItem(int maxEnergy, int maxReceive, int maxSend)
	{
		super();
		this.maxEnergy = maxEnergy * 1000;
		this.maxReceive = (short) maxReceive;
		this.maxSend = (short) maxSend;
		this.setMaxStackSize(1);
	}

	@SuppressWarnings("all")
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		list.add(this.getUnchargedItem(item));
		list.add(this.getChargedItem(item));
	}

	public ItemStack getUnchargedItem(Item item)
	{
		ItemStack uncharged = new ItemStack(item);

		if (!uncharged.hasTagCompound())
		{
			uncharged.setTagCompound(new NBTTagCompound());
		}
		uncharged.getTagCompound().setInteger("energy", 0);

		return uncharged.copy();
	}

	public ItemStack getChargedItem(Item item)
	{
		ItemStack charged = new ItemStack(item);

		if (!charged.hasTagCompound())
		{
			charged.setTagCompound(new NBTTagCompound());
		}
		charged.getTagCompound().setInteger("energy", this.maxEnergy);

		return charged.copy();
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityplayer, List list, boolean flag)
	{
		/* TODO: RF STUFF
		list.add("Energy: " + (this.getEnergyStored(stack) / 1000) + "k / " + (this.maxEnergy / 1000) + "k");
		if ((this.maxSend > 0) && (this.maxReceive > 0))
		{
			list.add("Transfer(in/out): " + this.maxReceive + " / " + this.maxSend);
		}
		else if (this.maxReceive > 0)
		{
			list.add("Transfer(in): " + this.maxReceive);
		}
		else if (this.maxSend > 0)
		{
			list.add("Transfer(out): " + this.maxSend);
		}*/
	}

	@Override
	public void onCreated(ItemStack stack, World par2World, EntityPlayer par3EntityPlayer)
	{
		this.setEnergy(stack, 0);
	}

	public void setEnergy(ItemStack stack, int energy)
	{
		if (!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		NBTTagCompound tag = stack.getTagCompound();

		if (energy < 0)
		{
			energy = 0;
		}

		if (energy > this.maxEnergy)
		{
			energy = this.maxEnergy;
		}

		tag.setInteger("energy", energy);

		stack.setTagCompound(tag);
	}
	/* TODO: RF Stuff
	@Override
	public int receiveEnergy(ItemStack itemStack, int maxReceive, boolean simulate)
	{
		int received = Math.min(this.maxEnergy - this.getEnergyStored(itemStack), maxReceive);
		received = Math.min(received, this.maxReceive);

		if (!simulate)
		{
			this.setEnergy(itemStack, this.getEnergyStored(itemStack) + received);
		}

		return received;
	}

	@Override
	public int extractEnergy(ItemStack itemStack, int maxExtract, boolean simulate)
	{
		int extracted = Math.min(this.getEnergyStored(itemStack), maxExtract);
		extracted = Math.min(extracted, this.maxSend);

		if (!simulate)
		{
			this.setEnergy(itemStack, this.getEnergyStored(itemStack) - extracted);
		}

		return extracted;
	}

	@Override
	public int getEnergyStored(ItemStack itemStack)
	{
		if (itemStack.hasTagCompound())
		{
			return itemStack.getTagCompound().getInteger("energy");
		}
		else
		{
			this.setEnergy(itemStack, 0);
		}
		return 0;
	}

	@Override
	public int getMaxEnergyStored(ItemStack container)
	{
		return this.maxEnergy;
	}

	@Override
	public short getMaxSend()
	{
		return this.maxSend;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack)
	{
		return 1.0D - ((double) this.getEnergyStored(stack) / this.getMaxEnergyStored(stack));
	}*/

	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		return true;
	}
}
