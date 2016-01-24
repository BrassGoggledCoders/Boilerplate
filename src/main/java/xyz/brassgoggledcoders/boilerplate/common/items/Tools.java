package xyz.brassgoggledcoders.boilerplate.common.items;

import xyz.brassgoggledcoders.boilerplate.common.utils.ItemStackUtils;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SkySom
 */
public class Tools
{
	public static boolean toolsSetup = false;
	public static HashMap<String, Class> toolClasses;

	public static boolean isItemATool(ItemStack itemStack)
	{
		boolean isTool = false;
		if(ItemStackUtils.isItemNonNull(itemStack))
		{
			if(itemStack.getItem().getToolClasses(itemStack).contains("IE_HAMMER"))
			{
				isTool = true;
			}

			if(!isTool)
			{
				if(!toolsSetup)
				{
					setupToolItems();
				}

				for(Map.Entry<String, Class> entry : toolClasses.entrySet())
				{
					if(ItemStackUtils.isItemInstanceOf(itemStack, entry.getValue())) {
						isTool = true;
						break;
					}
				}
			}
		}
		return isTool;
	}

	public static void setupToolItems()
	{
		HashMap<String, String> classStrings = new HashMap<String, String>();
		classStrings.put("BC Wrench", "buildcraft.api.tools.IToolWrench");
		classStrings.put("TE Hammer", "cofh.api.item.IToolHammer");
		classStrings.put("RC Crowbar", "mods.railcraft.api.core.items.IToolCrowbar");
		classStrings.put("MFR Hammer", "powercrystals.minefactoryreloaded.api.IMFRHammer");
		classStrings.put("AE Wrench", "appeng.api.implementations.items.IAEWrench");
		toolClasses = new HashMap<String, Class>();

		for(Map.Entry<String, String> entry : classStrings.entrySet())
		{
			try
			{
				Class toolClass = Class.forName(entry.getValue());
				if(toolClass != null)
				{
					toolClasses.put(entry.getKey(), toolClass);
				}
			}
			catch(ClassNotFoundException e)
			{
				//This will happen whenever a mod isn't installed. Pretty normal.
			}
		}

		toolsSetup = true;
	}
}
