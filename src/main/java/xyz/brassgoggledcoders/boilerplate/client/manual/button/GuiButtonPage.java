/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * File Created @ [Jan 16, 2014, 4:52:06 PM (GMT)]
 */
package xyz.brassgoggledcoders.boilerplate.client.manual.button;

import java.util.Collections;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import xyz.brassgoggledcoders.boilerplate.client.manual.GuiLexicon;
import xyz.brassgoggledcoders.boilerplate.client.manual.RenderHelper;

public class GuiButtonPage extends GuiButtonLexicon {

	boolean right;

	public GuiButtonPage(int par1, int par2, int par3, boolean right) {
		super(par1, par2, par3, 18, 10, "");
		this.right = right;
	}

	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
		if(enabled) {
			hovered = par2 >= xPosition && par3 >= yPosition && par2 < xPosition + width && par3 < yPosition + height;
			int k = getHoverState(hovered);

			par1Minecraft.renderEngine.bindTexture(GuiLexicon.texture);
			GlStateManager.color(1F, 1F, 1F, 1F);
			drawTexturedModalRect(xPosition, yPosition, k == 2 ? 18 : 0, right ? 180 : 190, 18, 10);

			if(k == 2)
				RenderHelper.renderTooltip(par2, par3, Collections
						.singletonList(I18n.format(right ? "botaniamisc.nextPage" : "botaniamisc.prevPage")));
		}
	}

}