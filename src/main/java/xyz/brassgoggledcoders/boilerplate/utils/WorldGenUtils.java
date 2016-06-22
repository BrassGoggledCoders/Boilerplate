package xyz.brassgoggledcoders.boilerplate.utils;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class WorldGenUtils {
	public static void generateOre(World world, Random random, int chunkX, int chunkZ, int blockPerChunk, int minHeight,
			int maxHeight, int blocks, Block ore, int meta, Block blockToGenIn) {
		for(int i = 0; i < blockPerChunk; i++) {
			random.nextInt(16);
			random.nextInt(maxHeight - minHeight);
			random.nextInt(16);

			// TODO new WorldGenMinable(ore, meta, blocks,
			// blockToGenIn).generate(world, random, oreXCoord, oreYCoord,
			// oreZCoord);
		}
	}
}
