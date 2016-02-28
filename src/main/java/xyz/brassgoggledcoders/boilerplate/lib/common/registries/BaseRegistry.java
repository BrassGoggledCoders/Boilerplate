package xyz.brassgoggledcoders.boilerplate.lib.common.registries;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.IHasModel;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.SafeModelLoader;
import xyz.brassgoggledcoders.boilerplate.lib.common.items.IHasRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseRegistry<T extends Object>
{
	private LoadingStage loadingStage = LoadingStage.PREINIT;
	protected HashMap<String, T> entries = new HashMap<String, T>();
	protected BaseRegistry<T> instance;

	public void preInit()
	{
		initiateEntries();
		initiateModels();
		setLoadingStage(LoadingStage.INIT);
	}

	public void init()
	{
		initiateRecipes();
		setLoadingStage(LoadingStage.POSTINIT);
	}

	public void postInit()
	{
		setLoadingStage(LoadingStage.DONE);
	}

	public abstract void initiateEntries();

	public void initiateModels()
	{
		for(Map.Entry<String, T> entry : entries.entrySet())
		{
			if(entry.getValue() instanceof IHasModel)
			{
				ResourceLocation[] locations = ((IHasModel) entry.getValue()).getModelResourceLocations();
				for(int i = 0; i < locations.length; i++)
				{
					SafeModelLoader.loadItemModel(entry.getValue(), i, locations[i]);
				}
			}
		}
	}

	public void initiateRecipes()
	{
		for(Map.Entry<String, T> entry : entries.entrySet())
		{
			if(entry.getValue() instanceof IHasRecipe)
			{
				for(IRecipe recipe: ((IHasRecipe) entry.getValue()).getRecipes())
				{
					GameRegistry.addRecipe(recipe);
				}
			}
		}
	}

	public LoadingStage getLoadingStage()
	{
		return loadingStage;
	}

	public void setLoadingStage(LoadingStage stage)
	{
		if(stage.ordinal() < loadingStage.ordinal())
		{
			BoilerplateLib.getLogger().fatal("Stage should never be set to an earlier stage!");
		} else
		{
			this.loadingStage = stage;
		}
	}

	public static List<BaseRegistry> getAllRegistries()
	{
		List<BaseRegistry> registries = new ArrayList<BaseRegistry>();
		registries.add(ItemRegistry.getInstance());
		registries.add(BlockRegistry.getInstance());
		registries.add(EntityRegistry.getInstance());
		return registries;
	}
}
