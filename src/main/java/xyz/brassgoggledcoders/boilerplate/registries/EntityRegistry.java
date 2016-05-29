package xyz.brassgoggledcoders.boilerplate.registries;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import xyz.brassgoggledcoders.boilerplate.BoilerplateLib;
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

	public static EntityRegistry getInstance()
	{
		if(instance == null)
		{
			instance = new EntityRegistry();
		}
		return instance;
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

	public static void registerEntity(Class<? extends  Entity> entityClass)
	{
		registerEntity(entityClass, entityClass.getSimpleName().toLowerCase());
	}

	public static void registerEntity(Class<? extends Entity> entityClass, String name)
	{
		getInstance().entries.put(name, entityClass);
	}

	public static void addSpawnEgg(String name, SpawnEgg spawnEgg)
	{
		getInstance().spawnEggs.put(name, spawnEgg);
	}

	public static void addSpawnInfo(String name, SpawnInfo spawnInfo)
	{
		getInstance().spawnInfos.put(name, spawnInfo);
	}

	public static Class<? extends Entity> getEntity(String name)
	{
		return getInstance().entries.get(name);
	}
}
