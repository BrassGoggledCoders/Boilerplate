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
	public void initiateEntry(String name, Map.Entry<Item, Class<? extends TileEntity>> tesr) {
		Boilerplate.proxy.registerTESR(name, tesr.getKey(), tesr.getValue());
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
