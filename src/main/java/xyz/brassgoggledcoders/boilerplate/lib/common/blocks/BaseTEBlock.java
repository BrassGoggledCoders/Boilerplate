package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.Utils;

public abstract class BaseTEBlock extends Block implements IHasTileEntity, ITileEntityProvider
{
	public BaseTEBlock(Material material)
	{
		super(material, material.getMaterialMapColor());
		this.isBlockContainer = true;
	}

	public int getRenderType()
	{
		return -1;
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
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		Object tileEntity = Utils.createObjectInstance(getTileEntityClass());
		if(tileEntity instanceof TileEntity)
		{
			return (TileEntity)tileEntity;
		}
		return null;
	}
}
