
package xyz.brassgoggledcoders.boilerplate.blocks;

import com.sun.javafx.beans.annotations.NonNull;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;

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
	public void breakBlock(@NonNull World world, BlockPos pos, IBlockState state)
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
}
