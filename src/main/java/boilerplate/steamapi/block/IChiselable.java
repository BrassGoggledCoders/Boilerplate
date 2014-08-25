package boilerplate.steamapi.block;

import net.minecraft.block.Block;

public interface IChiselable
{
	public Block getChiseledVariant();
	//Use -1 to get meta from block
	public int getChiseledVariantMeta();
}
