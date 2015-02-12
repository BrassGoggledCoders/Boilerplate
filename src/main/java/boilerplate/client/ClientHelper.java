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
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;

/**
 * @author Surseance
 * 
 */
public class ClientHelper
{
	public static String shiftForInfo = EnumChatFormatting.GRAY + "Hold " + EnumChatFormatting.GREEN + "SHIFT" + EnumChatFormatting.GRAY
			+ " for more info.";

	public static boolean isShiftKeyDown()
	{
		return (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) || (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT));
	}

	public static Minecraft mc()
	{
		return FMLClientHandler.instance().getClient();
	}

	public static FontRenderer fontRenderer()
	{
		return mc().fontRenderer;
	}

	public static EntityClientPlayerMP player()
	{
		return mc().thePlayer;
	}

	public static WorldClient world()
	{
		return mc().theWorld;
	}
	
	public static EntityLivingBase viewEntity()
	{
		return mc().renderViewEntity;
	}
	
	public static EffectRenderer effectRenderer()
	{
		return mc().effectRenderer;
	}
	
	public static TextureManager textureManager()
	{
		return mc().getTextureManager();
	}
	
	public static GuiScreen screen()
	{
		return mc().currentScreen;
	}
	
	public static GameSettings settings()
	{
		return mc().gameSettings;
	}
	
	public static EntityRenderer entityRenderer()
	{
		return mc().entityRenderer;
	}
	
	public static ScaledResolution resolution()
	{
		return new ScaledResolution(mc(), mc().displayWidth, mc().displayHeight);
	}
}
