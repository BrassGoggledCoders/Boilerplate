package xyz.brassgoggledcoders.boilerplate.registries;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.entity.SpawnEgg;
import xyz.brassgoggledcoders.boilerplate.entity.SpawnInfo;

import java.util.HashMap;

public class EntityRegistry extends BaseRegistry<Class<? extends Entity>> {
	private HashMap<String, SpawnEgg> spawnEggs = new HashMap<String, SpawnEgg>();
	private HashMap<String, SpawnInfo> spawnInfos = new HashMap<String, SpawnInfo>();
	private int nextAvailableID = 0;

	public EntityRegistry(IBoilerplateMod mod, IRegistryHolder registryHolder) {
		super(mod, registryHolder);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void initiateEntry(String name, Class<? extends Entity> entityClass) {
		net.minecraftforge.fml.common.registry.EntityRegistry
				.registerModEntity(entityClass, name, ++nextAvailableID, mod, 64, 1, true);

		if(spawnEggs.containsKey(name)) {
			SpawnEgg spawnEgg = spawnEggs.get(name);
			net.minecraftforge.fml.common.registry.EntityRegistry
					.registerEgg(entityClass, spawnEgg.primaryColor, spawnEgg.secondaryColor);
		}

		if(spawnInfos.containsKey(name)) {
			SpawnInfo spawnInfo = spawnInfos.get(name);
			if(EntityLiving.class.isAssignableFrom(entityClass)) {
				net.minecraftforge.fml.common.registry.EntityRegistry
						.addSpawn((Class<? extends EntityLiving>) entityClass, spawnInfo.weighted,
								spawnInfo.minimum, spawnInfo.maximum, spawnInfo.creatureType, spawnInfo.spawnBiomes);
			}

		}
		super.initiateEntry(name, entityClass);
	}

	public void registerEntity(Class<? extends Entity> entityClass) {
		registerEntity(entityClass, entityClass.getSimpleName().toLowerCase());
	}

	public void registerEntity(Class<? extends Entity> entityClass, String name) {
		this.entries.put(name, entityClass);
	}

	public void addSpawnEgg(String name, SpawnEgg spawnEgg) {
		this.spawnEggs.put(name, spawnEgg);
	}

	public void addSpawnInfo(String name, SpawnInfo spawnInfo) {
		this.spawnInfos.put(name, spawnInfo);
	}

	public Class<? extends Entity> getEntity(String name) {
		return this.entries.get(name);
	}
}
