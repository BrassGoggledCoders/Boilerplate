package xyz.brassgoggledcoders.boilerplate.registries;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import xyz.brassgoggledcoders.boilerplate.Boilerplate;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;

import java.util.AbstractMap;
import java.util.Map;

public class TESRRegistry extends BaseRegistry<Map.Entry<Item, Class<? extends TileEntity>>> {
	public TESRRegistry(IBoilerplateMod mod, IRegistryHolder registryHolder) {
		super(mod, registryHolder);
	}

	@Override
	public void afterRegistration() {
		for(Map.Entry<String, Map.Entry<Item, Class<? extends TileEntity>>> entry: this.entries.entrySet()) {
			Boilerplate.proxy.registerTESR(entry.getKey(), entry.getValue().getKey(), entry.getValue().getValue());
		}
	}

	public void addTESRItem(Item item, Class<? extends TileEntity> tileEntityClass) {
		String name = item.getUnlocalizedName();
		if(name.startsWith("item.")) {
			name = name.substring(5);
		}
		this.addTESR(name, item, tileEntityClass);
	}

	public void addTESR(String name, Item item, Class<? extends TileEntity> tileEntityClass) {
		this.entries.put(name, new AbstractMap.SimpleEntry<>(item, tileEntityClass));
	}


}
