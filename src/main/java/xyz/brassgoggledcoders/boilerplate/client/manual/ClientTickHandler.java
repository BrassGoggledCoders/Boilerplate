/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * File Created @ [Feb 3, 2014, 9:59:17 PM (GMT)]
 */
package xyz.brassgoggledcoders.boilerplate.client.manual;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class ClientTickHandler {

	public static int ticksInGame = 0;
	public static float partialTicks = 0;
	public static float delta = 0;
	public static float total = 0;

	@SubscribeEvent
	public void clientTickEnd(ClientTickEvent event) {
		if(event.phase == Phase.END) {

			GuiScreen gui = Minecraft.getMinecraft().currentScreen;
			if(gui == null || !gui.doesGuiPauseGame()) {
				ticksInGame++;
				partialTicks = 0;
			}
		}
	}

}
