package xyz.brassgoggledcoders.boilerplate.client.events;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.brassgoggledcoders.boilerplate.client.renderers.ItemSpecialRenderer;

public class ModelBakeHandler {
	private static final ModelBakeHandler INSTANCE = new ModelBakeHandler();

	public static ModelBakeHandler getInstance() {
		return INSTANCE;
	}

	private final HashMap<ModelResourceLocation, ItemSpecialRenderer> modelsToSwap;

	private ModelBakeHandler() {
		this.modelsToSwap = new HashMap<ModelResourceLocation, ItemSpecialRenderer>();
	}

	public void registerModelToSwap(ModelResourceLocation location, ItemSpecialRenderer renderer) {
		modelsToSwap.put(location, renderer);
	}

	@SubscribeEvent
	@SuppressWarnings("unused")
	public void onModelBakeEvent(ModelBakeEvent event) {
		for(Map.Entry<ModelResourceLocation, ItemSpecialRenderer> entry : modelsToSwap.entrySet()) {
			event.getModelRegistry().getObject(entry.getKey());
			event.getModelRegistry().putObject(entry.getKey(), entry.getValue());
			event.getModelRegistry().getObject(entry.getKey());
		}
	}
}
