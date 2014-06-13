/**
 * This class was created by <Surseance> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ 25-May-2014
 */
package boilerplate.common.utils.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

// TODO: Auto-generated Javadoc
/**
 * The Class RecipeUtils.
 */
public class RecipeUtils
{

	/**
	 * Adds the tool set.
	 *
	 * @param input the input
	 * @param outputs            should be a list of the tools, in this order: Pickaxe, Shovel,
	 *            Axe, Hoe, Sword
	 */
	public void addToolSet(final ItemStack input, final ItemStack[] outputs)
	{
		CustomToolRecipes.input = input;
		CustomToolRecipes.outputs = outputs;
		final CustomToolRecipes customToolRecipes = new CustomToolRecipes();
		customToolRecipes.addRecipes(CraftingManager.getInstance());
	}

	/**
	 * Adds the armor set.
	 *
	 * @param input            should be the Item/Block used in crafting
	 * @param outputs            a list of the tools, in this order: Helmet, Chestplate,
	 *            Leggings, Boots
	 */
	public void addArmorSet(final ItemStack input, final ItemStack[] outputs)
	{
		CustomArmorRecipes.input = input;
		CustomArmorRecipes.outputs = outputs;
		final CustomArmorRecipes customArmorRecipes = new CustomArmorRecipes();
		customArmorRecipes.addRecipes(CraftingManager.getInstance());
	}

	/**
	 * Adds the metal recipes.
	 *
	 * @param block the block
	 * @param ingot the ingot
	 * @param nugget the nugget
	 */
	public static void addMetalRecipes(final Block block, final Item ingot,
			final Item nugget, int meta)
	{
		CustomMetalRecipes.block = block;
		CustomMetalRecipes.ingot = ingot;
		CustomMetalRecipes.nugget = nugget;
		CustomMetalRecipes.meta = meta;
		CustomMetalRecipes.addRecipes(CraftingManager.getInstance());
	}
}