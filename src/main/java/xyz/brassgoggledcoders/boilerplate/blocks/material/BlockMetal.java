package xyz.brassgoggledcoders.boilerplate.blocks.material;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import xyz.brassgoggledcoders.boilerplate.blocks.BlockSubBase;
import xyz.brassgoggledcoders.boilerplate.blocks.IBlockType;
import xyz.brassgoggledcoders.boilerplate.blocks.ItemSubBlock;
import xyz.brassgoggledcoders.boilerplate.client.models.ISimpleVariant;

public class BlockMetal extends BlockSubBase implements ISimpleVariant {
	public static final PropertyEnum<EnumBlockType> type = PropertyEnum.create("type", EnumBlockType.class);

	public BlockMetal() {
		super(Material.IRON);
		this.setHarvestLevel("pickaxe", 1);
		this.setUnlocalizedName("metal_block");
	}

	@Override
	public ItemBlock getItemBlockClass(Block block) {
		return new ItemSubBlock(block, EnumBlockType.names());
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(type).getMeta();
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, type);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(type, EnumBlockType.VALUES[meta]);
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List<ItemStack> itemList) {
		for(EnumBlockType resourceType : EnumBlockType.VALUES)
			itemList.add(new ItemStack(item, 1, resourceType.getMeta()));
	}

	public enum EnumBlockType implements IBlockType {
		COPPER(0), ZINC(1), BRASS(2), STEEL(3);

		public static final EnumBlockType[] VALUES = values();

		private final int meta;

		EnumBlockType(int meta) {
			this.meta = meta;
		}

		@Override
		public int getMeta() {
			return meta;
		}

		@Override
		public String getName() {
			return name().toLowerCase();
		}

		public static String[] names() {
			ArrayList<String> names = new ArrayList<String>();
			for(EnumBlockType element : VALUES)
				names.add(element.toString().toLowerCase());

			return names.toArray(new String[0]);
		}
	}

	@Override
	public Class<? extends IBlockType> getEnumToSwitch() {
		return BlockMetal.EnumBlockType.class;
	}
}
