package boilerplate.common.utils.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class CustomMetalRecipes
{
    static Object[][] recipeItems;

    public CustomMetalRecipes()
    {

    }

    /**
     * Adds the ingot recipes to the CraftingManager.
     */
    public static void addRecipes(CraftingManager par1CraftingManager)
    {
        for (int i = 0; i < CustomMetalRecipes.recipeItems.length; ++i)
        {
            Block block = (Block)CustomMetalRecipes.recipeItems[i][0];
            ItemStack ingot = (ItemStack)CustomMetalRecipes.recipeItems[i][1];
            ItemStack nugget = (ItemStack) CustomMetalRecipes.recipeItems[i][2];
            par1CraftingManager.addRecipe(new ItemStack(block), new Object[] {"###", "###", "###", '#', ingot});
            par1CraftingManager.addRecipe(ingot, new Object[] {"#", '#', block});
            par1CraftingManager.addRecipe(nugget, new Object[] {"#", '#', ingot});
            par1CraftingManager.addRecipe(ingot, new Object[] {"###", "###", "###", '#', nugget});
        }
    }
}
