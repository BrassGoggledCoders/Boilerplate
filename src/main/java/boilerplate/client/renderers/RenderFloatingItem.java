/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.client.renderers;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;
import boilerplate.client.ClientHelper;

/**
 * @author warlordjones
 * 
 */
public class RenderFloatingItem extends RenderItem
{
	public static void render(double dx, double dy, double dz, float rotX, float rotY, float rotZ, ItemStack stack)
	{
		EntityItem entItem = new EntityItem(ClientHelper.world(), 0D, 0D, 0D, stack);
		// Without the below line, the item will spazz out
		entItem.hoverStart = 0.0F;
		RenderItem.renderInFrame = true;
		GL11.glTranslatef((float) dx, (float) dy, (float) dz);
		GL11.glRotatef(rotX, 1, 0, 0);
		GL11.glRotatef(rotY, 0, 1, 0);
		GL11.glRotatef(rotZ, 0, 0, 1);
		GL11.glScalef(1.3F, 1.3F, 1.3F);
		RenderManager.instance.renderEntityWithPosYaw(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
		RenderItem.renderInFrame = false;
	}

	public static void render(double dx, double dy, double dz, float rotX, float rotY, float rotZ, float scaleX, float scaleY, float scaleZ,
			ItemStack stack)
	{
		EntityItem entItem = new EntityItem(ClientHelper.world(), 0D, 0D, 0D, stack);
		// Without the below line, the item will spazz out
		entItem.hoverStart = 0.0F;
		RenderItem.renderInFrame = true;
		GL11.glTranslatef((float) dx, (float) dy, (float) dz);
		GL11.glRotatef(rotX, 1, 0, 0);
		GL11.glRotatef(rotY, 0, 1, 0);
		GL11.glRotatef(rotZ, 0, 0, 1);
		GL11.glScalef(scaleX, scaleY, scaleZ);
		RenderManager.instance.renderEntityWithPosYaw(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
		RenderItem.renderInFrame = false;
	}
}