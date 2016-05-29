package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.Utils;

import javax.annotation.Nonnull;

public abstract class BlockTEBase extends BlockBase implements IHasTileEntity
{
	public BlockTEBase(Material material, String name)
	{
		super(material, name);
		this.isBlockContainer = true;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		super.breakBlock(worldIn, pos, state);
		worldIn.removeTileEntity(pos);
	}

	@Override
	public boolean eventReceived(IBlockState state, World world, BlockPos pos, int id, int param)
	{
		super.eventReceived(state, world, pos, id, param);
		TileEntity tileentity = world.getTileEntity(pos);
		return tileentity != null && tileentity.receiveClientEvent(id, param);
	}

	@Override
	@Nonnull
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		Object tileEntity = Utils.createObjectInstance(getTileEntityClass());
		return (TileEntity)tileEntity;
	}
}
