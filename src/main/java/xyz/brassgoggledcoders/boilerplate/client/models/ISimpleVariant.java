package xyz.brassgoggledcoders.boilerplate.client.models;

import xyz.brassgoggledcoders.boilerplate.blocks.IBlockType;

public interface ISimpleVariant {
	public Class<? extends IBlockType> getEnumToSwitch();
}
