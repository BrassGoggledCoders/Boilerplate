
package xyz.brassgoggledcoders.boilerplate.common.utils.helpers;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import xyz.brassgoggledcoders.boilerplate.common.blocks.BaseBlock;
import xyz.brassgoggledcoders.boilerplate.common.items.BaseItem;
import xyz.brassgoggledcoders.boilerplate.common.items.ItemBlockDesc;
import xyz.brassgoggledcoders.boilerplate.common.utils.Utils;
import xyz.brassgoggledcoders.boilerplate.common.utils.entity.KeyValue;
import xyz.brassgoggledcoders.boilerplate.common.utils.entity.ModWithEntityList;

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
		// TODO: in 1.8, convert to using uppercase registry names, as is
		// standard for blocks.
		GameRegistry.registerItem(item, item.getUnlocalizedName());
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

	private static ArrayList<ModWithEntityList> entityArrayList = new ArrayList<ModWithEntityList>();

	public static void addToEntityArrayList(String modid, Configuration configuration)
	{
		addToEntityArrayList(new ModWithEntityList(modid, configuration));
	}

	public static void addToEntityArrayList(ModWithEntityList modWithEntityList)
	{
		entityArrayList.add(modWithEntityList);
	}

	public static ModWithEntityList getFromEntityArrayList(String modid)
	{
		for (ModWithEntityList modWithEntityList : entityArrayList)
		{
			if (modWithEntityList.getModName().equals(modid))
			{
				return modWithEntityList;
			}
		}

		return null;
	}

	public static void registerEntity(Class<? extends Entity> entityClass, String name)
	{
		int entityID = getEntityID(Utils.getCurrentMod().getID(), name);

		EntityRegistry.registerModEntity(entityClass, name, entityID, Utils.getCurrentMod(), 64, 1, true);
	}

	private static int getEntityID(String modid, String entityName)
	{
		for (ModWithEntityList modWithEntityList : entityArrayList)
		{
			if (modWithEntityList.getModName().equals(modid))
			{
				for (KeyValue<String, Integer> entityNameAndID : modWithEntityList.getEntityList())
				{
					if (entityNameAndID.getKey().equals(entityName))
					{
						return entityNameAndID.getValue();
					}
				}
			}

			return modWithEntityList.getNextAvailableID();
		}
		ArrayList<KeyValue<String, Integer>> newEntityArrayList = new ArrayList<KeyValue<String, Integer>>();
		newEntityArrayList.add(new KeyValue<String, Integer>(entityName, 0));
		ModWithEntityList newModWithEntityList = new ModWithEntityList(modid, newEntityArrayList, 0);
		addToEntityArrayList(newModWithEntityList);
		return getEntityID(modid, entityName);
	}
}
