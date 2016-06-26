package xyz.brassgoggledcoders.boilerplate.registries;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMap.Builder;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.blocks.BlockBase;
import xyz.brassgoggledcoders.boilerplate.blocks.IBlockType;
import xyz.brassgoggledcoders.boilerplate.blocks.IHasItemBlock;
import xyz.brassgoggledcoders.boilerplate.blocks.IHasTileEntity;
import xyz.brassgoggledcoders.boilerplate.client.models.IHasIgnoredVariants;
import xyz.brassgoggledcoders.boilerplate.client.models.ISimpleVariant;

public class BlockRegistry extends BaseRegistry<Block> {
	public BlockRegistry(IBoilerplateMod mod, IRegistryHolder registryHolder) {
		super(mod, registryHolder);
	}

	@Override
	public void initiateModels() {
		for(Map.Entry<String, Block> entry : entries.entrySet()) {
			if(entry.getValue() instanceof IHasIgnoredVariants) {
				IHasIgnoredVariants block = (IHasIgnoredVariants) entry.getValue();
				Builder builder = new StateMap.Builder();
				for(IProperty prop : block.getIgnoredVariants()) {
					builder.ignore(prop);
				}
				ModelLoader.setCustomStateMapper(entry.getValue(), builder.build());
			}
			// TODO Suppress missing inventory model errors
			if(entry.getValue() instanceof ISimpleVariant) {
				ISimpleVariant var = (ISimpleVariant) entry.getValue();
				Item item = Item.getItemFromBlock(entry.getValue());
				for(IBlockType e : var.getEnumToSwitch().getEnumConstants()) {
					String baseName = ForgeRegistries.BLOCKS.getKey(entry.getValue()).toString();
					String variantName = "type=" + e.getName();
					ModelLoader.setCustomModelResourceLocation(item, ((Enum) e).ordinal(),
							new ModelResourceLocation(baseName, variantName));
				}
			}
		}
		super.initiateModels();
	}

	@Override
	public void initiateEntries() {
		for(Map.Entry<String, Block> entry : entries.entrySet()) {
			ResourceLocation blockName = new ResourceLocation(mod.getID(), entry.getKey());
			GameRegistry.register(entry.getValue(), blockName);

			if(entry.getValue() instanceof IHasItemBlock) {
				GameRegistry.register(((IHasItemBlock) entry.getValue()).getItemBlockClass(entry.getValue()),
						blockName);
			}

			if(entry.getValue() instanceof IHasTileEntity) {
				GameRegistry.registerTileEntity(((IHasTileEntity) entry.getValue()).getTileEntityClass(),
						mod.getPrefix() + entry.getKey());
			}
		}
		super.initiateEntries();
	}

	public void registerAndCreateBasicBlock(Material mat, String name) {
		Block block = new BlockBase(mat);
		this.entries.put(name, block);
	}

	public void registerBlock(Block block) {
		String name = block.getUnlocalizedName();
		if(name.startsWith("tile.")) {
			name = name.substring(5);
		}
		registerBlock(block, name);
	}

	public void registerBlock(Block block, String name) {
		this.entries.put(name, block);
	}

	public Block getBlock(String name) {
		return this.entries.get(name);
	}
}
