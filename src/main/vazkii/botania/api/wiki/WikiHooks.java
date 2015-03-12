/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 * 
 * File Created @ [Sep 2, 2014, 6:05:03 PM (GMT)]
 */
package vazkii.botania.api.wiki;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;

public class WikiHooks {

	private static final IWikiProvider FALLBACK_PROVIDER = new SimpleWikiProvider("FTB Wiki", "http://wiki.feed-the-beast.com/%s");

	private static final Map<String, IWikiProvider> modWikis = new HashMap();

	public static IWikiProvider getWikiFor(Block block) {
		UniqueIdentifier mod = GameRegistry.findUniqueIdentifierFor(block);
		return getWikiFor(mod.modId.toLowerCase());
	}

	public static IWikiProvider getWikiFor(String mod) {
		if(!modWikis.containsKey(mod))
			modWikis.put(mod, FALLBACK_PROVIDER);

		return modWikis.get(mod);
	}

	public static void registerModWiki(String mod, IWikiProvider provider) {
		modWikis.put(mod.toLowerCase(), provider);
	}

}
