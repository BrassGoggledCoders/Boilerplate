package xyz.brassgoggledcoders.boilerplate.lib.client;

import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.client.events.ClientEventsHandler;
import xyz.brassgoggledcoders.boilerplate.lib.client.events.ModelBakeHandler;
import xyz.brassgoggledcoders.boilerplate.lib.client.renderers.ISpecialRenderedItem;
import xyz.brassgoggledcoders.boilerplate.lib.client.renderers.ItemSpecialRenderer;
import xyz.brassgoggledcoders.boilerplate.lib.common.CommonProxy;
import xyz.brassgoggledcoders.boilerplate.lib.common.modcompat.CompatibilityHandler;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.ConfigRegistry;

/**
 * @author Surseance
 *
 */
public class ClientProxy extends CommonProxy
{
	@Override
	public void initCompatibilityHandler(CompatibilityHandler compatibilityHandler, FMLInitializationEvent event)
	{
		compatibilityHandler.clientInit(event);
		super.initCompatibilityHandler(compatibilityHandler, event);
	}

	@Override
	public String translate(String text)
	{
		return StatCollector.translateToLocal("boilerplate." + text);
	}

	@Override
	public void loadItemModel(Item item, int metadata, ResourceLocation resourceLocation)
	{
		ModelLoader.setCustomModelResourceLocation(item, metadata,
				new ModelResourceLocation(resourceLocation, "inventory"));
	}

	@Override
	public void addVariantName(Item item, String... variantNames)
	{
		ModelResourceLocation[] modelResourceLocations = new ModelResourceLocation[variantNames.length];
		for(int i = 0; i < modelResourceLocations.length; i++)
		{
			modelResourceLocations[i] = new ModelResourceLocation(BoilerplateLib.getMod().getPrefix() +
					variantNames[i]);
		}
		ModelBakery.registerItemVariants(item, modelResourceLocations);
	}

	@Override
	public void registerItemModelVariant(Item item, int metadata, String itemModelName)
	{
		ModelResourceLocation modelResourceLocation =
				new ModelResourceLocation(BoilerplateLib.getMod().getPrefix() + itemModelName);
		ClientHelper.getItemModelMesher().register(item, metadata, modelResourceLocation);
	}

	@Override
	@SuppressWarnings({"unchecked", "deprecation"})
	public void registerISpecialRendererItem(Item item)
	{
		ISpecialRenderedItem specialRenderItem = (ISpecialRenderedItem)item;
		ItemSpecialRenderer renderer = ((ISpecialRenderedItem) item).getSpecialRenderer();
		ClientRegistry.bindTileEntitySpecialRenderer(renderer.getTileClass(), renderer);
		int length = specialRenderItem.getResourceLocations().length;
		ModelResourceLocation[] modelLocations = new ModelResourceLocation[length];

		for(int i = 0; i < length; i++)
		{
			modelLocations[i] = new ModelResourceLocation(specialRenderItem.getResourceLocations()[i], "inventory");
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
		MinecraftForge.EVENT_BUS.register(ModelBakeHandler.getInstance());
	}
}
