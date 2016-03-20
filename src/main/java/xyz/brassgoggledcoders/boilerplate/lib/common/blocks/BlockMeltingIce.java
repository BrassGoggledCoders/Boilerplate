
package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import java.util.Random;

import net.minecraft.block.BlockIce;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

//TODO move registration into BP
public class BlockMeltingIce extends BlockIce
{
	boolean meltsIntoWater;

	public BlockMeltingIce(boolean meltsIntoWater)
	{
		this.meltsIntoWater = meltsIntoWater;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (this.meltsIntoWater)
			world.setBlockState(pos, Blocks.water.getDefaultState());
		else
			world.setBlockState(pos, Blocks.air.getDefaultState());
	}

}
