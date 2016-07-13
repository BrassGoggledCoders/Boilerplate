package xyz.brassgoggledcoders.boilerplate.api;

import net.minecraft.nbt.NBTTagCompound;

public interface IPipeWrench {

	void deserializeNBT(NBTTagCompound nbt);

	NBTTagCompound serializeNBT();

}
