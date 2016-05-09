package xyz.brassgoggledcoders.boilerplate.lib.common.registries;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.BlockBase;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.IHasItemBlock;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.IHasTileEntity;

import java.util.Map;

public class BlockRegistry extends BaseRegistry<Block>
{
	private static BlockRegistry instance;

	public static BlockRegistry getInstance()
	{
		if(instance == null)
		{
			instance = new BlockRegistry();
		}
		return instance;
	}

	@Override
	public void initiateEntries()
	{
		for(Map.Entry<String, Block> entry : entries.entrySet())
		{
			ResourceLocation blockName = entry.getValue().getRegistryName();
			GameRegistry.register(entry.getValue(), blockName);
			if(entry.getValue() instanceof IHasItemBlock)
			{
				GameRegistry.register(((IHasItemBlock)entry.getValue()).getItemBlockClass(entry.getValue()), blockName);
			}

			if(entry.getValue() instanceof IHasTileEntity)
			{
				GameRegistry.registerTileEntity(((IHasTileEntity)entry.getValue()).getTileEntityClass(),
						BoilerplateLib.getMod().getPrefix() + entry.getKey());
			}
		}
		super.initiateEntries();
	}

	public static void registerAndCreateBasicBlock(Material mat, String name)
	{
		Block block = new BlockBase(mat);
		getInstance().entries.put(name, block);
	}

	public static void registerBlock(Block block)
	{
		String name = block.getUnlocalizedName();
		if(name.startsWith("tile."))
		{
			name = name.substring(5);
		}
		registerBlock(block, name);
	}

	public static void registerBlock(Block block, String name)
	{
		getInstance().entries.put(name, block);
	}

	public static Block getBlock(String name)
	{
		return getInstance().entries.get(name);
	}
}
