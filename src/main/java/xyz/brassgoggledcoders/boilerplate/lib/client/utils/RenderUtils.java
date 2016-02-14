package xyz.brassgoggledcoders.boilerplate.lib.client.utils;

import net.minecraft.util.EnumFacing;

import org.lwjgl.opengl.GL11;

public class RenderUtils
{
	public static void doWireRenderRotation(EnumFacing dir)
	{
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if (dir == EnumFacing.DOWN)
			GL11.glRotatef(180, 1, 0, 0);
		else if (dir == EnumFacing.SOUTH)
			GL11.glRotatef(90, 1, 0, 0);
		else if (dir == EnumFacing.NORTH)
			GL11.glRotatef(270, 1, 0, 0);
		else if (dir == EnumFacing.WEST)
			GL11.glRotatef(90, 0, 0, 1);
		else if (dir == EnumFacing.EAST)
			GL11.glRotatef(270, 0, 0, 1);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	}

	public static void doWireRenderNegRotation(EnumFacing dir)
	{
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if (dir == EnumFacing.DOWN)
			GL11.glRotatef(-180, 1, 0, 0);
		else if (dir == EnumFacing.SOUTH)
			GL11.glRotatef(-90, 1, 0, 0);
		else if (dir == EnumFacing.NORTH)
			GL11.glRotatef(-270, 1, 0, 0);
		else if (dir == EnumFacing.WEST)
			GL11.glRotatef(-90, 0, 0, 1);
		else if (dir == EnumFacing.EAST)
			GL11.glRotatef(-270, 0, 0, 1);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	}
}
