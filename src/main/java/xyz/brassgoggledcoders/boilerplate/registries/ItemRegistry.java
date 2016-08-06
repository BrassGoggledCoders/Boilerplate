package xyz.brassgoggledcoders.boilerplate.registries;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.client.models.IHasModel;
import xyz.brassgoggledcoders.boilerplate.client.models.SafeModelLoader;
import xyz.brassgoggledcoders.boilerplate.client.renderers.custom.IHasItemRenderHandler;
import xyz.brassgoggledcoders.boilerplate.items.ItemBase;

public class ItemRegistry extends BaseRegistry<Item> {
	public ItemRegistry(IBoilerplateMod mod, IRegistryHolder registryHolder) {
		super(mod, registryHolder);
	}

	@Override
	public void initiateEntry(String name, Item item) {
		ResourceLocation itemRegistryName = new ResourceLocation(mod.getPrefix() + name);
		GameRegistry.register(item, itemRegistryName);
		super.initiateEntry(name, item);
	}

	@Override
	public void initiateModel(String name, Item item) {
		if(item instanceof IHasModel) {
			SafeModelLoader.loadAllItemModels(mod, (IHasModel) item, item);
			if(item instanceof IHasItemRenderHandler) {
				mod.getBoilerplateProxy().registerItemRenderHandler(item);
			}
		} else {
			SafeModelLoader.loadItemModel(mod, item);
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
