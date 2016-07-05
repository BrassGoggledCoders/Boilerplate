package xyz.brassgoggledcoders.boilerplate.client.models;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.blocks.IBlockType;

public class SafeModelLoader {
	public static void loadBlockModel(IBoilerplateMod mod, Block block) {
		loadBlockModel(mod, block, 0);
	}

	public static void loadBlockModel(IBoilerplateMod mod, Block block, int metadata) {
		loadBlockModel(mod, block, metadata, block.getUnlocalizedName().substring(5));
	}

	public static void loadBlockModel(IBoilerplateMod mod, Block block, int metadata, String override) {
		loadItemModel(mod, Item.getItemFromBlock(block), metadata, override);
	}

	public static void loadItemModel(IBoilerplateMod mod, Item item) {
		loadItemModel(mod, item, 0);
	}

	public static void loadItemModel(IBoilerplateMod mod, Item item, int metadata) {
		String name = item.getUnlocalizedName();
		if(name.startsWith("item.")) {
			name = name.substring(5);
		}
		loadItemModel(mod, item, metadata, name);
	}

	public static void loadItemModel(IBoilerplateMod mod, Item item, int metadata, String override) {
		loadItemModel(mod, item, metadata, new ResourceLocation(mod.getPrefix() + override));
	}

	public static void loadItemModel(IBoilerplateMod mod, Item item, int metadata, ResourceLocation resourceLocation) {
		mod.getBoilerplateProxy().loadItemModel(item, metadata, resourceLocation);
	}

	public static void loadItemModel(IBoilerplateMod mod, Object object, int metadata, String location) {
		Item item = null;
		if(object instanceof Item) {
			item = (Item) object;
		}
		else if(object instanceof Block) {
			item = Item.getItemFromBlock((Block) object);
		}

		if(item != null) {
			ResourceLocation resourceLocation = new ResourceLocation(mod.getPrefix() + location);
			mod.getBoilerplateProxy().loadItemModel(item, metadata, resourceLocation);
		}
	}

	public static void loadISimpleVariant(IBoilerplateMod mod, ISimpleVariant variant, Block block) {
		Item item = Item.getItemFromBlock(block);
		if(item != null) {
			for(IBlockType e : variant.getEnumToSwitch().getEnumConstants()) {
				String baseName = ForgeRegistries.BLOCKS.getKey(block).toString();
				String variantName = "type=" + e.getName();
				mod.getBoilerplateProxy().setCustomModelLocation(item, ((Enum<?>) e).ordinal(),
						new ModelResourceLocation(baseName, variantName));
			}
		}
	}

	public static void loadIgnoredVariants(IBoilerplateMod mod, IHasIgnoredVariants ignoredVariants, Block block) {
		mod.getBoilerplateProxy().loadIgnoredVariants(ignoredVariants, block);
	}

	public static void loadAllItemModels(IBoilerplateMod mod, IHasModel model, Item item) {
		String[] locations = model.getResourceLocations();
		mod.getBoilerplateProxy().loadAllItemModels(item, locations);
	}
}
