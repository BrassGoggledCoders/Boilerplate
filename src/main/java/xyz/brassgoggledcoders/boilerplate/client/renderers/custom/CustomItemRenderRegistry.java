package xyz.brassgoggledcoders.boilerplate.client.renderers.custom;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.client.models.IHasModel;
import xyz.brassgoggledcoders.boilerplate.utils.ClassLoading;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class CustomItemRenderRegistry implements ICustomModelLoader {
	private static final CustomItemRenderRegistry INSTANCE = new CustomItemRenderRegistry();
	private final Map<ResourceLocation, ItemRenderer> renderers;

	private CustomItemRenderRegistry() {
		this.renderers = new HashMap<>();
		ModelLoaderRegistry.registerLoader(this);
	}

	public static CustomItemRenderRegistry getInstance() {
		return INSTANCE;
	}

	@SuppressWarnings("rawtypes")
	public static void addCustomRenderer(IBoilerplateMod mod, Item item, IHasItemRenderHandler itemRenderHandler) {
		String path = itemRenderHandler.itemRenderPath();
		ModelResourceLocation[] locations = INSTANCE.getResourceLocations(mod, itemRenderHandler);
		IItemRenderingHandler itemRenderingHandler = ClassLoading.createInstanceOf(IItemRenderingHandler.class, path);

		if(itemRenderingHandler != null) {
			for(ModelResourceLocation location : locations) {
				if(!getInstance().renderers.containsKey(location)) {
					getInstance().renderers.put(new ResourceLocation(location.getResourceDomain(), "models/item/" +
							location.getResourcePath()), new ItemRenderer(itemRenderingHandler));
				}
				ModelLoader.setCustomMeshDefinition(item, stack -> location);
			}
		}
	}

	private ModelResourceLocation[] getResourceLocations(IBoilerplateMod mod, IHasModel hasModel) {
		String[] locations = hasModel.getResourceLocations();
		ModelResourceLocation[] resourceLocations = new ModelResourceLocation[locations.length];
		for(int i = 0; i < locations.length; i++) {
			resourceLocations[i] = new ModelResourceLocation(mod.getPrefix() + locations[i]);
		}
		return resourceLocations;
	}

	@Override
	public boolean accepts(ResourceLocation modelLocation) {
		return renderers.containsKey(modelLocation);
	}

	@Override
	public IModel loadModel(ResourceLocation modelLocation) throws Exception {
		return renderers.get(modelLocation);
	}

	@Override
	public void onResourceManagerReload(@Nonnull IResourceManager resourceManager) {

	}
}
