package xyz.brassgoggledcoders.boilerplate.lib.common.registries;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.BaseBlock;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.BaseTEBlock;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.IHasItemBlock;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.IHasTileEntity;

import java.util.HashMap;
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
			if(entry.getValue() instanceof IHasItemBlock)
			{
				GameRegistry.registerBlock(entry.getValue(), ((IHasItemBlock)entry.getValue()).getItemBlockClass(),
						entry.getKey());
			} else
			{
				GameRegistry.registerBlock(entry.getValue(), entry.getKey());
			}

			if(entry.getValue() instanceof IHasTileEntity)
			{
				GameRegistry.registerTileEntity(((IHasTileEntity) entry.getValue()).getTileEntityClass(), entry.getKey());
			}
		}
	}

	public static void registerAndCreateBasicBlock(Material mat, String name)
	{
		Block block = new BaseBlock(mat);
		getInstance().entries.put(name, block);
	}

	public static void regsterBlock(Block block)
	{
		String name = block.getUnlocalizedName();
		if(name.startsWith("block."))
		{
			name = name.substring(6);
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
