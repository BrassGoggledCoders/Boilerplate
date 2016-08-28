package xyz.brassgoggledcoders.boilerplate.client.guis;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import xyz.brassgoggledcoders.boilerplate.client.guis.components.IComponent;

import java.util.ArrayList;

public class GuiScreenBase extends GuiContainer {
	private ArrayList<IComponent> components;

	public GuiScreenBase(Container inventorySlots){
		super(inventorySlots);
	}

	public void addComponent(IComponent component) {
		if(components == null) {
			components = new ArrayList<>();
		}
		components.add(component);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		components.forEach(component -> component.render(mouseX, mouseY));
	}
}
