package xyz.brassgoggledcoders.boilerplate.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;

public class BlockSubBase extends BlockBase {

	public static PropertyEnum<?> type;
	
	public BlockSubBase(Material mat, PropertyEnum<?> type) {
		super(mat);
		BlockSubBase.type = type;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((IBlockType) state.getValue(type)).getMeta();
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

}
