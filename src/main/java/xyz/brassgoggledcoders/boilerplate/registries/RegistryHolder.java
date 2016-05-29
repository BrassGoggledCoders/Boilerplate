package xyz.brassgoggledcoders.boilerplate.registries;

import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RegistryHolder implements IRegistryHolder
{
	private List<BaseRegistry> registries;
	private BlockRegistry blockRegistry;
	private ItemRegistry itemRegistry;
	private EntityRegistry entityRegistry;
	private ConfigRegistry configRegistry;

	public RegistryHolder(IBoilerplateMod mod, File config)
	{
		blockRegistry = new BlockRegistry(mod, this);
		itemRegistry = new ItemRegistry(mod, this);
		entityRegistry = new EntityRegistry(mod, this);
		configRegistry = new ConfigRegistry(mod, this, config);

		registries = new ArrayList<>();
		registries.add(blockRegistry);
		registries.add(itemRegistry);
		registries.add(entityRegistry);
		registries.add(configRegistry);
	}

	@Override
	public List<BaseRegistry> getAllRegistries()
	{
		return this.registries;
	}

	@Override
	public BlockRegistry getBlockRegistry()
	{
		return this.blockRegistry;
	}

	@Override
	public ItemRegistry getItemRegistry()
	{
		return this.itemRegistry;
	}

	@Override
	public EntityRegistry getEntityRegistry()
	{
		return this.entityRegistry;
	}

	@Override
	public ConfigRegistry getConfigRegistry()
	{
		return this.configRegistry;
	}
}
