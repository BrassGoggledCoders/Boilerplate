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
public class CustomMetalRecipes
{
	Block block;

	Item ingot;

	Item nugget;

	int meta;

	public CustomMetalRecipes(Block block, Item ingot, Item nugget, int meta)
	{
		this.block = block;
		this.ingot = ingot;
		this.nugget = nugget;
		this.meta = meta;
	}

	public void addRecipes(final CraftingManager manager)
	{
		manager.addRecipe(new ItemStack(this.block, 1, this.meta), "###", "###", "###", '#', new ItemStack(this.ingot, 9, this.meta));
		manager.addRecipe(new ItemStack(this.ingot, 9, this.meta), "#", '#', new ItemStack(this.block, 1, this.meta));
		manager.addRecipe(new ItemStack(this.nugget, 9, this.meta), "#", '#', new ItemStack(this.ingot, 1, this.meta));
		manager.addRecipe(new ItemStack(this.ingot, 1, this.meta), "###", "###", "###", '#', new ItemStack(this.nugget, 9, this.meta));
	}
}