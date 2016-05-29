package xyz.brassgoggledcoders.boilerplate.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

/**
 * @author warlordjones
 *
 */
public class CustomArmorRecipes
{
	private static String[][] recipePatterns = new String[][] { { "XXX", "X X" }, { "X X", "XXX", "XXX" }, { "XXX", "X X", "X X" },
			{ "X X", "X X" } };

	public ItemStack input;
	public ItemStack[] outputs;

	public CustomArmorRecipes(ItemStack input, ItemStack[] outputs)
	{
		this.input = input;
		this.outputs = outputs;
	}

	public void addRecipes(CraftingManager manager)
	{
		manager.addRecipe(this.outputs[0], recipePatterns[0], 'X', this.input);
		manager.addRecipe(this.outputs[1], recipePatterns[1], 'X', this.input);
		manager.addRecipe(this.outputs[2], recipePatterns[2], 'X', this.input);
		manager.addRecipe(this.outputs[3], recipePatterns[3], 'X', this.input);
	}
}
