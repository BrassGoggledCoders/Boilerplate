/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package xyz.brassgoggledcoders.boilerplate.common.modcompat;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;

import java.util.ArrayList;
import java.util.HashMap;

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
