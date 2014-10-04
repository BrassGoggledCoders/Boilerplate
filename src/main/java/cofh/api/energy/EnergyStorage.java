package cofh.api.energy;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Reference implementation of {@link IEnergyStorage}. Use/extend this or
 * implement your own.
 * 
 * @author King Lemming
 * 
 */
public class EnergyStorage implements IEnergyStorage
{

	protected int energy;
	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;

	public EnergyStorage(int capacity)
	{

		this(capacity, capacity, capacity);
	}

	public EnergyStorage(int capacity, int maxTransfer)
	{

		this(capacity, maxTransfer, maxTransfer);
	}

	public EnergyStorage(int capacity, int maxReceive, int maxExtract)
	{

		this.capacity = capacity;
		this.maxReceive = maxReceive;
		this.maxExtract = maxExtract;
	}

	public EnergyStorage readFromNBT(NBTTagCompound nbt)
	{

		this.energy = nbt.getInteger("Energy");

		if (this.energy > this.capacity)
		{
			this.energy = this.capacity;
		}
		return this;
	}

	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{

		if (this.energy < 0)
		{
			this.energy = 0;
		}
		nbt.setInteger("Energy", this.energy);
		return nbt;
	}

	public void setCapacity(int capacity)
	{

		this.capacity = capacity;

		if (this.energy > capacity)
		{
			this.energy = capacity;
		}
	}

	public void setMaxTransfer(int maxTransfer)
	{

		this.setMaxReceive(maxTransfer);
		this.setMaxExtract(maxTransfer);
	}

	public void setMaxReceive(int maxReceive)
	{

		this.maxReceive = maxReceive;
	}

	public void setMaxExtract(int maxExtract)
	{

		this.maxExtract = maxExtract;
	}

	public int getMaxReceive()
	{

		return this.maxReceive;
	}

	public int getMaxExtract()
	{

		return this.maxExtract;
	}

	/**
	 * This function is included to allow for server -&gt; client sync. Do not
	 * call this externally to the containing Tile Entity, as not all
	 * IEnergyHandlers are guaranteed to have it.
	 * 
	 * @param energy
	 */
	public void setEnergyStored(int energy)
	{

		this.energy = energy;

		if (this.energy > this.capacity)
		{
			this.energy = this.capacity;
		}
		else if (this.energy < 0)
		{
			this.energy = 0;
		}
	}

	/**
	 * This function is included to allow the containing tile to directly and
	 * efficiently modify the energy contained in the EnergyStorage. Do not rely
	 * on this externally, as not all IEnergyHandlers are guaranteed to have it.
	 * 
	 * @param energy
	 */
	public void modifyEnergyStored(int energy)
	{

		this.energy += energy;

		if (this.energy > this.capacity)
		{
			this.energy = this.capacity;
		}
		else if (this.energy < 0)
		{
			this.energy = 0;
		}
	}

	/* IEnergyStorage */
	@Override
	public int receiveEnergy(int maxReceive, boolean simulate)
	{

		int energyReceived = Math.min(this.capacity - this.energy, Math.min(this.maxReceive, maxReceive));

		if (!simulate)
		{
			this.energy += energyReceived;
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate)
	{

		int energyExtracted = Math.min(this.energy, Math.min(this.maxExtract, maxExtract));

		if (!simulate)
		{
			this.energy -= energyExtracted;
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored()
	{

		return this.energy;
	}

	@Override
	public int getMaxEnergyStored()
	{

		return this.capacity;
	}

}
