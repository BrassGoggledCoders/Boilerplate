package xyz.brassgoggledcoders.boilerplate.client.renderers.custom;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IItemRenderingHandler {
	void render(World world, Item item, ItemStack itemStack, ItemCameraTransforms.TransformType type);
}
