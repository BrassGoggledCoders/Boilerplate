package xyz.brassgoggledcoders.boilerplate.registries;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.IModAware;
import xyz.brassgoggledcoders.boilerplate.client.models.IHasModel;
import xyz.brassgoggledcoders.boilerplate.client.models.SafeModelLoader;
import xyz.brassgoggledcoders.boilerplate.config.IConfigListener;
import xyz.brassgoggledcoders.boilerplate.items.IHasRecipe;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseRegistry<T>
{
	private LoadingStage loadingStage = LoadingStage.PREINIT;
	protected IBoilerplateMod mod;
	protected IRegistryHolder registryHolder;
	HashMap<String, T> entries = new HashMap<String, T>();
	protected BaseRegistry<T> instance;

	public BaseRegistry(IBoilerplateMod mod, IRegistryHolder registryHolder)
	{
		this.mod = mod;
		this.registryHolder = registryHolder;
	}

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

	public void initiateEntries()
	{
		for(Map.Entry<String, T> entry: entries.entrySet())
		{
			if(entry.getValue() instanceof IConfigListener)
			{
				registryHolder.getConfigRegistry().addListener((IConfigListener) entry.getValue());
			}
			if(entry.getValue() instanceof IModAware)
			{
				((IModAware) entry.getValue()).setMod(this.mod);
			}
		}
	}

	public void initiateModels()
	{
		for(Map.Entry<String, T> entry : entries.entrySet())
		{
			if(entry.getValue() instanceof IHasModel)
			{
				String[] locations = ((IHasModel) entry.getValue()).getResourceLocations();
				for(int i = 0; i < locations.length; i++)
				{
					SafeModelLoader.loadItemModel(mod, entry.getValue(), i, locations[i]);
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
			this.mod.getLogger().fatal("Stage should never be set to an earlier stage!");
		} else
		{
			this.loadingStage = stage;
		}
	}
}
