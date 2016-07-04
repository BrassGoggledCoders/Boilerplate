package xyz.brassgoggledcoders.boilerplate.registries;

import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class RegistryHolder implements IRegistryHolder {
	private Map<String, BaseRegistry> registries;
	private BlockRegistry blockRegistry;
	private ItemRegistry itemRegistry;
	private EntityRegistry entityRegistry;
	private ConfigRegistry configRegistry;
	private TESRRegistry tesrRegistry;

	public RegistryHolder(IBoilerplateMod mod, File config) {
		blockRegistry = new BlockRegistry(mod, this);
		itemRegistry = new ItemRegistry(mod, this);
		entityRegistry = new EntityRegistry(mod, this);
		configRegistry = new ConfigRegistry(mod, this, config);
		tesrRegistry = new TESRRegistry(mod, this);

		registries = new HashMap<>();
		registries.put("block", blockRegistry);
		registries.put("item", itemRegistry);
		registries.put("entity", entityRegistry);
		registries.put("config", configRegistry);
		registries.put("tesr", tesrRegistry);
	}

	@Override
	public Map<String, BaseRegistry> getAllRegistries() {
		return this.registries;
	}

	@Override
	public BlockRegistry getBlockRegistry() {
		return this.blockRegistry;
	}

	@Override
	public ItemRegistry getItemRegistry() {
		return this.itemRegistry;
	}

	@Override
	public EntityRegistry getEntityRegistry() {
		return this.entityRegistry;
	}

	@Override
	public ConfigRegistry getConfigRegistry() {
		return this.configRegistry;
	}

	@Override
	public TESRRegistry getTESRRegistry() {
		return null;
	}
}
