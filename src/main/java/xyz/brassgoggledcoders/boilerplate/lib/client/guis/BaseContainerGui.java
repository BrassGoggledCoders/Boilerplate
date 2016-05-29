package xyz.brassgoggledcoders.boilerplate.lib.client.guis;

import com.google.common.collect.Lists;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import xyz.brassgoggledcoders.boilerplate.lib.client.utils.GuiColors;
import xyz.brassgoggledcoders.boilerplate.lib.common.tileentities.BaseTileWithInventory;

import java.util.ArrayList;

public abstract class BaseContainerGui extends GuiContainer
{
	protected BaseTileWithInventory tile;

	public BaseContainerGui(Container p_i1072_1_)
	{
		super(p_i1072_1_);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);

		if (this.tile == null)
		{
			return;
		}

		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;

		for (Slot slot : this.inventorySlots.inventorySlots)
		{
			if (slot instanceof IToolTipSlot && !slot.getHasStack() && this.mouseInside(slot, mouseX - x, mouseY - y))
			{
				if (slot.slotNumber < this.tile.getSizeInventory())
				{
					this.drawHoveringText(Lists.newArrayList(I18n.format(((IToolTipSlot) slot).getSlotTooltipUnloc())), mouseX - x,
							mouseY - y);
				}
			}
		}
	}

	private boolean mouseInside(Slot slot, int x, int y)
	{
		return (x >= slot.xDisplayPosition) && (x <= (slot.xDisplayPosition + 16)) && (y >= slot.yDisplayPosition)
				&& (y <= (slot.yDisplayPosition + 16));
	}

	protected void drawFluidInfo(FluidTank tank, int x, int y)
	{
		ArrayList<String> lines = new ArrayList<String>();

		FluidStack fluidstack = tank.getFluid();

		if (fluidstack == null)
		{
			lines.add(GuiColors.RED + "Empty");
		}
		else if (fluidstack.getFluid() == FluidRegistry.WATER)
		{
			lines.add(GuiColors.LIGHTBLUE + "Water");
		}
		else if (fluidstack.getFluid() == FluidRegistry.getFluid("steam"))
		{
			lines.add(GuiColors.GRAY + "Steam");
		}
		else
		{
			lines.add(GuiColors.GREEN + fluidstack.getLocalizedName());
		}

		lines.add(tank.getFluidAmount() + "/" + tank.getCapacity());

		this.drawHoveringText(lines, x - this.guiLeft, y - this.guiTop, this.fontRendererObj);
	}

	/*
	 * TODO: Fix GUI Fluid rendering protected void drawFluid(Fluid fluid, int
	 * level, int x, int y, int width, int height) { if (fluid == null) {
	 * return; }
	 * 
	 * IIcon icon = fluid.getIcon(); if (icon == null) { icon =
	 * fluid.getBlock().getIcon(0, 0); } if (icon != null) {
	 * this.mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture); int
	 * fullX = width / 16; int fullY = height / 16; int lastX = width - (fullX *
	 * 16); int lastY = height - (fullY * 16); int fullLvl = (height - level) /
	 * 16; int lastLvl = height - level - (fullLvl * 16); for (int i = 0; i <
	 * fullX; i++) { for (int j = 0; j < fullY; j++) { if (j >= fullLvl) {
	 * this.drawCutIcon(icon, x + (i * 16), y + (j * 16), 16, 16, j == fullLvl ?
	 * lastLvl : 0); } } } for (int i = 0; i < fullX; i++) {
	 * this.drawCutIcon(icon, x + (i * 16), y + (fullY * 16), 16, lastY, fullLvl
	 * == fullY ? lastLvl : 0); } for (int i = 0; i < fullY; i++) { if (i >=
	 * fullLvl) { this.drawCutIcon(icon, x + (fullX * 16), y + (i * 16), lastX,
	 * 16, i == fullLvl ? lastLvl : 0); } } this.drawCutIcon(icon, x + (fullX *
	 * 16), y + (fullY * 16), lastX, lastY, fullLvl == fullY ? lastLvl : 0); } }
	 * 
	 * private void drawCutIcon(IIcon icon, int x, int y, int width, int height,
	 * int cut) { Tessellator tess = Tessellator.getInstance();
	 * tess.startDrawingQuads(); tess.addVertexWithUV(x, y + height,
	 * this.zLevel, icon.getMinU(), icon.getInterpolatedV(height));
	 * tess.addVertexWithUV(x + width, y + height, this.zLevel,
	 * icon.getInterpolatedU(width), icon.getInterpolatedV(height));
	 * tess.addVertexWithUV(x + width, y + cut, this.zLevel,
	 * icon.getInterpolatedU(width), icon.getInterpolatedV(cut));
	 * tess.addVertexWithUV(x, y + cut, this.zLevel, icon.getMinU(),
	 * icon.getInterpolatedV(cut)); tess.draw(); }
	 */
}
