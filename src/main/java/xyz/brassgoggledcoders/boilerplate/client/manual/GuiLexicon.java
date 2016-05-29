/**
 * This class was orginally created by <Vazkii>.
 */
package xyz.brassgoggledcoders.boilerplate.client.manual;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import xyz.brassgoggledcoders.boilerplate.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.client.manual.button.*;
import xyz.brassgoggledcoders.boilerplate.manual.BotaniaAPI;
import xyz.brassgoggledcoders.boilerplate.manual.LexiconCategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GuiLexicon extends GuiScreen {

	public static GuiLexicon currentOpenLexicon = new GuiLexicon();
	public static ItemStack stackUsed;

	public static HashMap<String, String> notes = new HashMap<String, String>();

	private static final String TAG_TYPE = "type";

	public static final ResourceLocation texture = new ResourceLocation(BoilerplateLib.getMod().getPrefix() + "textures/gui/manual/manual.png");

	public float lastTime = 0F;
	public float partialTicks = 0F;
	public float timeDelta = 0F;

	public String categoryHighlight = "";

	List<LexiconCategory> allCategories;

	String title;
	int guiWidth = 146;
	int guiHeight = 180;
	int left, top;

	@Override
	public final void initGui() {
		super.initGui();
		onInitGui();
	}

	public void onInitGui() {
		allCategories = new ArrayList<>(BotaniaAPI.getAllCategories());
		Collections.sort(allCategories);

		lastTime = ClientTickHandler.ticksInGame;

		title = stackUsed.getDisplayName();
		currentOpenLexicon = this;

		left = width / 2 - guiWidth / 2;
		top = height / 2 - guiHeight / 2;

		buttonList.clear();
		if(isIndex()) {
			int x = 18;
			for(int i = 0; i < 12; i++) {
				int y = 16 + i * 12;
				buttonList.add(new GuiButtonInvisible((GuiLexiconIndex) this, i, left + x, top + y, 110, 10, ""));
			}
			populateIndex();
		} else if(isCategoryIndex()) {
			int categories = allCategories.size();
			for(int i = 0; i < categories + 1; i++) {
				LexiconCategory category = null;
				category = i >= categories ? null : allCategories.get(i);
				int perline = 5;
				int x = i % perline;
				int y = i / perline;

				int size = 22;
				GuiButtonCategory button = new GuiButtonCategory(i, left + 18 + x * size, top + 50 + y * size, this, category);
				buttonList.add(button);
			}
		}
		populateBookmarks();
		if(isMainPage()) {
			buttonList.add(new GuiButtonOptions(-1, left - 6, top + guiHeight - 54));
			buttonList.add(new GuiButtonAchievement(-2, left - 6, top + guiHeight - 40));
		}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		float time = ClientTickHandler.ticksInGame + par3;
		timeDelta = time - lastTime;
		lastTime = time;
		partialTicks = par3;

		GlStateManager.color(1F, 1F, 1F, 1F);
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(left, top, 0, 0, guiWidth, guiHeight);

		if(isMainPage())
			drawHeader();

		super.drawScreen(par1, par2, par3);
	}

	void drawHeader() {
		GlStateManager.pushMatrix();
		GlStateManager.color(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(left - 8, top + 9, 0, 224, 140, 31);

		int color = 0xffd200;
		boolean unicode = fontRendererObj.getUnicodeFlag();
		fontRendererObj.drawString(title, left + 18, top + 13, color);
		fontRendererObj.setUnicodeFlag(true);
		//fontRendererObj.drawString(String.format(StatCollector.format("botaniamisc.edition"), ItemLexicon.getEdition()), left + 24, top + 22, color);

		String s = TextFormatting.BOLD + categoryHighlight;
		fontRendererObj.drawString(s, left + guiWidth / 2 - fontRendererObj.getStringWidth(s) / 2, top + 36, 0);

		fontRendererObj.setUnicodeFlag(unicode);
		GlStateManager.popMatrix();

		categoryHighlight = "";
	}

	boolean isMainPage() {
		return true;
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if(par1GuiButton instanceof GuiButtonCategory) {
			LexiconCategory category = ((GuiButtonCategory) par1GuiButton).getCategory();

			mc.displayGuiScreen(new GuiLexiconIndex(category));
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	public int bookmarkWidth(String b) {
		if(fontRendererObj == null)
			fontRendererObj = Minecraft.getMinecraft().fontRendererObj;

		boolean unicode = fontRendererObj.getUnicodeFlag();
		fontRendererObj.setUnicodeFlag(true);
		int width = fontRendererObj.getStringWidth(b) + 15;
		fontRendererObj.setUnicodeFlag(unicode);
		return width;
	}

	String getTitle() {
		return title;
	}

	String getSubtitle() {
		return null;
	}

	int getTitleHeight() {
		return getSubtitle() == null ? 12 : 22;
	}

	boolean isIndex() {
		return false;
	}

	boolean isChallenge() {
		return false;
	}

	boolean isCategoryIndex() {
		return true;
	}

	void populateIndex() {
		List<LexiconCategory> categoryList = BotaniaAPI.getAllCategories();
		int shift = 2;
		for(int i = shift; i < 12; i++) {
			int i_ = i - shift;
			GuiButtonInvisible button = (GuiButtonInvisible) buttonList.get(i);
			LexiconCategory category = i_ >= categoryList.size() ? null : categoryList.get(i_);
			if(category != null)
				button.displayString = I18n.format(category.getUnlocalizedName());
			else {
				button.displayString = I18n.format("botaniamisc.lexiconIndex");
				break;
			}
		}
	}

	void populateBookmarks() {
		List<GuiButton> remove = new ArrayList<GuiButton>();
		buttonList.removeAll(remove);

		if(isMainPage())
			buttonList.add(new GuiButtonHistory(2000, left + 138, top + guiHeight - 24, I18n.format("botaniamisc.history"), this));
	}

	boolean closeScreenOnInvKey() {
		return true;
	}

	public static GuiLexicon create(NBTTagCompound cmp) {
		String type = cmp.getString(TAG_TYPE);
		try {
			GuiLexicon lex = (GuiLexicon) Class.forName(type).newInstance();
			if(lex != null)
				lex.load(cmp);
			if(isValidLexiconGui(lex))
				return lex;
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void serialize(NBTTagCompound cmp) {
		cmp.setString(TAG_TYPE, getClass().getName());
	}

	public String getNotesKey() {
		return "index";
	}

	public void load(NBTTagCompound cmp) {
		// NO-OP
	}

	public GuiLexicon copy() {
		return new GuiLexicon();
	}

	public static boolean isValidLexiconGui(GuiLexicon gui)	{
		if(gui == null)
			return false;
		if(gui.isCategoryIndex() || gui.isChallenge())
			return true;
		if(gui.isIndex()) {
			GuiLexiconIndex indexGui = (GuiLexiconIndex) gui;
			if(indexGui.category == null)
				return true;
			return BotaniaAPI.getAllCategories().contains(indexGui.category);
		}

		GuiLexiconEntry entryGui = (GuiLexiconEntry) gui;
		if(!BotaniaAPI.getAllEntries().contains(entryGui.entry))
			return false;

		return entryGui.page < entryGui.entry.pages.size();
	}
}

