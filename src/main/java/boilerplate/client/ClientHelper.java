package boilerplate.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.input.Keyboard;

/**
 * @author warlordjones
 *
 */
public class ClientHelper
{
	public static String shiftForInfo = EnumChatFormatting.GRAY + "Hold " + EnumChatFormatting.GREEN + "SHIFT" + EnumChatFormatting.GRAY + " for more info.";

	public static boolean isShiftKeyDown()
	{
		return (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) || (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT));
	}

	/**
	 * Mc.
	 * 
	 * @return the minecraft
	 */
	public static Minecraft mc()
	{
		return Minecraft.getMinecraft();
	}

	/**
	 * Font renderer.
	 * 
	 * @return the font renderer
	 */
	public static FontRenderer fontRenderer()
	{
		return mc().fontRenderer;
	}

	/**
	 * Client player.
	 * 
	 * @return the entity client player mp
	 */
	public static EntityClientPlayerMP clientPlayer()
	{
		return mc().thePlayer;
	}
}
