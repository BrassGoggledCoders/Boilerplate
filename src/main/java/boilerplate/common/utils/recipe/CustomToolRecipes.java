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

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomToolRecipes.
 */
public class CustomToolRecipes
{
	
	/** The recipe patterns. */
	private static String[][] recipePatterns = new String[][] {
			{ "XXX", " # ", " # " }, { "X", "#", "#" }, { "XX", "X#", " #" },
			{ "XX", " #", " #" }, { "X", "X", "#" } };
	
	/** The outputs. */
	public static ItemStack[] outputs;
	
	/** The input. */
	public static ItemStack input;

	/**
	 * Instantiates a new custom tool recipes.
	 */
	public CustomToolRecipes()
	{

	}

	/**
	 * Adds the tool recipes to the CraftingManager.
	 *
	 * @param par1CraftingManager the par1 crafting manager
	 */
	public void addRecipes(final CraftingManager par1CraftingManager)
	{
		// for(int j = 0; j < recipePatterns.length; j++)
		// {
		par1CraftingManager.addRecipe(outputs[0], new Object[] {
				recipePatterns[0], '#', Items.stick, 'X', input });
		par1CraftingManager.addRecipe(outputs[1], new Object[] {
				recipePatterns[1], '#', Items.stick, 'X', input });
		par1CraftingManager.addRecipe(outputs[2], new Object[] {
				recipePatterns[2], '#', Items.stick, 'X', input });
		par1CraftingManager.addRecipe(outputs[3], new Object[] {
				recipePatterns[3], '#', Items.stick, 'X', input });
		par1CraftingManager.addRecipe(outputs[4], new Object[] {
				recipePatterns[4], '#', Items.stick, 'X', input });
		// }
	}
}
