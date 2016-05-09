/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Jan 14, 2014, 6:15:28 PM (GMT)]
 */
package xyz.brassgoggledcoders.boilerplate.lib.common.manual;

import java.util.ArrayList;
import java.util.List;

public final class BotaniaAPI {

	private static List<LexiconCategory> categories = new ArrayList<>();
	private static List<LexiconEntry> allEntries = new ArrayList<>();
	
	public static List<LexiconCategory> getAllCategories()
	{
		return categories;
	}
	public static List<LexiconEntry> getAllEntries()
	{
		return allEntries;
	}
}
