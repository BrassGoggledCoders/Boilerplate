/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Feb 8, 2014, 2:46:36 PM (GMT)]
 */
package xyz.brassgoggledcoders.boilerplate.manual.page;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import xyz.brassgoggledcoders.boilerplate.client.manual.GuiLexiconEntry;
import xyz.brassgoggledcoders.boilerplate.manual.IGuiLexiconEntry;
import xyz.brassgoggledcoders.boilerplate.manual.ILexicon;
import xyz.brassgoggledcoders.boilerplate.manual.LexiconPage;
import xyz.brassgoggledcoders.boilerplate.manual.LexiconRecipeMappings;
import xyz.brassgoggledcoders.boilerplate.manual.LexiconRecipeMappings.EntryData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PageRecipe extends LexiconPage {

	int relativeMouseX, relativeMouseY;
	ItemStack tooltipStack, tooltipContainerStack;
	boolean tooltipEntry;

	static boolean mouseDownLastTick = false;

	public PageRecipe(String unlocalizedName) {
		super(unlocalizedName);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderScreen(IGuiLexiconEntry gui, int mx, int my) {
		relativeMouseX = mx;
		relativeMouseY = my;

		renderRecipe(gui, mx, my);

		int width = gui.getWidth() - 30;
		int height = gui.getHeight();
		int x = gui.getLeft() + 16;
		int y = gui.getTop() + height - 40;
		PageText.renderText(x, y, width, height, getUnlocalizedName());

		if(tooltipStack != null) {
			List<String> tooltipData = tooltipStack.getTooltip(Minecraft.getMinecraft().thePlayer, false);
			List<String> parsedTooltip = new ArrayList<>();
			boolean first = true;

			for(String s : tooltipData) {
				String s_ = s;
				if(!first)
					s_ = TextFormatting.GRAY + s;
				parsedTooltip.add(s_);
				first = false;
			}

			xyz.brassgoggledcoders.boilerplate.lib.client.manual.RenderHelper.renderTooltip(mx, my, parsedTooltip);

			int tooltipY = 8 + tooltipData.size() * 11;

			if(tooltipEntry) {
				xyz.brassgoggledcoders.boilerplate.lib.client.manual.RenderHelper.renderTooltipOrange(mx, my + tooltipY, Collections.singletonList(
						TextFormatting.GRAY + I18n.format("botaniamisc.clickToRecipe")));
				tooltipY += 18;
			}

			if(tooltipContainerStack != null)
				xyz.brassgoggledcoders.boilerplate.lib.client.manual.RenderHelper.renderTooltipGreen(mx, my + tooltipY, Arrays.asList(
						TextFormatting.AQUA + I18n.format("botaniamisc.craftingContainer"), tooltipContainerStack.getDisplayName()));
		}

		tooltipStack = tooltipContainerStack = null;
		tooltipEntry = false;
		GlStateManager.disableBlend();
		mouseDownLastTick = Mouse.isButtonDown(0);
	}

	@SideOnly(Side.CLIENT)
	public void renderRecipe(IGuiLexiconEntry gui, int mx, int my) {
		// NO-OP
	}

	@SideOnly(Side.CLIENT)
	public void renderItemAtAngle(IGuiLexiconEntry gui, float angle, ItemStack stack) {
		if(stack == null || stack.getItem() == null)
			return;

		ItemStack workStack = stack.copy();

		if(workStack.getItemDamage() == Short.MAX_VALUE || workStack.getItemDamage() == -1)
			workStack.setItemDamage(0);

		angle -= 90;
		int radius = 32;
		double xPos = gui.getLeft() + Math.cos(angle * Math.PI / 180D) * radius + gui.getWidth() / 2 - 8;
		double yPos = gui.getTop() + Math.sin(angle * Math.PI / 180D) * radius + 53;

		renderItem(gui, xPos, yPos, workStack, false);
	}

	//TODO Figure out if the Item stack is actually the book
	@SideOnly(Side.CLIENT)
	public void renderItemAtGridPos(IGuiLexiconEntry gui, int x, int y, ItemStack book, boolean accountForContainer) {
		if(book == null || book.getItem() == null)
			return;
		book = book.copy();

		if(book.getItemDamage() == Short.MAX_VALUE)
			book.setItemDamage(0);

		int xPos = gui.getLeft() + x * 29 + 7 + (y == 0  && x == 3 ? 10 : 0);
		int yPos = gui.getTop() + y * 29 + 24 - (y == 0 ? 7 : 0);
		ItemStack stack1 = book.copy();
		if(stack1.getItemDamage() == -1)
			stack1.setItemDamage(0);

		renderItem(gui, xPos, yPos, stack1, accountForContainer);
	}

	//TODO: Again figure out is the stack was actually the book or if I broke everything
	@SideOnly(Side.CLIENT)
	public void renderItem(IGuiLexiconEntry gui, double xPos, double yPos, ItemStack book, boolean accountForContainer) {
		RenderItem render = Minecraft.getMinecraft().getRenderItem();
		boolean mouseDown = Mouse.isButtonDown(0);

		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.enableRescaleNormal();
		GlStateManager.enableDepth();
		GlStateManager.pushMatrix();
		GlStateManager.translate(xPos, yPos, 0);
		render.renderItemAndEffectIntoGUI(book, 0, 0);
		render.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRendererObj, book, 0, 0, "");
		GlStateManager.popMatrix();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.popMatrix();

		int xpi = (int) xPos;
		int ypi = (int) yPos;
		if(relativeMouseX >= xpi && relativeMouseY >= ypi && relativeMouseX <= xpi + 16 && relativeMouseY <= ypi + 16) {
			tooltipStack = book;

			EntryData data = LexiconRecipeMappings.getDataForStack(tooltipStack);

			if(data != null && (data.entry != gui.getEntry() || data.page != gui.getPageOn()) && book != null && book.getItem() instanceof ILexicon) {
				tooltipEntry = true;

				if(!mouseDownLastTick && mouseDown && GuiScreen.isShiftKeyDown()) {
					GuiLexiconEntry newGui = new GuiLexiconEntry(data.entry, (GuiScreen) gui);
					newGui.page = data.page;
					Minecraft.getMinecraft().displayGuiScreen(newGui);
				}
			} else tooltipEntry = false;

			if(accountForContainer) {
				ItemStack containerStack = book.getItem().getContainerItem(book);
				if(containerStack != null && containerStack.getItem() != null)
					tooltipContainerStack = containerStack;
			}
		}

		GlStateManager.disableLighting();
	}

}
