/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package xyz.brassgoggledcoders.boilerplate.common.utils.helpers;

import xyz.brassgoggledcoders.boilerplate.common.items.ItemBlockWithDesc;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author warlordjones
 *
 */
public class RegistryHelper
{
	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName());
	}

	public static void registerBlockWithDesc(Block block, String name)
	{
		GameRegistry.registerBlock(block, ItemBlockWithDesc.class, name);
	}

	public static void registerContainerBlock(Block block, Class<? extends TileEntity> tile, String name)
	{
		GameRegistry.registerBlock(block, name);
		String id = "TE" + name.substring(5);
		GameRegistry.registerTileEntity(tile, id);
	}

	public static void registerContainerBlockWithDesc(Block block, Class<? extends TileEntity> tile, String name)
	{
		GameRegistry.registerBlock(block, ItemBlockWithDesc.class, name);
		String id = "TE" + name.substring(5);
		GameRegistry.registerTileEntity(tile, id);
	}

	/* TODO Metadata Blocks in general
	public static void registerContainerBlockWithDescAndMeta(Block block, Class<? extends TileEntity> tile, String name)
	{
		GameRegistry.registerBlock(block, ItemBlockWithDescAndMeta.class, name);
		String id = "TE" + name.substring(5);
		GameRegistry.registerTileEntity(tile, id);
	}*/

	public static void registerArmorSet(Item helm, Item chestplate, Item legs, Item boots, String name)
	{
		GameRegistry.registerItem(helm, "ItemHelmet" + name);
		GameRegistry.registerItem(chestplate, "ItemChestplate" + name);
		GameRegistry.registerItem(legs, "ItemLegs" + name);
		GameRegistry.registerItem(boots, "ItemBoots" + name);
	}

	public static void registerToolSet(Item sword, Item spade, Item pickaxe, Item axe, Item hoe, String name)
	{
		GameRegistry.registerItem(sword, "ItemSword" + name);
		GameRegistry.registerItem(spade, "ItemShovel" + name);
		GameRegistry.registerItem(pickaxe, "ItemPickaxe" + name);
		GameRegistry.registerItem(axe, "ItemAxe" + name);
		GameRegistry.registerItem(hoe, "ItemHoe" + name);
	}
}
