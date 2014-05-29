/**
 * This class was created by <Surseance> as a part of the
 * EnderGlove mod for Minecraft.
 *
 * This mod is registered under the WTFPL v2.0. Please read the
 * COPYING.WTFPL file for more details.
 *
 * File created @[May 14, 2014, 9:07:32 PM]
 */
package boilerplate.common.utils;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import boilerplate.common.entity.EntityMinedBlock;

/**
 * @author Surseance (Johnny Eatmon)
 * Email: surseance@autistici.org
 *
 */
public class EnderUtils
{
	/**
	 * Hmm...I wonder what this method does? Indeed! It *does* spawn chickens!
	 *
	 * @param player - the player to send the message
	 * @param msg - the message to send
	 */
	public static void sendMessage(EntityPlayer player, String msg)
	{
		IChatComponent chat = new ChatComponentText(msg);

		if (!player.worldObj.isRemote)
			player.addChatMessage(chat);
	}

	/**
	 * Determines what a block's drops are without checking for fortune values
	 * (useful for the Flame Touch enchantment).
	 *
	 * @param world - the world "Minecraftia"
	 * @param player - the player breaking the block
	 * @param block - the block being broken
	 * @param x - block xCoord
	 * @param y - block yCoord
	 * @param z - block zCoord
	 * @param md - block metadata
	 *
	 * @return the block's drops
	 */
	public static ItemStack getDroppedItemStack(World world, EntityPlayer player, Block block, int x, int y, int z, int md)
	{
		ArrayList<ItemStack> items = block.getDrops(world, x, y, z, md, EnchantmentHelper.getFortuneModifier(player));
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
	 * @param is - the item stack to check
	 *
	 * @return false if it cannot be smelted
	 */
	public static boolean isSmeltable(ItemStack is)
	{
		return is == null || FurnaceRecipes.smelting().getSmeltingResult(is) == null ? false : true;
	}

	/**
	 * Plays a sound at the given location. It's an ugly method, that's why I
	 * moved it here.
	 *
	 * @param world - the world. What else?
	 * @param x - block xCoord
	 * @param y - block yCoord
	 * @param z - block zCoord
	 * @param sound - sound name
	 */
	public static void playSFX(World world, int x, int y, int z, String sound)
	{
		world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, sound, 1.0F, world.rand.nextFloat() * 0.4F + 0.8F);
	}

	// XXX: doesn't work for some weird reason; keeps returning 4
	public static int getRotationMeta(EntityLivingBase entLiving)
	{
		int md = 0;
		int rot = MathHelper.floor_double((double)(entLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
		switch (rot)
		{
			case 0:
				md = 2;
			case 1:
				md = 5;
			case 2:
				md = 3;
			case 3:
				md = 4;
		}

		return md;
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
	
	@Deprecated
	public static void spawnBlockEntity(EntityPlayer player, Block block, int x, int y, int z, int md, ItemStack drops)
	{
		InventoryEnderChest enderInv = InventoryHelper.getPlayerEnderChest(player);
		
		if ((player.worldObj.isRemote) && (InventoryHelper.isInvEmpty(enderInv, drops)))
			player.worldObj.spawnEntityInWorld(new EntityMinedBlock(player.worldObj, x + 0.5F, y + 0.5F, z + 0.5F, block, md));
	}
}
