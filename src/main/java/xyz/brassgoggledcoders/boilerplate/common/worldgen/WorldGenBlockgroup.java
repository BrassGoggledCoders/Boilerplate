
package xyz.brassgoggledcoders.boilerplate.common.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBlockgroup extends WorldGenerator
{
	private final Block blockToGen;
	private final int numberOfBlocks;

	public WorldGenBlockgroup(Block block, int p_i2011_1_)
	{
		this.blockToGen = block;
		this.numberOfBlocks = p_i2011_1_;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position)
	{
		return false;
		// int l = random.nextInt(this.numberOfBlocks);
		// byte b0 = 1;
		// boolean flag = false;
		//
		// for (int i1 = x - l; i1 <= (x + l); ++i1)
		// {
		// for (int j1 = z - l; j1 <= (z + l); ++j1)
		// {
		// int k1 = i1 - x;
		// int l1 = j1 - z;
		//
		// if (((k1 * k1) + (l1 * l1)) <= (l * l))
		// {
		// for (int i2 = y - b0; i2 <= (y + b0); ++i2)
		// {
		// Block block = world.getBlock(i1, i2, j1);
		//
		// if ((block == Blocks.dirt) || (block == Blocks.grass) || (block ==
		// Blocks.stone))
		// {
		// world.setBlock(i1, i2, j1, this.blockToGen, 0, 2);
		// flag = true;
		// }
		// }
		// }
		// }
		// }
		// return flag;
		// TODO
	}
}