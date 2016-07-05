/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * File Created @ [Jan 14, 2014, 9:54:21 PM (GMT)]
 */
package xyz.brassgoggledcoders.boilerplate.client.manual.button;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import xyz.brassgoggledcoders.boilerplate.client.manual.GuiLexicon;
import xyz.brassgoggledcoders.boilerplate.client.manual.RenderHelper;

public class GuiButtonBack extends GuiButtonLexicon {

	public GuiButtonBack(int par1, int par2, int par3) {
		super(par1, par2, par3, 18, 9, "");
	}

	@Override
	public void drawButton(@Nonnull Minecraft minecraft, int par2, int par3) {
		if(enabled) {
			hovered = par2 >= xPosition && par3 >= yPosition && par2 < xPosition + width && par3 < yPosition + height;
			int k = getHoverState(hovered);

			minecraft.renderEngine.bindTexture(GuiLexicon.texture);
			GlStateManager.color(1F, 1F, 1F, 1F);
			drawTexturedModalRect(xPosition, yPosition, 36, k == 2 ? 180 : 189, 18, 9);

			List<String> tooltip = getTooltip();
			int tooltipY = (tooltip.size() - 1) * 10;
			if(k == 2)
				RenderHelper.renderTooltip(par2, par3 + tooltipY, tooltip);
		}
	}

	public List<String> getTooltip() {
		return Collections.singletonList(I18n.format("botaniamisc.back"));
	}

}