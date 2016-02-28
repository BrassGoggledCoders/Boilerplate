package xyz.brassgoggledcoders.boilerplate.lib.client.events;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.brassgoggledcoders.boilerplate.lib.client.renderers.ItemSpecialRenderer;

import java.util.HashMap;
import java.util.Map;

public class ModelBakeHandler
{
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
			Object before = event.modelRegistry.getObject(entry.getKey());
			event.modelRegistry.putObject(entry.getKey(), entry.getValue());
			Object after = event.modelRegistry.getObject(entry.getKey());
			boolean test = true;
		}
	}
}
