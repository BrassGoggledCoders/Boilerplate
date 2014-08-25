/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
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
	private static String[][] recipePatterns = new String[][] {
			{ "XXX", " # ", " # " }, { "X", "#", "#" }, { "XX", "X#", " #" },
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
		manager.addRecipe(outputs[0], new Object[] { recipePatterns[0], '#', Items.stick, 'X', input });
		manager.addRecipe(outputs[1], new Object[] { recipePatterns[1], '#', Items.stick, 'X', input });
		manager.addRecipe(outputs[2], new Object[] { recipePatterns[2], '#', Items.stick, 'X', input });
		manager.addRecipe(outputs[3], new Object[] { recipePatterns[3], '#', Items.stick, 'X', input });
		manager.addRecipe(outputs[4], new Object[] { recipePatterns[4], '#', Items.stick, 'X', input });
	}
}
