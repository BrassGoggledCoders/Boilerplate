package xyz.brassgoggledcoders.boilerplate.registries;

import java.util.Map;

public interface IRegistryHolder {
	Map<String, BaseRegistry> getAllRegistries();

	BlockRegistry getBlockRegistry();

	ItemRegistry getItemRegistry();

	EntityRegistry getEntityRegistry();

	ConfigRegistry getConfigRegistry();

	TESRRegistry getTESRRegistry();
}
