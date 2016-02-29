package xyz.brassgoggledcoders.boilerplate.lib.common.recipes;

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
}