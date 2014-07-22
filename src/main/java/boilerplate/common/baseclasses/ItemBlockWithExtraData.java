package boilerplate.common.baseclasses;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockWithExtraData extends ItemBlockWithDesc
{
	Object[] data;
	BlockContainer block;

	public ItemBlockWithExtraData(BlockContainer block, Object[] data)
	{
		super(block);
		this.data = data;
		this.block = block;
	}
	public ItemBlockWithExtraData(Block block)
	{
		super(block);
	}
	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		if(block != null)
		world.setBlock(x, y, z, block);
		BaseTileWithInventory te = (BaseTileWithInventory) world.getTileEntity(x, y, z);
		if(data.length > 0)
		te.inventory = (ItemStack[]) data[0];
		world.setTileEntity(x, y, z, te);
		player.inventory.consumeInventoryItem(stack.getItem());
		return true;
	}
	@Override
	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
		return true;
    }
}
