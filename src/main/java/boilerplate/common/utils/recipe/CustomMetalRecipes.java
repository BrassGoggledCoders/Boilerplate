package boilerplate.common.utils.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class CustomMetalRecipes
{
    static Block block;
    static Item ingot;
    static Item nugget;

    public CustomMetalRecipes()
    {

    }

    /**
     * Adds the ingot recipes to the CraftingManager.
     */
    public static void addRecipes(CraftingManager par1CraftingManager)
    {
            par1CraftingManager.addRecipe(new ItemStack(block), new Object[] {"###", "###", "###", '#', new ItemStack(ingot, 9)});
            par1CraftingManager.addRecipe(new ItemStack(ingot, 9), new Object[] {"#", '#', block});
            par1CraftingManager.addRecipe(new ItemStack(nugget, 9), new Object[] {"#", '#', ingot});
            par1CraftingManager.addRecipe(new ItemStack(ingot), new Object[] {"###", "###", "###", '#', new ItemStack(nugget, 9)});
    }
}
