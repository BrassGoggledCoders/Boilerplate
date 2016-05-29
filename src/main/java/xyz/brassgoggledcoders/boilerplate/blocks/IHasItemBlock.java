package xyz.brassgoggledcoders.boilerplate.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public interface IHasItemBlock
{
	ItemBlock getItemBlockClass(Block block);
}
