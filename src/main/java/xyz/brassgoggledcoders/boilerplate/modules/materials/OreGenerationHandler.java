package xyz.brassgoggledcoders.boilerplate.modules.materials;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.oredict.OreDictionary;
import xyz.brassgoggledcoders.boilerplate.Boilerplate;
import xyz.brassgoggledcoders.boilerplate.blocks.material.BlockMetalOre;
import xyz.brassgoggledcoders.boilerplate.config.ConfigEntry;
import xyz.brassgoggledcoders.boilerplate.config.Type;

public class OreGenerationHandler implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if(world.provider.getDimension() == 0) {
			this.generateSurface(random, chunkX, chunkZ, world);
		}
	}

	private void generateSurface(Random random, int chunkX, int chunkZ, World world) {
		for(int i = 0; i < BlockMetalOre.EnumBlockType.names().length; i++) {
			Boilerplate.instance.getRegistryHolder().getConfigRegistry().addEntry(
					"oreOverride" + BlockMetalOre.EnumBlockType.names()[i],
					new ConfigEntry("ores", "Always load ore " + BlockMetalOre.EnumBlockType.names()[i] + "?",
							Type.BOOLEAN, Boolean.toString(false)),
					"oregeneration");
			// TODO Add request system here
			if(!(OreDictionary.doesOreNameExist("ore" + BlockMetalOre.EnumBlockType.names()[i]))
					|| Boilerplate.instance.getRegistryHolder().getConfigRegistry()
							.getBoolean("oreOverride" + BlockMetalOre.EnumBlockType.names()[i], false)) {
				for(int g = 0; g < 20; g++) {
					BlockPos pos = new BlockPos(chunkX + random.nextInt(16), random.nextInt(64)/* TODO */,
							chunkZ + random.nextInt(16));
					new WorldGenMinable(MaterialsModule.metal_ore.getStateFromMeta(i), 10 /* TODO */).generate(world,
							random, pos);
				}
			}
		}
	}

}
