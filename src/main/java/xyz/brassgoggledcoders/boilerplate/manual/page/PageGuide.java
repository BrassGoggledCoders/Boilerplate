/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Sep 3, 2014, 9:41:47 PM (GMT)]
 */
package xyz.brassgoggledcoders.boilerplate.manual.page;

import java.awt.Desktop;
import java.net.URI;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import xyz.brassgoggledcoders.boilerplate.manual.IGuiLexiconEntry;

public class PageGuide extends PageText {

	GuiButton button;

	public PageGuide(String unlocalizedName) {
		super(unlocalizedName);
	}

	@Override
	public void onOpened(IGuiLexiconEntry gui) {
		button = new GuiButton(101, gui.getLeft() + 30, gui.getTop() + gui.getHeight() - 50, gui.getWidth() - 60, 20, I18n
				.format("botaniamisc.playVideo"));
		gui.getButtonList().add(button);
	}

	@Override
	public void onClosed(IGuiLexiconEntry gui) {
		gui.getButtonList().remove(button);
	}

	@Override
	public void onActionPerformed(IGuiLexiconEntry gui, GuiButton button) {
		if(button == this.button && Desktop.isDesktopSupported())
			try {
				Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=rx0xyejC6fI"));
				if(Math.random() < 0.01)
					Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			} catch(Exception e) { }
	}

}
