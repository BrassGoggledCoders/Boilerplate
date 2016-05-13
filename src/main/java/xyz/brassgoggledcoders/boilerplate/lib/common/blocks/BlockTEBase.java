package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.Utils;

public abstract class BlockTEBase extends BlockBase implements IHasTileEntity
{
	public BlockTEBase(Material material, String name)
	{
		super(material, name);
		this.isBlockContainer = true;
	}

	public int getRenderType()
	{
		return 3;
	}

	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		super.breakBlock(worldIn, pos, state);
		worldIn.removeTileEntity(pos);
	}

	public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam)
	{
		super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		return tileentity != null && tileentity.receiveClientEvent(eventID, eventParam);
	}

	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		Object tileEntity = Utils.createObjectInstance(getTileEntityClass());
		if(tileEntity instanceof TileEntity)
		{
			return (TileEntity)tileEntity;
		}
		return null;
	}
}
