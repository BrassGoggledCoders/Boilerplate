package xyz.brassgoggledcoders.boilerplate.client.renderers;

import java.util.HashMap;

import xyz.brassgoggledcoders.boilerplate.utils.ClassLoading;

public class ItemSpecialRenderStore {
	private static ItemSpecialRenderStore instance;
	private static HashMap<String, ItemSpecialRenderer> renderersLoaded = new HashMap<String, ItemSpecialRenderer>();

	public static ItemSpecialRenderStore getInstance() {
		if(instance == null)
			instance = new ItemSpecialRenderStore();
		return instance;
	}

	public static ItemSpecialRenderer getItemSpecialRenderer(ISpecialRenderedItem item) {
		return getItemSpecialRenderer(item.getSpecialRendererPath());
	}

	public static ItemSpecialRenderer getItemSpecialRenderer(String path) {
		if(!renderersLoaded.containsKey(path)) {
			Object renderer = ClassLoading.createObjectInstance(path);
			if(renderer instanceof ItemSpecialRenderer)
				renderersLoaded.put(path, (ItemSpecialRenderer) renderer);
		}
		return renderersLoaded.get(path);
	}
}
