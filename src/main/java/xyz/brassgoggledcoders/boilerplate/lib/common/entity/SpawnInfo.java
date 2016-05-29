package xyz.brassgoggledcoders.boilerplate.lib.common.entity;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;

public class SpawnInfo
{
	public int weighted;
	public int minimum;
	public int maximum;
	public EnumCreatureType creatureType;
	public Biome[] spawnBiomes;

	public SpawnInfo(int weightedProb, int min, int max, EnumCreatureType typeOfCreature, Biome... biomes)
	{
		this.weighted = weightedProb;
		this.minimum = min;
		this.maximum = max;
		this.creatureType = typeOfCreature;
		this.spawnBiomes = biomes;
	}
}
