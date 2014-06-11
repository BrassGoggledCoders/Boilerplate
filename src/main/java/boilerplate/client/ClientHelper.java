package boilerplate.client;

import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.input.Keyboard;

public class ClientHelper {
	public static String shiftForInfo = EnumChatFormatting.GRAY + "Hold " + EnumChatFormatting.GREEN + "SHIFT" + EnumChatFormatting.GRAY + " for more info.";

	public static boolean isShiftKeyDown() {
		return (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) || (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT));
	}
}
