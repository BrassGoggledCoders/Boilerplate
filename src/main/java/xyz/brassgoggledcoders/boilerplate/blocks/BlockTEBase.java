package xyz.brassgoggledcoders.boilerplate.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public abstract class BlockTEBase<T extends TileEntity> extends BlockBase implements IHasTileEntity, ITileEntityProvider {
	public BlockTEBase(Material material, String name) {
		super(material, name);
		this.isBlockContainer = true;
	}

	@Override
	public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
		super.breakBlock(world, pos, state);
		world.removeTileEntity(pos);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean eventReceived(IBlockState state, World world, BlockPos pos, int id, int param) {
		super.eventReceived(state, world, pos, id, param);
		TileEntity tileentity = world.getTileEntity(pos);
		return tileentity != null && tileentity.receiveClientEvent(id, param);
	}

	@SuppressWarnings("unchecked")
	public T getTileEntity(World world, BlockPos pos) {
		TileEntity tileEntity = world.getTileEntity(pos);
		if(tileEntity != null && tileEntity.getClass() == this.getTileEntityClass()) {
			return (T)tileEntity;
		}
		return null;
	}

	@Override
	@Nonnull
	@SuppressWarnings("deprecation")
	public TileEntity createNewTileEntity(@Nonnull World world, int meta) {
		return createTileEntity(world, getStateFromMeta(meta));
	}

	@Override
	@Nonnull
	public abstract TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState blockState);
}
