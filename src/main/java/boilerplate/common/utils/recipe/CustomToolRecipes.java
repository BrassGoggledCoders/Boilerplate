package boilerplate.common.utils.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class CustomToolRecipes
{
	private static String[][] recipePatterns = new String[][] {{"XXX", " # ", " # "}, {"X", "#", "#"}, {"XX", "X#", " #"}, {"XX", " #", " #"}, {"X", "X", "#"}};
    public static ItemStack[] outputs;
    public static ItemStack input;

    public CustomToolRecipes()
    {

    }

    /**
     * Adds the tool recipes to the CraftingManager.
     */
    public void addRecipes(CraftingManager par1CraftingManager)
    {
    		//for(int j = 0; j < recipePatterns.length; j++)
    		//{
    		par1CraftingManager.addRecipe(outputs[0], new Object[] {recipePatterns[0], '#', Items.stick, 'X', input});
    		par1CraftingManager.addRecipe(outputs[1], new Object[] {recipePatterns[1], '#', Items.stick, 'X', input});
    		par1CraftingManager.addRecipe(outputs[2], new Object[] {recipePatterns[2], '#', Items.stick, 'X', input});
    		par1CraftingManager.addRecipe(outputs[3], new Object[] {recipePatterns[3], '#', Items.stick, 'X', input});
    		par1CraftingManager.addRecipe(outputs[4], new Object[] {recipePatterns[4], '#', Items.stick, 'X', input});
    		//}
    }
}
