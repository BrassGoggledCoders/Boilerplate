package xyz.brassgoggledcoders.boilerplate.lib.common.registries;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.client.renderers.ISpecialRenderedItem;
import xyz.brassgoggledcoders.boilerplate.lib.common.items.ItemBase;

import java.util.Map;

public class ItemRegistry extends BaseRegistry<Item>
{
	private static ItemRegistry instance;

	public static ItemRegistry getInstance()
	{
		if(instance == null)
		{
			instance = new ItemRegistry();
		}
		return instance;
	}

	@Override
	public void initiateEntries()
	{
		for(Map.Entry<String, Item> itemEntry : entries.entrySet())
		{
			GameRegistry.registerItem(itemEntry.getValue(), itemEntry.getKey());
		}
		super.initiateEntries();
	}

	@Override
	public void initiateModels()
	{
		super.initiateModels();
		for(Map.Entry<String, Item> entry: entries.entrySet())
		{
			if(entry.getValue() instanceof ISpecialRenderedItem)
			{
				BoilerplateLib.getProxy().registerISpecialRendererItem(entry.getValue());
			}
		}
	}

	public static void registerItem(Item item)
	{
		String name = item.getUnlocalizedName();
		if(name.startsWith("item."))
		{
			name = name.substring(5);
		}
		registerItem(item, name);
	}

	public static void registerItem(Item item, String name)
	{
		if(getInstance().getLoadingStage() != LoadingStage.PREINIT)
		{
			BoilerplateLib.getLogger().devFatal("Item named " + name + " is being loaded too late");
		}
		getInstance().entries.put(name, item);
	}

	public static void registerAndCreateBasicItem(String name)
	{
		Item item = new ItemBase(name);
		registerItem(item, name);
	}


	public static Item getItem(String name)
	{
		return getInstance().entries.get(name);
	}
}
