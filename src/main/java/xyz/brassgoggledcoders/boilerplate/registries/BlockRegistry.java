package xyz.brassgoggledcoders.boilerplate.registries;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.blocks.BlockBase;
import xyz.brassgoggledcoders.boilerplate.blocks.IHasItemBlock;
import xyz.brassgoggledcoders.boilerplate.blocks.IHasTileEntity;

import java.util.Map;

public class BlockRegistry extends BaseRegistry<Block>
{
	public BlockRegistry(IBoilerplateMod mod, IRegistryHolder registryHolder)
	{
		super(mod, registryHolder);
	}

	@Override
	public void initiateEntries()
	{
		for(Map.Entry<String, Block> entry : entries.entrySet())
		{
			entry.getValue().setCreativeTab(mod.getCreativeTab());
			ResourceLocation blockName = entry.getValue().getRegistryName();
			GameRegistry.register(entry.getValue(), new ResourceLocation(mod.getPrefix(),  entry.getValue().getUnlocalizedName()));
			if(entry.getValue() instanceof IHasItemBlock)
			{
				GameRegistry.register(((IHasItemBlock)entry.getValue()).getItemBlockClass(entry.getValue()), blockName);
			}

			if(entry.getValue() instanceof IHasTileEntity)
			{
				GameRegistry.registerTileEntity(((IHasTileEntity)entry.getValue()).getTileEntityClass(),
						mod.getPrefix() + entry.getKey());
			}
		}
		super.initiateEntries();
	}

	public void registerAndCreateBasicBlock(Material mat, String name)
	{
		Block block = new BlockBase(mat);
		this.entries.put(name, block);
	}

	public void registerBlock(Block block)
	{
		String name = block.getUnlocalizedName();
		if(name.startsWith("tile."))
		{
			name = name.substring(5);
		}
		registerBlock(block, name);
	}

	public void registerBlock(Block block, String name)
	{
		this.entries.put(name, block);
	}

	public Block getBlock(String name)
	{
		return this.entries.get(name);
	}
}
