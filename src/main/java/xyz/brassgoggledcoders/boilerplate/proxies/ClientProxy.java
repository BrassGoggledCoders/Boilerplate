package xyz.brassgoggledcoders.boilerplate.proxies;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import xyz.brassgoggledcoders.boilerplate.Boilerplate;
import xyz.brassgoggledcoders.boilerplate.MaterialsModule;
import xyz.brassgoggledcoders.boilerplate.blocks.material.BlockMetal;
import xyz.brassgoggledcoders.boilerplate.blocks.material.BlockMetalOre;
import xyz.brassgoggledcoders.boilerplate.client.ClientHelper;
import xyz.brassgoggledcoders.boilerplate.client.events.ClientEventsHandler;
import xyz.brassgoggledcoders.boilerplate.client.events.ModelBakeHandler;
import xyz.brassgoggledcoders.boilerplate.client.manual.ClientTickHandler;
import xyz.brassgoggledcoders.boilerplate.client.manual.GuiLexicon;
import xyz.brassgoggledcoders.boilerplate.client.renderers.ISpecialRenderedItem;
import xyz.brassgoggledcoders.boilerplate.client.renderers.ItemSpecialRenderStore;
import xyz.brassgoggledcoders.boilerplate.client.renderers.ItemSpecialRenderer;

/**
 * @author Surseance
 */
public class ClientProxy extends CommonProxy
{
	@Override
	public void registerBlockModels()
	{
		if(Boilerplate.instance.getModuleHandler().isModuleEnabled("Materials"))
		{
			registerVariantsDefaulted(MaterialsModule.metal_ore, BlockMetalOre.EnumBlockType.class, "type");
			registerVariantsDefaulted(MaterialsModule.metal_block, BlockMetal.EnumBlockType.class, "type");
		}
	}

	@Override
	public String translate(String text)
	{
		return I18n.format("boilerplate." + text);
	}

	@Override
	public void loadItemModel(Item item, int metadata, ResourceLocation resourceLocation)
	{
		ModelResourceLocation modelResourceLocation = new ModelResourceLocation(resourceLocation, "inventory");
		ModelLoader.setCustomModelResourceLocation(item, metadata, modelResourceLocation);
	}

	@Override
	public void addVariantName(Item item, String... variantNames)
	{
		ModelResourceLocation[] modelResourceLocations = new ModelResourceLocation[variantNames.length];
		for(int i = 0; i < modelResourceLocations.length; i++)
		{
			modelResourceLocations[i] = new ModelResourceLocation(mod.getPrefix() + variantNames[i]);
		}
		ModelBakery.registerItemVariants(item, modelResourceLocations);
	}

	@Override
	public void registerItemModelVariant(Item item, int metadata, String itemModelName)
	{
		ModelResourceLocation modelResourceLocation = new ModelResourceLocation(mod.getPrefix() + itemModelName);
		ClientHelper.getItemModelMesher().register(item, metadata, modelResourceLocation);
	}

	@Override
	@SuppressWarnings({"unchecked", "deprecation"})
	public void registerISpecialRendererItem(Item item)
	{
		ISpecialRenderedItem specialRenderItem = (ISpecialRenderedItem) item;
		ItemSpecialRenderer renderer = ItemSpecialRenderStore.getItemSpecialRenderer(((ISpecialRenderedItem) item));
		ClientRegistry.bindTileEntitySpecialRenderer(renderer.getTileClass(), renderer);
		int length = specialRenderItem.getResourceLocations().length;
		ModelResourceLocation[] modelLocations = new ModelResourceLocation[length];

		for(int i = 0; i < length; i++)
		{
			modelLocations[i] = new ModelResourceLocation(mod.getPrefix() + specialRenderItem.getResourceLocations()[i],
					"inventory");
		}

		for(int i = 0; i < length; i++)
		{
			ModelBakeHandler.getInstance().registerModelToSwap(modelLocations[i], renderer);
			ForgeHooksClient.registerTESRItemStack(item, i, renderer.getTileClass());
		}
	}

	@Override
	public void registerEvents()
	{
		MinecraftForge.EVENT_BUS.register(new ClientEventsHandler());
		MinecraftForge.EVENT_BUS.register(new ClientTickHandler()); // TODO
		MinecraftForge.EVENT_BUS.register(ModelBakeHandler.getInstance());
	}

	@Override
	public void setLexiconStack(ItemStack stack)
	{
		GuiLexicon.stackUsed = stack;
	}

	public static <T extends Enum<T> & IStringSerializable> void registerVariantsDefaulted(Block b, Class<T> enumclazz,
			String variantHeader)
	{
		Item item = Item.getItemFromBlock(b);
		for(T e : enumclazz.getEnumConstants())
		{
			String baseName = ForgeRegistries.BLOCKS.getKey(b).toString();
			String variantName = variantHeader + "=" + e.getName();
			ModelLoader.setCustomModelResourceLocation(item, e.ordinal(),
					new ModelResourceLocation(baseName, variantName));
		}
	}
}
