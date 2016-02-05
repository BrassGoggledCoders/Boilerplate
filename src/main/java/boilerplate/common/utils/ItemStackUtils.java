
package boilerplate.common.utils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
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

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author warlordjones & decebaldecebal
 *
 */
public class ItemStackUtils
{
	public static Material getBlockMaterial(final IBlockAccess world, final int x, final int y, final int z)
	{
		if (world.getBlock(x, y, z) != null)
		{
			return world.getBlock(x, y, z).getMaterial();
		}

		return Material.air;
	}

	public static ItemStack getDroppedItemStack(World world, EntityPlayer player, Block block, int x, int y, int z, int md)
	{
		ArrayList<ItemStack> items = block.getDrops(world, x, y, z, md, EnchantmentHelper.getFortuneModifier(player));
		ItemStack drops = null;

		if ((items != null) && (items.size() > 0))
		{
			for (int size = 0; size < items.size(); size++)
			{
				drops = items.get(size);
			}
		}

		return drops;
	}

	public static boolean isSmeltable(ItemStack is)
	{
		return !((is != null) && (FurnaceRecipes.smelting().getSmeltingResult(is) == null));
	}

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
		for (int i = 0; i < inventory.getSizeInventory(); i++)
		{
			if ((inventory.getStackInSlot(i) != null) && (item == inventory.getStackInSlot(i).getItem()))
			{
				return i;
			}
		}

		return -1;
	}

	public static ItemStack copyStackWithAmount(ItemStack stack, int amount)
	{
		if(stack==null)
			return null;
		ItemStack s2 = stack.copy();
		s2.stackSize=amount;
		return s2;
	}

	public static boolean isItemNonNull(ItemStack itemStack)
	{
		return itemStack != null && itemStack.getItem() != null;
	}

	public static boolean isItemInstanceOf(ItemStack itemStack, Class itemClass)
	{
		return isItemNonNull(itemStack) && itemClass != null && itemClass.isInstance(itemStack.getItem());
	}

}
