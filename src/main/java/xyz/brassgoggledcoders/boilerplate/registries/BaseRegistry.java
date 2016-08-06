package xyz.brassgoggledcoders.boilerplate.registries;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.IModAware;
import xyz.brassgoggledcoders.boilerplate.config.IConfigListener;
import xyz.brassgoggledcoders.boilerplate.items.IHasRecipe;

import java.util.HashMap;

public abstract class BaseRegistry<T> {
	protected IBoilerplateMod mod;
	protected IRegistryHolder registryHolder;
	protected BaseRegistry<T> instance;
	protected HashMap<String, T> entries = new HashMap<String, T>();
	private LoadingStage loadingStage = LoadingStage.PREINIT;

	public BaseRegistry(IBoilerplateMod mod, IRegistryHolder registryHolder) {
		this.mod = mod;
		this.registryHolder = registryHolder;
	}

	public void preInit() {
		this.entries.forEach((name, entry) -> {
			initiateEntry(name, entry);
			initiateModel(name, entry);
		});

		setLoadingStage(LoadingStage.INIT);
	}

	protected void initiateEntry(String name, T entry) {
		if(entry instanceof IConfigListener) {
			registryHolder.getConfigRegistry().addListener((IConfigListener) entry);
		}
		if(entry instanceof IModAware) {
			((IModAware) entry).setMod(this.mod);
		}
	}

	protected void initiateModel(String name, T entry) {
	}

	public void init() {
		this.entries.forEach(this::initiateRecipes);

		setLoadingStage(LoadingStage.POSTINIT);
	}

	public void postInit() {
		setLoadingStage(LoadingStage.DONE);
	}

	public void initiateRecipes(String name, T entry) {
		if(entry instanceof IHasRecipe) {
			for(IRecipe recipe : ((IHasRecipe) entry).getRecipes()) {
				GameRegistry.addRecipe(recipe);
			}
		}
	}

	public LoadingStage getLoadingStage() {
		return loadingStage;
	}

	public void setLoadingStage(LoadingStage stage) {
		if(stage.ordinal() < loadingStage.ordinal()) {
			this.mod.getLogger().fatal("Stage should never be set to an earlier stage!");
		} else {
			this.loadingStage = stage;
		}
	}
}
