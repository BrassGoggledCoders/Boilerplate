package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.item.ItemBlock;

public interface IHasItemBlock
{
	Class<? extends ItemBlock> getItemBlockClass();
}
