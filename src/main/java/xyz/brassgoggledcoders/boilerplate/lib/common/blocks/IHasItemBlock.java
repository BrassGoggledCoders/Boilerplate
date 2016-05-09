package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public interface IHasItemBlock
{
	ItemBlock getItemBlockClass(Block block);
}
