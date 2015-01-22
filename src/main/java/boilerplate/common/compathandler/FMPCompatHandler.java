package boilerplate.common.compathandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.event.FMLInterModComms;

/**
 * @author tterrag1098
 * 
 */
public class FMPCompatHandler
{
	static ArrayList<Block> blocksToRegister = new ArrayList();
	static Map<Block, Integer> metaBlocksToRegister = new HashMap();

	public static void registerFMP(Block block)
	{
		blocksToRegister.add(block);
	}

	public static void registerFMP(Block block, int maxMeta)
	{
		metaBlocksToRegister.put(block, maxMeta);
	}

	public static void doRegister()
	{
		for (int i = 0; i < blocksToRegister.size(); i++)
			FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", new ItemStack(blocksToRegister.get(i), 1));
	}

	public static void doRegisterMeta()
	{
		Iterator it = metaBlocksToRegister.entrySet().iterator();
		while (it.hasNext())
		{
			Map.Entry pairs = (Map.Entry) it.next();
			for (int i = 0; i < (int) pairs.getValue(); i++)
				FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", new ItemStack((Block) pairs.getKey(), 1, i));
			it.remove();
		}
	}
}
