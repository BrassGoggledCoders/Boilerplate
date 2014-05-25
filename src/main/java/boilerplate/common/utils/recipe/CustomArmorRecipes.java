package boilerplate.common.utils.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class CustomArmorRecipes
{
	 private static String[][] recipePatterns = new String[][] {{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
	 public static ItemStack[] outputs;
	 public static ItemStack input;

	    public CustomArmorRecipes()
	    {

	    }

	    /**
	     * Adds the armor recipes to the CraftingManager.
	     */
	    public void addRecipes(CraftingManager par1CraftingManager)
	    {
	    	par1CraftingManager.addRecipe(outputs[0], new Object[] {recipePatterns[0], 'X', input});
    		par1CraftingManager.addRecipe(outputs[1], new Object[] {recipePatterns[1], 'X', input});
    		par1CraftingManager.addRecipe(outputs[2], new Object[] {recipePatterns[2], 'X', input});
    		par1CraftingManager.addRecipe(outputs[3], new Object[] {recipePatterns[3], 'X', input});
	    }
}
