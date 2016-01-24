/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package xyz.brassgoggledcoders.boilerplate.common.modcompat;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class TinkersIMCRegistration
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
		// TODO
		tag.setInteger("Bow_DrawSpeed", 100);
		tag.setFloat("Bow_ProjectileSpeed", 1.0f);
		tag.setFloat("Projectile_Mass", 2.0f);
		tag.setFloat("Projectile_Fragility", 0.9f);

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

	public static void addNewCastable(String fluidName, int materialID)
	{
		NBTTagCompound tag = new NBTTagCompound();
		// liquid to use
		tag.setString("FluidName", fluidName);
		tag.setInteger("MaterialId", materialID);
		FMLInterModComms.sendMessage("TConstruct", "addPartCastingMaterial", tag);
	}

	public static void addNewFluxBattery(Item fluxBattery)
	{
		ItemStack battery = new ItemStack(fluxBattery);
		FMLInterModComms.sendMessage("TConstruct", "addFluxBattery", battery);
	}

	public static void addNewSmeltable(Item item, int metadata, Block toRender, FluidStack toProduce, int tempRequired)
	{
		NBTTagCompound tag = new NBTTagCompound();
		NBTTagCompound itemtag = new NBTTagCompound();
		(new ItemStack(item, 1, metadata)).writeToNBT(itemtag);
		tag.setTag("Item", itemtag);
		NBTTagCompound block = new NBTTagCompound();
		(new ItemStack(toRender, 1)).writeToNBT(block);
		tag.setTag("Block", block);
		toProduce.writeToNBT(tag);
		tag.setInteger("Temperature", tempRequired);
		FMLInterModComms.sendMessage("TConstruct", "addSmelteryMelting", tag);
	}
}
