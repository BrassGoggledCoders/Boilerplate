package xyz.brassgoggledcoders.boilerplate.registries;

import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RegistryHolder implements IRegistryHolder
{
	private List<BaseRegistry> registries;
	private ConfigRegistry configRegistry;

	public RegistryHolder(IBoilerplateMod mod, File config)
	{
		configRegistry = new ConfigRegistry(mod, this, config);

		registries = new ArrayList<>();
	}

	@Override
	public List<BaseRegistry> getAllRegistries()
	{
		return null;
	}

	@Override
	public BlockRegistry getBlockRegistry()
	{
		return null;
	}

	@Override
	public ItemRegistry getItemRegistry()
	{
		return null;
	}

	@Override
	public EntityRegistry getEntityRegistry()
	{
		return null;
	}

	@Override
	public ConfigRegistry getConfigRegistry()
	{
		return configRegistry;
	}
}
