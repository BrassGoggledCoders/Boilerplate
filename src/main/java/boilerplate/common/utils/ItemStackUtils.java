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
package boilerplate.common.utils;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockUtils.
 */
public class ItemStackUtils
{

	/**
	 * Gets the block material.
	 *
	 * @param world the world
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return the block material
	 */
	public static Material getBlockMaterial(final IBlockAccess world,
			final int x, final int y, final int z)
	{
		if (world.getBlock(x, y, z) != null)
		{
			return world.getBlock(x, y, z).getMaterial();
		}

		return Material.air;
	}
	/**
	 * Determines what a block's drops are without checking for fortune values
	 * (useful for the Flame Touch enchantment).
	 *
	 * @param world
	 *            - the world "Minecraftia"
	 * @param player
	 *            - the player breaking the block
	 * @param block
	 *            - the block being broken
	 * @param x
	 *            - block xCoord
	 * @param y
	 *            - block yCoord
	 * @param z
	 *            - block zCoord
	 * @param md
	 *            - block metadata
	 * @return the block's drops
	 */
	public static ItemStack getDroppedItemStack(World world,
			EntityPlayer player, Block block, int x, int y, int z, int md)
	{
		ArrayList<ItemStack> items = block.getDrops(world, x, y, z, md,
				EnchantmentHelper.getFortuneModifier(player));
		ItemStack drops = null;

		if (items != null && items.size() > 0)
		{
			for (int size = 0; size < items.size(); size++)
			{
				drops = items.get(size);
			}
		}

		return drops;
	}

	/**
	 * Determines whether the given block stored in the item stack can be
	 * smelted.
	 *
	 * @param is
	 *            - the item stack to check
	 * @return false if it cannot be smelted
	 */
	public static boolean isSmeltable(ItemStack is)
	{
		return is == null
				|| FurnaceRecipes.smelting().getSmeltingResult(is) == null ? false
				: true;
	}
}
