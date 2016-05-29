package xyz.brassgoggledcoders.boilerplate.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.IModAware;

import javax.annotation.Nonnull;

/**
 * @author Surseance
 *
 */
public class BlockBase extends Block implements IModAware
{
	IBoilerplateMod mod;

	public BlockBase(Material mat)
	{
		super(mat);
		this.setHardness(1F);
	}

	public BlockBase(Material mat, String name)
	{
		this(mat);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
	}

	@Override
	public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state)
	{
		world.updateComparatorOutputLevel(pos, this);

		super.breakBlock(world, pos, state);
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		this.updateState(worldIn, pos, state);
	}

	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighborPos)
	{
		this.updateState(world, pos, world.getBlockState(neighborPos));
	}

	protected void updateState(IBlockAccess world, BlockPos pos, IBlockState state)
	{

	}

	@Override
	public IBoilerplateMod getMod()
	{
		return this.mod;
	}

	@Override
	public void setMod(IBoilerplateMod mod)
	{
		this.mod = mod;
	}
}
