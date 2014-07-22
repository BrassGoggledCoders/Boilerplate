package boilerplate.common.utils.helpers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHelper
{
	public static void registerOreWithAlts(String[] names, ItemStack ore)
	{
		for(int i=0; i<names.length; i++)
		OreDictionary.registerOre(names[i], ore);
	}
}
