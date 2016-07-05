package xyz.brassgoggledcoders.boilerplate.client.models;

import net.minecraft.block.properties.IProperty;

public interface IHasIgnoredVariants {
	IProperty[] getIgnoredVariants();
}
