package boilerplate.common.compathandler;

import java.util.ArrayList;

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

	public static void registerFMP(Block block)
	{
		blocksToRegister.add(block);
	}

	public static void doRegister()
	{
		for (int i = 0; i < blocksToRegister.size(); i++)
		{
			for (int i2 = 0; i2 < 15; i2++)
			{
				FMLInterModComms.sendMessage("ForgeMicroblock", "microMaterial", new ItemStack(blocksToRegister.get(i), 1, i2));
			}
		}
	}
}
