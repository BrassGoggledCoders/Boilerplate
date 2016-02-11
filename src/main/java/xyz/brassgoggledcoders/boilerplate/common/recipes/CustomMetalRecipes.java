package xyz.brassgoggledcoders.boilerplate.common.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

/**
 * @author warlordjones
 *
 */
public class CustomMetalRecipes
{
	Block block;

	Item ingot;

	Item nugget;

	int meta;

	public CustomMetalRecipes(Block block, Item ingot, Item nugget, int meta)
	{
		this.block = block;
		this.ingot = ingot;
		this.nugget = nugget;
		this.meta = meta;
	}

	public void addRecipes(final CraftingManager manager)
	{
		manager.addRecipe(new ItemStack(this.block, 1, this.meta), "###", "###", "###", '#', new ItemStack(this.ingot, 9, this.meta));
		manager.addRecipe(new ItemStack(this.ingot, 9, this.meta), "#", '#', new ItemStack(this.block, 1, this.meta));
		manager.addRecipe(new ItemStack(this.nugget, 9, this.meta), "#", '#', new ItemStack(this.ingot, 1, this.meta));
		manager.addRecipe(new ItemStack(this.ingot, 1, this.meta), "###", "###", "###", '#', new ItemStack(this.nugget, 9, this.meta));
	}
}