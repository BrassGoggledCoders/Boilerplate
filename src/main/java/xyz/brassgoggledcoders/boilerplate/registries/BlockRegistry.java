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

public class BlockRegistry extends BaseRegistry<Block> {
	public BlockRegistry(IBoilerplateMod mod, IRegistryHolder registryHolder) {
		super(mod, registryHolder);
	}

	@Override
	public void initiateModel(String name, Block entry) {
		if(entry instanceof IHasIgnoredVariants) {
			IHasIgnoredVariants block = (IHasIgnoredVariants) entry;
			SafeModelLoader.loadIgnoredVariants(mod, block, entry);
		}

		Item item = Item.getItemFromBlock(entry);

		// TODO Suppress missing inventory model errors
		if(entry instanceof ISimpleVariant) {
			SafeModelLoader.loadISimpleVariant(mod, (ISimpleVariant) entry, entry);
		} else {
			mod.getBoilerplateProxy().loadItemModel(item, 0, new ResourceLocation(mod.getID(), name), "inventory");
		}

		if(item instanceof IHasModel) {
			SafeModelLoader.loadAllItemModels(mod, (IHasModel) item, item);
		}
		super.initiateModel(name, entry);
	}

	@Override
	public void initiateEntry(String name, Block block) {
		ResourceLocation blockName = new ResourceLocation(mod.getID(), name);
		GameRegistry.register(block, blockName);

		if(block instanceof IHasItemBlock) {
			GameRegistry.register(((IHasItemBlock) block).getItemBlockClass(block), blockName);
		}

		if(block instanceof IHasTileEntity) {
			Class<? extends TileEntity> tileEntityClass = ((IHasTileEntity) block).getTileEntityClass();
			GameRegistry.registerTileEntity(tileEntityClass, mod.getPrefix() + name);
			if(block instanceof IHasTESR) {
				this.registryHolder.getTESRRegistry().addTESR(name, Item.getItemFromBlock(block), tileEntityClass);
			}
		}
		super.initiateEntry(name, block);
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
