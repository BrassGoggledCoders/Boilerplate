package xyz.brassgoggledcoders.boilerplate.blocks;

import net.minecraft.tileentity.TileEntity;

public interface IHasTileEntity {
	Class<? extends TileEntity> getTileEntityClass();
}
