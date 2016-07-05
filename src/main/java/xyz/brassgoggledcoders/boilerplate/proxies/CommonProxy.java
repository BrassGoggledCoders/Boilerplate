package xyz.brassgoggledcoders.boilerplate.proxies;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.client.models.IHasIgnoredVariants;
import xyz.brassgoggledcoders.boilerplate.module.IModule;
import xyz.brassgoggledcoders.boilerplate.module.IModuleProxy;
import xyz.brassgoggledcoders.boilerplate.utils.ClassLoading;

import javax.annotation.Nullable;

public abstract class CommonProxy {
	protected IBoilerplateMod mod;

	public String translate(String text) {
		return "";
	}

	public void registerBlockModels() {}

	public void loadItemModel(Item item, int metadata, ResourceLocation location) {}

	public void loadIgnoredVariants(IHasIgnoredVariants variants, Block block) { }

	public void loadAllItemModels(Item item, String[] locations) { }

	public void addVariantName(Item item, String... variantNames) {}

	public void registerItemModelVariant(Item item, int metadata, String itemModelName) {}

	public void registerISpecialRendererItem(Item item) {}

	public void registerEvents() {}

	public void setLexiconStack(ItemStack stack) {}

	public void initTESRLoader(ASMDataTable dataTable) { }

	public void registerTESR(String name, Item item, Class<? extends TileEntity> tileEntityClass) { }

	public void setMod(IBoilerplateMod mod) {
		this.mod = mod;
	}

	public abstract IModuleProxy getModuleProxy(IModule module);

	@Nullable
	protected IModuleProxy getModuleProxy(String path) {
		IModuleProxy moduleProxy = null;

		if(path != null && !path.isEmpty()) {
			moduleProxy = ClassLoading.createInstanceOf(IModuleProxy.class, path);
		}

		return moduleProxy;
	}
}
