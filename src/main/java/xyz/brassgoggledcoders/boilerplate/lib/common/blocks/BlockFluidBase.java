package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidHandler;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ComparatorUtils;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.FluidUtils;

public abstract class BlockFluidBase extends BlockTEBase
{
	public BlockFluidBase(Material material, String name)
	{
		super(material, name);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		TileEntity tileEntity = world.getTileEntity(pos);
		return tileEntity instanceof IFluidHandler && shouldInteractWithFluidContainers() &&
				FluidUtils.fillFluidHandlerWithPlayerItem(world, (IFluidHandler)tileEntity, player, heldItem);
	}

	public boolean shouldInteractWithFluidContainers()
	{
		return true;
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state)
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos)
	{
		if(world.getTileEntity(pos) instanceof IFluidHandler)
		{
			return ComparatorUtils.scaleSingleFluidLevelTo(15, (IFluidHandler)world.getTileEntity(pos));
		}
		return 0;
	}
}
