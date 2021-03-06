package xyz.brassgoggledcoders.boilerplate.utils;

import java.util.UUID;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author warlordjones & decebaldecebal
 */
public class ItemStackUtils {
	public static Material getBlockMaterial(final IBlockAccess world, final BlockPos blockPos) {
		IBlockState blockState = world.getBlockState(blockPos);
		return blockState.getMaterial();
	}

	public static boolean isSmeltable(ItemStack itemStack) {
		return itemStack != null && FurnaceRecipes.instance().getSmeltingResult(itemStack) != null;
	}

	public static void spawnStackInWorld(World world, BlockPos blockPos, ItemStack stack) {
		world.setBlockToAir(blockPos);
		world.spawnEntityInWorld(new EntityItem(world, blockPos.getX() + 0.5F, blockPos.getY() + 0.5F,
				blockPos.getZ() + 0.5F, stack.copy()));
	}

	public static void addModifier(ItemStack itemStack, String attribute, double amount, int mode) {
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
		if(attributeModifierTag != null)
			attributeModifierTag.setTag("AttributeModifiers", list);

		itemStack.setTagCompound(attributeModifierTag);
	}

	public static int getStackPosition(InventoryPlayer inventory, Item item) {
		for(int i = 0; i < inventory.getSizeInventory(); i++)
			if(doItemsMatch(inventory.getItemStack(), item))
				return i;

		return -1;
	}

	public static ItemStack copyStackWithAmount(ItemStack stack, int amount) {
		if(stack == null)
			return null;
		ItemStack s2 = stack.copy();
		s2.stackSize = amount;
		return s2;
	}

	public static boolean isItemNonNull(ItemStack itemStack) {
		return itemStack != null && itemStack.getItem() != null;
	}

	public static boolean isItemInstanceOf(ItemStack itemStack, Class itemClass) {
		return isItemNonNull(itemStack) && itemClass != null && itemClass.isInstance(itemStack.getItem());
	}

	public static boolean doItemsMatch(ItemStack itemStack, Item item) {
		return isItemNonNull(itemStack) && itemStack.getItem() == item;
	}

}
