
package boilerplate.common.utils.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

/**
 * @author warlordjones
 *
 */
public class CustomToolRecipes
{
	private static String[][] recipePatterns = new String[][] { { "XXX", " # ", " # " }, { "X", "#", "#" }, { "XX", "X#", " #" },
			{ "XX", " #", " #" }, { "X", "X", "#" } };

	public ItemStack input;
	public ItemStack[] outputs;

	public CustomToolRecipes(ItemStack input, ItemStack[] outputs)
	{
		this.input = input;
		this.outputs = outputs;
	}

	public void addRecipes(final CraftingManager manager)
	{
		manager.addRecipe(this.outputs[0], recipePatterns[0], '#', Items.stick, 'X', this.input);
		manager.addRecipe(this.outputs[1], recipePatterns[1], '#', Items.stick, 'X', this.input);
		manager.addRecipe(this.outputs[2], recipePatterns[2], '#', Items.stick, 'X', this.input);
		manager.addRecipe(this.outputs[3], recipePatterns[3], '#', Items.stick, 'X', this.input);
		manager.addRecipe(this.outputs[4], recipePatterns[4], '#', Items.stick, 'X', this.input);
	}
}
