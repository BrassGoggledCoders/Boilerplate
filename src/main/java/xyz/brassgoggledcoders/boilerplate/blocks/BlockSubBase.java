package xyz.brassgoggledcoders.boilerplate.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;

public class BlockSubBase extends BlockBase {

	String[] names;

	public BlockSubBase(Material mat, String[] names) {
		super(mat);
		this.names = names;
	}

	@Override
	public ItemBlock getItemBlockClass(Block block) {
		return new ItemSubBlock(block, names);
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

}
