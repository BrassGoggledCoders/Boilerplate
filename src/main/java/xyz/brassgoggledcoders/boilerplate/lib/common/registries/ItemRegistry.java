package xyz.brassgoggledcoders.boilerplate.lib.common.registries;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.IHasModel;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.SafeModelLoader;
import xyz.brassgoggledcoders.boilerplate.lib.common.items.BaseItem;
import xyz.brassgoggledcoders.boilerplate.lib.common.items.IHasRecipe;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry
{
	private static ItemRegistry instance;

	private HashMap<String, Item> items = new HashMap<String, Item>();
	private LoadingStage loadingStage = LoadingStage.PREINIT;

	public static ItemRegistry getInstance()
	{
		if(instance == null)
		{
			instance = new ItemRegistry();
		}
		return instance;
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
		if(getInstance().loadingStage != LoadingStage.PREINIT)
		{
			BoilerplateLib.getLogger().devFatal("Item named " + name + " is being loaded too late");
		}
		getInstance().items.put(name, item);
	}

	public static void registerAndCreateBasicItem(String name)
	{
		Item item = new BaseItem(name);
		registerItem(item);
	}

	public void initiateItems()
	{
		for(Map.Entry<String, Item> itemEntry : items.entrySet())
		{
			GameRegistry.registerItem(itemEntry.getValue(), itemEntry.getKey());
		}
	}

	public void initiateModels()
	{
		for(Map.Entry<String, Item> itemEntry : items.entrySet())
		{
			if(itemEntry.getValue() instanceof IHasModel)
			{
				ResourceLocation[] locations = ((IHasModel) itemEntry.getValue()).getModelResourceLocations();
				for(int i = 0; i < locations.length; i++)
				{
					SafeModelLoader.loadItemModel(itemEntry.getValue(), i, locations[i]);
				}
			}
		}
	}

	public void initiateRecipes()
	{
		for(Map.Entry<String, Item> itemEntry : items.entrySet())
		{
			if(itemEntry.getValue() instanceof IHasRecipe)
			{
				for(IRecipe recipe: ((IHasRecipe) itemEntry.getValue()).getRecipes())
				{
					GameRegistry.addRecipe(recipe);
				}
			}
		}
	}

	public void setLoadingStage(LoadingStage stage)
	{
		if(stage.ordinal() < loadingStage.ordinal())
		{
			BoilerplateLib.getLogger().fatal("Stage should never be set to an Early stage!");
		} else
		{
			this.loadingStage = stage;
		}
	}
}
