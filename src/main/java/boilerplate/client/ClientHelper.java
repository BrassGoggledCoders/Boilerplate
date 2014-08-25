/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.input.Keyboard;

/**
 * @author Surseance
 * 
 */
public class ClientHelper
{
	public static String shiftForInfo = EnumChatFormatting.GRAY + "Hold " + EnumChatFormatting.GREEN + "SHIFT" + EnumChatFormatting.GRAY + " for more info.";

	public static boolean isShiftKeyDown()
	{
		return (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) || (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT));
	}

	// Not used anywhere
	public static Minecraft mc()
	{
		return Minecraft.getMinecraft();
	}

	public static FontRenderer fontRenderer()
	{
		return mc().fontRenderer;
	}

	public static EntityClientPlayerMP clientPlayer()
	{
		return mc().thePlayer;
	}
}
