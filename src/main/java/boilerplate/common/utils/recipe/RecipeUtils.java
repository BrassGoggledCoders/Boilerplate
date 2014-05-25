package boilerplate.common.utils.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class RecipeUtils
{
/**
 *
 * @param inputs should be the Item/Block used in crafting
 * @param outputs should be a list of the tools, in this order: Pickaxe, Shovel, Axe, Hoe, Sword
 */
public void addToolSet(ItemStack input, ItemStack[] outputs)
{
	CustomToolRecipes.input = input;
	CustomToolRecipes.outputs = outputs;
	CustomToolRecipes customToolRecipes = new CustomToolRecipes();
	customToolRecipes.addRecipes(CraftingManager.getInstance());
}
/**
 * @param input should be the Item/Block used in crafting
 * @param outputs a list of the tools, in this order: Helmet, Chestplate, Leggings, Boots
 *
 */
public void addArmorSet(ItemStack input, ItemStack[] outputs)
{
	CustomArmorRecipes.input = input;
	CustomArmorRecipes.outputs = outputs;
	CustomArmorRecipes customArmorRecipes = new CustomArmorRecipes();
	customArmorRecipes.addRecipes(CraftingManager.getInstance());
}
/**
 *
 * @param recipe should be in this format Block, Ingot, Nugget.
 */
public static void addMetalRecipes(Object[][] recipe)
{
	CustomMetalRecipes.recipeItems = recipe;
	CustomMetalRecipes.addRecipes(CraftingManager.getInstance());
}
}
