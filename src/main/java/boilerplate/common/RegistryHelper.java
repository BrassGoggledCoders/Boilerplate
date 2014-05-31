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
	public static void registerArmorSet(Item helm, Item chest, Item legs, Item boots, String name)
	{
		GameRegistry.registerItem(helm, "ItemHelmet" + name);
		GameRegistry.registerItem(chest, "ItemChest" + name);
		GameRegistry.registerItem(legs, "ItemLegs" + name);
		GameRegistry.registerItem(boots, "ItemBoots" + name);
	}
}
