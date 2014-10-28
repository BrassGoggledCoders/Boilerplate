package boilerplate.common.utils.helpers;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.common.event.FMLInterModComms;

public class IMCHelper
{
	public static void addNewToolMaterial(int matID, String name, int dura, int minespeed, int attack, float handlemodifier, int reinforcedLevel,
			String matTextColor, int color)
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("Id", matID);
		tag.setString("Name", name);
		tag.setInteger("Durability", dura);
		tag.setInteger("MiningSpeed", minespeed);
		tag.setInteger("Attack", attack);
		tag.setFloat("HandleModifier", handlemodifier);
		tag.setInteger("Reinforced", reinforcedLevel);
		tag.setString("Style", matTextColor);
		tag.setInteger("Color", color);

		FMLInterModComms.sendMessage("TConstruct", "addMaterial", tag);
	}

	public static void addNewSmeltable(ItemStack item, Block toRender, FluidStack toProduce, int tempRequired)
	{
		NBTTagCompound tag = new NBTTagCompound();
		NBTTagCompound itemtag = new NBTTagCompound();
		item.writeToNBT(itemtag);
		tag.setTag("Item", itemtag);
		NBTTagCompound block = new NBTTagCompound();
		(new ItemStack(toRender, 1)).writeToNBT(block);
		tag.setTag("Block", block);
		toProduce.writeToNBT(tag);
		tag.setInteger("Temperature", tempRequired);
		FMLInterModComms.sendMessage("TConstruct", "addSmelteryMelting", tag);
	}

	public static void addNewPartBuilderMaterial(int matID, ItemStack mainItem, ItemStack shardItem, int value)
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("MaterialId", matID);
		NBTTagCompound item = new NBTTagCompound();
		mainItem.writeToNBT(item);
		tag.setTag("Item", item);

		item = new NBTTagCompound();
		shardItem.writeToNBT(item);
		tag.setTag("Shard", item);

		tag.setInteger("Value", value);
		FMLInterModComms.sendMessage("TConstruct", "addPartBuilderMaterial", tag);
	}
}
