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
