package xyz.brassgoggledcoders.boilerplate.worldgen;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import xyz.brassgoggledcoders.boilerplate.Boilerplate;
import xyz.brassgoggledcoders.boilerplate.config.ConfigEntry;
import xyz.brassgoggledcoders.boilerplate.config.Type;
import xyz.brassgoggledcoders.boilerplate.registries.ConfigRegistry;

public class OreGenerationHandler implements IWorldGenerator {

	private static ConfigRegistry conf = Boilerplate.instance.getRegistryHolder().getConfigRegistry();
	private static HashMap<String, IBlockState> requests = new HashMap<String, IBlockState>();

	// TODO Oredict checks.

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if(world.provider.getDimension() == 0) {
			generateSurface(random, chunkX, chunkZ, world);
		}
	}

	private void generateSurface(Random random, int chunkX, int chunkZ, World world) {
		// TODO Caching?
		for(Entry<String, IBlockState> entry : requests.entrySet()) {
			if(conf.getBoolean(entry.getKey(), true)) {
				for(int g = 0; g < conf.getInt(entry.getKey() + "_chance", 1); g++) {
					BlockPos pos = new BlockPos(chunkX + random.nextInt(16),
							random.nextInt(conf.getInt(entry.getKey() + "_height", 128)), chunkZ + random.nextInt(16));
					new WorldGenMinable(entry.getValue(), conf.getInt(entry.getKey() + "vein", 4)).generate(world,
							random, pos);
					// FMLLog.warning("" + pos.toString(), "");
				}
			}
		}
	}

	public static void requestOreGeneration(String name, IBlockState ore, int vein_size, int max_height,
			int gen_chance) {
		conf.addEntry(name, new ConfigEntry("generate", "Generate " + name.toLowerCase() + "?", Type.BOOLEAN,
				Boolean.toString(true)), "oregeneration");
		conf.addEntry(name + "_size", new ConfigEntry("vein_size", "Vein size for " + name.toLowerCase(), Type.INTEGER,
				Integer.toString(vein_size)), "oregeneration");
		conf.addEntry(name + "_height", new ConfigEntry("max_height", "Max gen height for " + name.toLowerCase(),
				Type.INTEGER, Integer.toString(max_height)), "oregeneration");
		conf.addEntry(name + "_chance", new ConfigEntry("gen_chance",
				"Generation chance per chunk for " + name.toLowerCase(), Type.INTEGER, Integer.toString(gen_chance)),
				"oregeneration");
		OreGenerationHandler.requests.putIfAbsent(name, ore);
	}

	public static boolean haveAnyOresBeenRequested() {
		return !requests.isEmpty();
	}
}
