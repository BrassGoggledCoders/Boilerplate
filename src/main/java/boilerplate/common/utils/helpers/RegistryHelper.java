/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.common.utils.helpers;

import boilerplate.common.baseclasses.items.ItemBlockWithDesc;
import boilerplate.common.baseclasses.items.ItemBlockWithDescAndMeta;
import boilerplate.common.utils.entity.KeyValue;
import boilerplate.common.utils.entity.ModWithEntityList;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;

/**
 * @author warlordjones
 *
 */
public class RegistryHelper
{
	public static void registerItem(Item item, String modid)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName(), modid);
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
		for(ModWithEntityList modWithEntityList: entityArrayList)
		{
			if(modWithEntityList.getModName().equals(modid))
			{
				return modWithEntityList;
			}
		}

		return null;
	}

	public static void registerEntity(Mod mod, Entity entity, String name)
	{
		int entityID = getEntityID(mod.modid(), name);

		EntityRegistry.registerModEntity(entity.getClass(), name, entityID, mod, 64, 1, true);
	}

	private static int getEntityID(String modid, String entityName)
	{
		for(ModWithEntityList modWithEntityList: entityArrayList)
		{
			if(modWithEntityList.getModName().equals(modid))
			{
				for(KeyValue<String, Integer> entityNameAndID: modWithEntityList.getEntityList())
				{
					if(entityNameAndID.getKey().equals(entityName))
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
