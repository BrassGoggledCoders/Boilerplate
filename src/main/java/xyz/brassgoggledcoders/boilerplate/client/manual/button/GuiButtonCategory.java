/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * File Created @ [Oct 18, 2014, 4:00:30 PM (GMT)]
 */
package xyz.brassgoggledcoders.boilerplate.client.manual.button;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import xyz.brassgoggledcoders.boilerplate.client.manual.GuiLexicon;
import xyz.brassgoggledcoders.boilerplate.manual.LexiconCategory;
import xyz.brassgoggledcoders.boilerplate.utils.Utils;

public class GuiButtonCategory extends GuiButtonLexicon {

	private static final ResourceLocation stencilResource =
			new ResourceLocation(Utils.getCurrentMod().getPrefix() + "textures/gui/manual/categories/");

	static boolean boundStencil = false;

	GuiLexicon gui;
	LexiconCategory category;
	ResourceLocation resource = null;
	float ticksHovered = 0F;
	float time = 12F;

	int activeTex = 0;

	public GuiButtonCategory(int id, int x, int y, GuiLexicon gui, LexiconCategory category) {
		super(id, x, y, 16, 16, "");
		this.gui = gui;
		this.category = category;
	}

	@Override
	public void drawButton(Minecraft mc, int mx, int my) {
		boolean inside = mx >= xPosition && my >= yPosition && mx < xPosition + width && my < yPosition + height;
		if(inside)
			ticksHovered = Math.min(time, ticksHovered + gui.timeDelta);
		else
			ticksHovered = Math.max(0F, ticksHovered - gui.timeDelta);

		if(resource == null)
			resource = category.getIcon();

		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		GlStateManager.color(1F, 1F, 1F, 1F);

		if(!boundStencil) { // Allow for the texture manager to take care of the ResourceLocation before we use it
							// directly with gl
			mc.renderEngine.bindTexture(stencilResource);
			boundStencil = true;
		}
		mc.renderEngine.bindTexture(resource);
		GlStateManager.popMatrix();

		if(inside)
			gui.categoryHighlight = I18n.format(getTooltipText());
	}

	String getTooltipText() {
		if(category == null)
			return "botaniamisc.lexiconIndex";
		return category.getUnlocalizedName();
	}

	public LexiconCategory getCategory() {
		return category;
	}

}
