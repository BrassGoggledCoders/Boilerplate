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
 * The Class CustomMetalRecipes.
 */
public class CustomMetalRecipes
{
	
	/** The block. */
	static Block block;
	
	/** The ingot. */
	static Item ingot;
	
	/** The nugget. */
	static Item nugget;

	/**
	 * Instantiates a new custom metal recipes.
	 */
	public CustomMetalRecipes()
	{

	}

	/**
	 * Adds the ingot recipes to the CraftingManager.
	 *
	 * @param par1CraftingManager the par1 crafting manager
	 */
	public static void addRecipes(final CraftingManager par1CraftingManager)
	{
		par1CraftingManager.addRecipe(new ItemStack(block), new Object[] {
				"###", "###", "###", '#', new ItemStack(ingot, 9) });
		par1CraftingManager.addRecipe(new ItemStack(ingot, 9), new Object[] {
				"#", '#', block });
		par1CraftingManager.addRecipe(new ItemStack(nugget, 9), new Object[] {
				"#", '#', ingot });
		par1CraftingManager.addRecipe(new ItemStack(ingot), new Object[] {
				"###", "###", "###", '#', new ItemStack(nugget, 9) });
	}
}
