package xyz.brassgoggledcoders.boilerplate.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockSubBase extends BlockBase {

	public BlockSubBase(Material mat) {
		super(mat);
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

}
