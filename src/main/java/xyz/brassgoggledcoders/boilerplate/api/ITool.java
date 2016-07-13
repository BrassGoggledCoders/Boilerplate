package xyz.brassgoggledcoders.boilerplate.api;

import net.minecraft.nbt.NBTTagCompound;

public interface ITool {

	void deserializeNBT(NBTTagCompound nbt);

	NBTTagCompound serializeNBT();

}
