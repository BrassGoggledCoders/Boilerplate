package xyz.brassgoggledcoders.boilerplate.common.recipes;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author warlordjones
 *
 */
public class RecipeUtils
{
	public static void addToolSet(final ItemStack input, final ItemStack[] outputs)
	{
		CustomToolRecipes customToolRecipes = new CustomToolRecipes(input, outputs);
		customToolRecipes.addRecipes(CraftingManager.getInstance());
	}

	public static void addArmorSet(ItemStack input, ItemStack[] outputs)
	{
		CustomArmorRecipes customArmorRecipes = new CustomArmorRecipes(input, outputs);
		customArmorRecipes.addRecipes(CraftingManager.getInstance());
	}

	public static void addMetalRecipes(final Block block, final Item ingot, final Item nugget, int meta)
	{
		CustomMetalRecipes customMetalRecipes = new CustomMetalRecipes(block, ingot, nugget, meta);
		customMetalRecipes.addRecipes(CraftingManager.getInstance());
	}

	public static void addMinecartRecipe(ItemStack resultItemStack, ItemStack cartBlock)
	{
		GameRegistry.addShapelessRecipe(resultItemStack, cartBlock, Items.minecart);
	}

	/**
	 * @author warlordjones
	 *
	 */
	public static class CustomToolRecipes
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
}