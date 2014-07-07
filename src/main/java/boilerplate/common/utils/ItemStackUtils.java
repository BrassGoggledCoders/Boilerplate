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
import java.util.UUID;

import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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

	/**
	 * @author decebaldecebal
	 *
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param stack
	 */
	public static void spawnStackInWorld(World world, int x, int y, int z, ItemStack stack)
    {
    	world.setBlockToAir(x, y, z);
		world.spawnEntityInWorld(new EntityItem(world, x + 0.5F, y + 0.5F, z + 0.5F, stack.copy()));
    }

	public static void addModifier(ItemStack itemStack, String attribute, double amount, int mode)
	{
		NBTTagList list = new NBTTagList();
		NBTTagCompound attributes = new NBTTagCompound();
		attributes.setString("Name", "Attribute");
		attributes.setString("AttributeName", attribute);
		attributes.setDouble("Amount", amount);
		attributes.setLong("UUIDMost", UUID.randomUUID().getMostSignificantBits());
		attributes.setLong("UUIDLeast", UUID.randomUUID().getLeastSignificantBits());
		attributes.setInteger("Operation", mode);

		list.appendTag(attributes);

		NBTTagCompound attributeModifierTag = itemStack.getTagCompound();
		attributeModifierTag.setTag("AttributeModifiers", list);

		itemStack.setTagCompound(attributeModifierTag);
	}
	public static int getStackPosition(InventoryPlayer inventory, Item item)
	{
		for(int i = 0; i < inventory.getSizeInventory(); i++)
		{
			if(inventory.getStackInSlot(i) != null && item == inventory.getStackInSlot(i).getItem())
			{
				return i;
			}
		}

		return -1;
	}
}
