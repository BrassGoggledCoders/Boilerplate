package xyz.brassgoggledcoders.boilerplate.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BaseMetadataBlock extends Block
{

	public BaseMetadataBlock(Material materialIn)
	{
		super(materialIn);
		// TODO Auto-generated constructor stub
	}
	/*
	 * public IIcon[] icon = new IIcon[] { null };
	 * 
	 * protected BaseMetadataBlock(Material p_i45394_1_) { super(p_i45394_1_);
	 * this.setHardness(1F); }
	 * 
	 * @Override public ItemStack getPickBlock(MovingObjectPosition target,
	 * World world, int x, int y, int z, EntityPlayer player) { return new
	 * ItemStack(world.getBlock(x, y, z), 1, world.getBlockMetadata(x, y, z)); }
	 * 
	 * @Override public int damageDropped(int meta) { return meta; }
	 */
}
