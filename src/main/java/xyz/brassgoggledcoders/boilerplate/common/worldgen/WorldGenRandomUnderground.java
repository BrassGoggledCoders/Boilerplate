
package xyz.brassgoggledcoders.boilerplate.common.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenRandomUnderground extends WorldGenerator
{
	private Block toGen;
	private int metaToGen;

	public WorldGenRandomUnderground(Block block)
	{
		this(block, 0);
	}

	public WorldGenRandomUnderground(Block block, int meta)
	{
		this.toGen = block;
		this.metaToGen = meta;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position)
	{
		// for (int l = 0; l < 16; ++l)
		// {
		// int xPos = (x + random.nextInt(8)) - random.nextInt(8);
		// int yPos = (y + random.nextInt(4)) - random.nextInt(4);
		// int zPos = (z + random.nextInt(8)) - random.nextInt(8);
		//
		// if (world.isAirBlock(xPos, yPos, zPos) &&
		// this.toGen.canBlockStay(world, xPos, yPos, zPos) &&
		// this.toGen.canPlaceBlockAt(world, x, y, z))
		// {
		// world.setBlock(xPos, yPos, zPos, this.toGen, this.metaToGen, 2);
		// }
		// }

		return false;
	}
}
