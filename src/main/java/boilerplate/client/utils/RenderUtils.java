package boilerplate.client.utils;

import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class RenderUtils
{
	public static void doWireRenderRotation(ForgeDirection dir)
	{
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if (dir == ForgeDirection.DOWN)
			GL11.glRotatef(180, 1, 0, 0);
		else if (dir == ForgeDirection.SOUTH)
			GL11.glRotatef(90, 1, 0, 0);
		else if (dir == ForgeDirection.NORTH)
			GL11.glRotatef(270, 1, 0, 0);
		else if (dir == ForgeDirection.WEST)
			GL11.glRotatef(90, 0, 0, 1);
		else if (dir == ForgeDirection.EAST)
			GL11.glRotatef(270, 0, 0, 1);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	}

	public static void doWireRenderNegRotation(ForgeDirection dir)
	{
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if (dir == ForgeDirection.DOWN)
			GL11.glRotatef(-180, 1, 0, 0);
		else if (dir == ForgeDirection.SOUTH)
			GL11.glRotatef(-90, 1, 0, 0);
		else if (dir == ForgeDirection.NORTH)
			GL11.glRotatef(-270, 1, 0, 0);
		else if (dir == ForgeDirection.WEST)
			GL11.glRotatef(-90, 0, 0, 1);
		else if (dir == ForgeDirection.EAST)
			GL11.glRotatef(-270, 0, 0, 1);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	}
}
