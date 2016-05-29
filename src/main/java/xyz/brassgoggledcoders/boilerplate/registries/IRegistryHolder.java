package xyz.brassgoggledcoders.boilerplate.registries;

import java.util.List;

public interface IRegistryHolder
{
	List<BaseRegistry> getAllRegistries();

	BlockRegistry getBlockRegistry();

	ItemRegistry getItemRegistry();

	EntityRegistry getEntityRegistry();

	ConfigRegistry getConfigRegistry();
}
