/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.common.utils.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

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

	public static void addMetalRecipes(final Block block, final Item ingot,
			final Item nugget, int meta)
	{
		CustomMetalRecipes customMetalRecipes = new CustomMetalRecipes(block, ingot, nugget, meta);
		customMetalRecipes.addRecipes(CraftingManager.getInstance());
	}
}