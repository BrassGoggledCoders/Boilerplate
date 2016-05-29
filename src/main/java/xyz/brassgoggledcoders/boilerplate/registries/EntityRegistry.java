package xyz.brassgoggledcoders.boilerplate.registries;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import xyz.brassgoggledcoders.boilerplate.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.entity.SpawnEgg;
import xyz.brassgoggledcoders.boilerplate.entity.SpawnInfo;

import java.util.HashMap;
import java.util.Map;

public class EntityRegistry extends BaseRegistry<Class<? extends Entity>>
{
	private static EntityRegistry instance;

	private HashMap<String, SpawnEgg> spawnEggs = new HashMap<String, SpawnEgg>();
	private HashMap<String, SpawnInfo> spawnInfos = new HashMap<String, SpawnInfo>();
	private int nextAvailableID = 0;

	public EntityRegistry(IBoilerplateMod mod, IRegistryHolder registryHolder)
	{
		super(mod, registryHolder);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void initiateEntries()
	{
		for(Map.Entry<String, Class<? extends Entity>> entry : entries.entrySet())
		{
			net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(entry.getValue(), entry.getKey(),
					++nextAvailableID, BoilerplateLib.getMod(),	64, 1, true);

			if(spawnEggs.containsKey(entry.getKey()))
			{
				SpawnEgg spawnEgg = spawnEggs.get(entry.getKey());
				net.minecraftforge.fml.common.registry.EntityRegistry.registerEgg(entry.getValue(),
						spawnEgg.primaryColor, spawnEgg.secondaryColor);
			}

			if(spawnInfos.containsKey(entry.getKey()))
			{
				SpawnInfo spawnInfo = spawnInfos.get(entry.getKey());
				if(EntityLiving.class.isAssignableFrom(entry.getValue()))
				{
					net.minecraftforge.fml.common.registry.EntityRegistry.addSpawn(
							(Class<? extends EntityLiving>) entry.getValue(), spawnInfo.weighted, spawnInfo.minimum,
							spawnInfo.maximum, spawnInfo.creatureType, spawnInfo.spawnBiomes);
				}

			}
		}
		super.initiateEntries();
	}

	public void registerEntity(Class<? extends  Entity> entityClass)
	{
		registerEntity(entityClass, entityClass.getSimpleName().toLowerCase());
	}

	public void registerEntity(Class<? extends Entity> entityClass, String name)
	{
		this.entries.put(name, entityClass);
	}

	public void addSpawnEgg(String name, SpawnEgg spawnEgg)
	{
		this.spawnEggs.put(name, spawnEgg);
	}

	public void addSpawnInfo(String name, SpawnInfo spawnInfo)
	{
		this.spawnInfos.put(name, spawnInfo);
	}

	public Class<? extends Entity> getEntity(String name)
	{
		return this.entries.get(name);
	}
}
