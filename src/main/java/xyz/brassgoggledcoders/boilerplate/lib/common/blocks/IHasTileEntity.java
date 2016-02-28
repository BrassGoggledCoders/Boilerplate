package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.tileentity.TileEntity;

public interface IHasTileEntity
{
	Class<? extends TileEntity> getTileEntityClass();
}
