
package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.common.IBoilerplateMod;

/**
 * @author Surseance
 *
 */
public class BlockBase extends Block
{
	IBoilerplateMod mod;

	public BlockBase(Material mat)
	{
		super(mat);
		this.mod = BoilerplateLib.getMod();
		this.setCreativeTab(mod.getCreativeTab());
		this.setHardness(1F);
	}

	public BlockBase(Material mat, String name)
	{
		this(mat);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		worldIn.updateComparatorOutputLevel(pos, this);

		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		this.updateState(worldIn, pos, state);
	}

	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
	{
		this.updateState(worldIn, pos, state);
	}

	protected void updateState(World worldIn, BlockPos pos, IBlockState state)
	{

	}
}
