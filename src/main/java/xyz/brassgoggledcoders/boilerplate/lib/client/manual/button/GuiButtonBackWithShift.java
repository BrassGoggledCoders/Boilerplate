/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Mar 6, 2014, 6:35:32 PM (GMT)]
 */
package xyz.brassgoggledcoders.boilerplate.lib.client.manual.button;

import java.util.Arrays;
import java.util.List;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class GuiButtonBackWithShift extends GuiButtonBack {

	public GuiButtonBackWithShift(int par1, int par2, int par3) {
		super(par1, par2, par3);
	}

	@Override
	public List<String> getTooltip() {
		return Arrays.asList(StatCollector.translateToLocal("botaniamisc.back"), EnumChatFormatting.GRAY + StatCollector.translateToLocal("botaniamisc.clickToIndex"));
	}

}
