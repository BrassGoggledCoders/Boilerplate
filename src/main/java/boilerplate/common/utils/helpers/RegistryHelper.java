/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.common.utils.helpers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;

import cpw.mods.fml.common.registry.GameRegistry;

import boilerplate.common.baseclasses.ItemBlockWithDesc;
import boilerplate.common.baseclasses.ItemBlockWithDescAndMeta;

/**
 * @author warlordjones
 * 
 */
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
		GameRegistry.registerBlock(block, ItemBlockWithDesc.class, name);
		String id = "TE" + name.substring(5);
		GameRegistry.registerTileEntity(tile, id);
	}

	public static void registerContainerBlockWithDescAndMeta(Block block, Class<? extends TileEntity> tile, String name)
	{
		GameRegistry.registerBlock(block, ItemBlockWithDescAndMeta.class, name);
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

	public static void registerToolSet(Item sword, Item spade, Item pickaxe, Item axe, Item hoe, String name, String modid)
	{
		GameRegistry.registerItem(sword, "ItemSword" + name, modid);
		GameRegistry.registerItem(spade, "ItemShovel" + name, modid);
		GameRegistry.registerItem(pickaxe, "ItemPickaxe" + name, modid);
		GameRegistry.registerItem(axe, "ItemAxe" + name, modid);
		GameRegistry.registerItem(hoe, "ItemHoe" + name, modid);
	}
}
