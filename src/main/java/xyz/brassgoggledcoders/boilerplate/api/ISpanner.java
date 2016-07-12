package xyz.brassgoggledcoders.boilerplate.api;

import net.minecraft.nbt.NBTTagCompound;

public interface ISpanner {

	void deserializeNBT(NBTTagCompound nbt);

	NBTTagCompound serializeNBT();

}
