package xyz.brassgoggledcoders.boilerplate.registries;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.blocks.BlockBase;
import xyz.brassgoggledcoders.boilerplate.blocks.IHasItemBlock;
import xyz.brassgoggledcoders.boilerplate.blocks.IHasTESR;
import xyz.brassgoggledcoders.boilerplate.blocks.IHasTileEntity;
import xyz.brassgoggledcoders.boilerplate.client.models.IHasIgnoredVariants;
import xyz.brassgoggledcoders.boilerplate.client.models.IHasModel;
import xyz.brassgoggledcoders.boilerplate.client.models.ISimpleVariant;
import xyz.brassgoggledcoders.boilerplate.client.models.SafeModelLoader;
import xyz.brassgoggledcoders.boilerplate.client.renderers.ISpecialRenderedItem;

import java.util.Map;

public class BlockRegistry extends BaseRegistry<Block> {
	public BlockRegistry(IBoilerplateMod mod, IRegistryHolder registryHolder) {
		super(mod, registryHolder);
	}

	@Override
	public void initiateModels() {
		for(Map.Entry<String, Block> entry : entries.entrySet()) {
			if(entry.getValue() instanceof IHasIgnoredVariants) {
				IHasIgnoredVariants block = (IHasIgnoredVariants) entry.getValue();
				SafeModelLoader.loadIgnoredVariants(mod, block, entry.getValue());
			}
			// TODO Suppress missing inventory model errors
			if(entry.getValue() instanceof ISimpleVariant) {
				SafeModelLoader.loadISimpleVariant(mod, (ISimpleVariant)entry.getValue(), entry.getValue());
			}

			Item item = Item.getItemFromBlock(entry.getValue());
			if(item instanceof IHasModel) {
				SafeModelLoader.loadAllItemModels(mod, (IHasModel)item, item);
				if(item instanceof ISpecialRenderedItem) {
					mod.getBoilerplateProxy().registerISpecialRendererItem(item);
				}
			}
		}
		super.initiateModels();
	}

	@Override
	public void initiateEntries() {
		for(Map.Entry<String, Block> entry : entries.entrySet()) {
			Block block = entry.getValue();
			ResourceLocation blockName = new ResourceLocation(mod.getID(), entry.getKey());
			GameRegistry.register(entry.getValue(), blockName);

			if(block instanceof IHasItemBlock) {
				GameRegistry.register(((IHasItemBlock) block).getItemBlockClass(block), blockName);
			}

			if(block instanceof IHasTileEntity) {
				Class<? extends TileEntity> tileEntityClass = ((IHasTileEntity)block).getTileEntityClass();
				GameRegistry.registerTileEntity(tileEntityClass, mod.getPrefix() + entry.getKey());
				if(entry.getValue() instanceof IHasTESR) {
					this.registryHolder.getTESRRegistry().addTESR(entry.getKey(), Item.getItemFromBlock(block),
							tileEntityClass);
				}
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
