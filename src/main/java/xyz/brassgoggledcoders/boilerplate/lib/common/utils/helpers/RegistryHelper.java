
package xyz.brassgoggledcoders.boilerplate.lib.common.utils.helpers;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.BaseBlock;
import xyz.brassgoggledcoders.boilerplate.lib.common.items.BaseItem;
import xyz.brassgoggledcoders.boilerplate.lib.common.items.ItemBlockDesc;

/**
 * @author warlordjones
 *
 */
public class RegistryHelper
{
	public static void registerAndCreateBasicItem(Item itemField)
	{
		itemField = new BaseItem();
		registerItem(itemField);
	}

	public static void registerAndCreateBasicBlock(Block blockField, Material mat, String name)
	{
		blockField = new BaseBlock(mat);
		GameRegistry.registerBlock(blockField, name);
	}

	public static void registerItem(Item item)
	{
		registerItem(item, item.getUnlocalizedName().substring(5));
	}

	public static void registerItem(Item item, String name)
	{
		GameRegistry.registerItem(item, name);
	}

	public static void registerBlockWithDesc(Block block, String name)
	{
		GameRegistry.registerBlock(block, ItemBlockDesc.class, name);
	}

	public static void registerContainerBlock(Block block, Class<? extends TileEntity> tile, String name)
	{
		GameRegistry.registerBlock(block, name);
		String id = "TE" + name.substring(5);
		GameRegistry.registerTileEntity(tile, id);
	}

	public static void registerContainerBlockWithDesc(Block block, Class<? extends TileEntity> tile, String name)
	{
		registerBlockWithDesc(block, name);
		GameRegistry.registerTileEntity(tile, "TE" + name.substring(5));
	}

	public static void registerContainerBlockWithDescAndMeta(Block block, Class<? extends TileEntity> tile, String name)
	{
		// TODO GameRegistry.registerBlock(block, ItemBlockDescMeta.class,
		// name);
		GameRegistry.registerTileEntity(tile, "TE" + name.substring(5));
	}

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

	private static int nextAvailableID = 0;

	public static void registerEntity(Class<? extends Entity> entityClass, String name)
	{
		if(BoilerplateLib.getInstance().getMod() != null)
		{
			EntityRegistry.registerModEntity(entityClass, name, ++nextAvailableID,
					BoilerplateLib.getInstance().getMod(),
					64, 1, true);
		} else {
			BoilerplateLib.getInstance().getLogger().error("Failed to register entity " + name);
		}
	}
}
