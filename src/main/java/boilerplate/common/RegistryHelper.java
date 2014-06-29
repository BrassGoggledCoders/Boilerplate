package boilerplate.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;

public class RegistryHelper
{
	public static void registerContainerBlock(Block block, Class<? extends TileEntity> tile, String name)
	{
		GameRegistry.registerBlock(block, name);
		String id = "TE" + name.substring(5);
		GameRegistry.registerTileEntity(tile, id);
	}
	public static void registerContainerBlockWithDesc(Block block, Class<? extends TileEntity> tile, String name)
	{
		GameRegistry.registerBlock(block,ItemBlockWithDesc.class, name);
		String id = "TE" + name.substring(5);
		GameRegistry.registerTileEntity(tile, id);
	}
	public static void registerArmorSet(Item helm, Item chestplate, Item legs, Item boots, String name, String modid)
	{
		GameRegistry.registerItem(helm, "ItemHelmet" + name, modid);
		GameRegistry.registerItem(chestplate, "ItemChestplate" + name, modid);
		GameRegistry.registerItem(legs, "ItemLegs" + name, modid);
		GameRegistry.registerItem(boots, "ItemBoots" + name, modid);
	}
	public static void registerToolSet(Item axe, Item hoe, Item pickaxe, Item spade, Item sword, String name, String modid)
	{
		//TODO: Reorder to Sword, Shovel, Pick, Axe, Hoe
		GameRegistry.registerItem(sword, "ItemSword" + name, modid);
		GameRegistry.registerItem(spade, "ItemShovel" + name, modid);
		GameRegistry.registerItem(pickaxe, "ItemPickaxe" + name, modid);
		GameRegistry.registerItem(axe, "ItemAxe" + name, modid);
		GameRegistry.registerItem(hoe, "ItemHoe" + name, modid);
	}
}
