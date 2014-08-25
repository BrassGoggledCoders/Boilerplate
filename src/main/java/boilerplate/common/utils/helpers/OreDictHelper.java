/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.common.utils.helpers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author warlordjones
 * 
 */
public class OreDictHelper
{
	public static void registerOreWithAlts(String[] names, ItemStack ore)
	{
		for(String name : names)
			OreDictionary.registerOre(name, ore);
	}
}
