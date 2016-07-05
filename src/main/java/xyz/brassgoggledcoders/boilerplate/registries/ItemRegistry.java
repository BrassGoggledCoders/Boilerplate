package xyz.brassgoggledcoders.boilerplate.registries;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.client.models.IHasModel;
import xyz.brassgoggledcoders.boilerplate.client.models.SafeModelLoader;
import xyz.brassgoggledcoders.boilerplate.client.renderers.ISpecialRenderedItem;
import xyz.brassgoggledcoders.boilerplate.items.ItemBase;

import java.util.Map;

public class ItemRegistry extends BaseRegistry<Item> {
	public ItemRegistry(IBoilerplateMod mod, IRegistryHolder registryHolder) {
		super(mod, registryHolder);
	}

	@Override
	public void initiateEntries() {
		for(Map.Entry<String, Item> itemEntry : entries.entrySet()) {
			ResourceLocation name = new ResourceLocation(mod.getPrefix() + itemEntry.getKey());
			GameRegistry.register(itemEntry.getValue(), name);
		}
		super.initiateEntries();
	}

	@Override
	public void initiateModels() {
		for(Map.Entry<String, Item> entry : entries.entrySet()) {
			Item item = entry.getValue();
			if(item instanceof IHasModel) {
				SafeModelLoader.loadAllItemModels(mod, (IHasModel)item, item);
				if(item instanceof ISpecialRenderedItem) {
					mod.getBoilerplateProxy().registerISpecialRendererItem(item);
				}
			} else {
				SafeModelLoader.loadItemModel(mod, item);
			}
		}
	}

	public void registerItem(Item item) {
		String name = item.getUnlocalizedName();
		if(name.startsWith("item.")) {
			name = name.substring(5);
		}
		registerItem(item, name);
	}

	public void registerItem(Item item, String name) {
		if(this.getLoadingStage() != LoadingStage.PREINIT) {
			mod.getLogger().devFatal("Item named " + name + " is being loaded too late");
		}
		this.entries.put(name, item);
	}

	public void registerAndCreateBasicItem(String name) {
		Item item = new ItemBase(name);
		registerItem(item, name);
	}

	public Item getItem(String name) {
		return this.entries.get(name);
	}
}