package xyz.brassgoggledcoders.boilerplate.lib.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.lwjgl.input.Keyboard;

/**
 * @author Surseance
 *
 */
public class ClientHelper
{
	public static String shiftForInfo = TextFormatting.GRAY + "Hold " + TextFormatting.GREEN + "SHIFT" + TextFormatting.GRAY
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
		return mc().fontRendererObj;
	}

	public static EntityPlayerSP player()
	{
		return mc().thePlayer;
	}

	public static WorldClient world()
	{
		return mc().theWorld;
	}

	public static Entity viewEntity()
	{
		return mc().getRenderViewEntity();
	}

	public static EffectRenderer effectRenderer()
	{
		return mc().effectRenderer;
	}

	public static TextureManager textureManager()
	{
		return mc().getTextureManager();
	}

	public static RayTraceResult rayTrace()
	{
		return mc().objectMouseOver;
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

	public static RenderManager renderManager()
	{
		return mc().getRenderManager();
	}

	public static ScaledResolution resolution()
	{
		return new ScaledResolution(mc());
	}

	public static ItemModelMesher getItemModelMesher()
	{
		return mc().getRenderItem().getItemModelMesher();
	}
}
