package xyz.brassgoggledcoders.boilerplate.proxies;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import xyz.brassgoggledcoders.boilerplate.client.ClientHelper;
import xyz.brassgoggledcoders.boilerplate.client.events.ClientEventsHandler;
import xyz.brassgoggledcoders.boilerplate.client.models.IHasIgnoredVariants;
import xyz.brassgoggledcoders.boilerplate.client.models.SafeModelLoader;
import xyz.brassgoggledcoders.boilerplate.client.renderers.TESRLoader;
import xyz.brassgoggledcoders.boilerplate.client.renderers.custom.CustomItemRenderRegistry;
import xyz.brassgoggledcoders.boilerplate.client.renderers.custom.IHasItemRenderHandler;
import xyz.brassgoggledcoders.boilerplate.module.IModule;
import xyz.brassgoggledcoders.boilerplate.module.IModuleProxy;

public class ClientProxy extends CommonProxy {
	private TESRLoader tesrLoader;

	public static void registerFluidModel(Block fluidBlock, final ModelResourceLocation loc) {
		Item fluidItem = Item.getItemFromBlock(fluidBlock);
		if(fluidItem != null) {
			ModelBakery.registerItemVariants(fluidItem);
			ModelLoader.setCustomMeshDefinition(fluidItem, stack -> loc);
			ModelLoader.setCustomStateMapper(fluidBlock, new StateMapperBase() {
				@Override
				@Nonnull
				protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
					return loc;
				}
			});
		}

	}

	@Override
	public void loadAllItemModels(Item item, String[] locations) {
		List<ItemStack> allSubItems = new ArrayList<>();
		item.getSubItems(item, item.getCreativeTab(), allSubItems);
		int locationsIndex = 0;
		for(int i = 0; i < allSubItems.size(); i++) {
			SafeModelLoader.loadItemModel(mod, item, i, locations[locationsIndex]);
			locationsIndex++;
			if(locationsIndex >= locations.length) {
				locationsIndex = 0;
			}
		}
	}

	@Override
	public String translate(String text) {
		return I18n.format("boilerplate." + text);
	}

	@Override
	public void loadItemModel(Item item, int metadata, ResourceLocation resourceLocation) {
		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(resourceLocation, null));
	}

	@Override
	public void loadItemModel(Item item, int metadata, ResourceLocation resourceLocation, String variant) {
		ModelLoader.setCustomModelResourceLocation(item, metadata,
				new ModelResourceLocation(resourceLocation, variant));
	}

	@Override
	public void setCustomModelLocation(Item item, int metadata, ModelResourceLocation modelResourceLocation) {
		ModelLoader.setCustomModelResourceLocation(item, metadata, modelResourceLocation);
	}

	@Override
	public void loadIgnoredVariants(IHasIgnoredVariants variants, Block block) {

	}

	@Override
	public void addVariantName(Item item, String... variantNames) {
		ModelResourceLocation[] modelResourceLocations = new ModelResourceLocation[variantNames.length];
		for(int i = 0; i < modelResourceLocations.length; i++) {
			modelResourceLocations[i] = new ModelResourceLocation(mod.getPrefix() + variantNames[i]);
		}
		ModelBakery.registerItemVariants(item, modelResourceLocations);
	}

	@Override
	public void registerItemModelVariant(Item item, int metadata, String itemModelName) {
		ModelResourceLocation modelResourceLocation = new ModelResourceLocation(mod.getPrefix() + itemModelName);
		ClientHelper.getItemModelMesher().register(item, metadata, modelResourceLocation);
	}

	@Override
	public void registerItemRenderHandler(Item item) {
		CustomItemRenderRegistry.addCustomRenderer(this.mod, item, (IHasItemRenderHandler) item);
	}

	@Override
	public void initTESRLoader(ASMDataTable dataTable) {
		tesrLoader = new TESRLoader(dataTable);
	}

	@Override
	public void addOBJDomain() {
		OBJLoader.INSTANCE.addDomain(mod.getID());
	}

	@Override
	@SuppressWarnings("deprecation")
	public void registerTESR(String name, Item item, Class<? extends TileEntity> tileEntityClass) {
		tesrLoader.registerTESRToTile(name, tileEntityClass);
		List<ItemStack> allSubItems = new ArrayList<>();
		item.getSubItems(item, item.getCreativeTab(), allSubItems);
		allSubItems.forEach(itemStack -> ForgeHooksClient.registerTESRItemStack(itemStack.getItem(),
				itemStack.getItemDamage(), tileEntityClass));
	}

	@Override
	public IModuleProxy getModuleProxy(IModule module) {
		return this.getModuleProxy(module.getClientProxyPath());
	}

	@Override
	public void registerEvents() {
		MinecraftForge.EVENT_BUS.register(new ClientEventsHandler());
	}
}
