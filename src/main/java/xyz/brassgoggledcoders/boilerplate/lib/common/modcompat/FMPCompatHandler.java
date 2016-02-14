package xyz.brassgoggledcoders.boilerplate.lib.common.modcompat;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fml.common.event.FMLInterModComms;

/**
 * @author tterrag1098
 *
 */
public class FMPCompatHandler
{
	static ArrayList<Block> blocksToRegister = new ArrayList();
	static HashMap<Block, Integer> metaBlocksToRegister = new HashMap();

	public static void registerFMP(Block block)
	{
		blocksToRegister.add(block);
	}

	public static void registerMetaFMP(Block block, int size)
	{
		metaBlocksToRegister.put(block, size);
	}

	public static void doRegister()
	{
		for (Block block : blocksToRegister)
		{
			FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", new ItemStack(block));
		}
		for (Block block : metaBlocksToRegister.keySet())
		{
			for (int meta = 0; meta < metaBlocksToRegister.get(block).intValue(); meta++)
			{
				FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", new ItemStack(block, 1, meta));
			}
		}
	}
}
