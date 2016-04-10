/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Mar 2, 2015, 6:27:58 PM (GMT)]
 */
package xyz.brassgoggledcoders.boilerplate.lib.client.manual;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import xyz.brassgoggledcoders.boilerplate.lib.common.manual.ILexicon;
import xyz.brassgoggledcoders.boilerplate.lib.common.manual.LexiconEntry;

public class GuiLexiconHistory extends GuiLexiconIndex {

	public static List<LexiconEntry> history = new ArrayList<>();

	public GuiLexiconHistory() {
		super(null);
		title = StatCollector.translateToLocal("botaniamisc.historyLong");
	}

	@Override
	void buildEntries() {
		entriesToDisplay.clear();
		ILexicon lex = (ILexicon) stackUsed.getItem();
		for(int i = history.size() - 1; i >= 0; i--) {
			LexiconEntry entry = history.get(i);
			if(StatCollector.translateToLocal(entry.getUnlocalizedName()).toLowerCase().contains(searchField.getText().toLowerCase().trim()))
				entriesToDisplay.add(entry);
		}
	}

	public static void visit(LexiconEntry entry) {
		if(history.contains(entry))
			history.remove(entry);
		history.add(entry);
	}

	@Override
	public GuiLexicon copy() {
		return new GuiLexiconHistory();
	}

	@Override
	public void load(NBTTagCompound cmp) {
		// NO-OP
	}

	@Override
	public String getNotesKey() {
		return "history";
	}

}
