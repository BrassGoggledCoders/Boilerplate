package xyz.brassgoggledcoders.boilerplate.api;

import net.minecraft.nbt.NBTTagCompound;

public interface IWrench {

	void deserializeNBT(NBTTagCompound nbt);

	NBTTagCompound serializeNBT();

}
