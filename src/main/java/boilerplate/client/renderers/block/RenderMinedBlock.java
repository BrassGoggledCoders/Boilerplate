/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.client.renderers.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.entity.EntityMinedBlock;
import org.lwjgl.opengl.GL11;

/**
 * @author Surseance
 *
 */
@SideOnly(Side.CLIENT)
public class RenderMinedBlock extends Render
{
	private RenderBlocks blockRenderer = new RenderBlocks();

	public RenderMinedBlock()
	{
		this.shadowSize = 0.0F;
	}

	public void doRender(EntityMinedBlock entBlock, double posX, double posY, double posZ, float f, float renderTick)
	{
		World world = entBlock.getWorldObj();
		Block block = entBlock.getBlock();

		MathHelper.floor_double(entBlock.posX);
		MathHelper.floor_double(entBlock.posY);
		MathHelper.floor_double(entBlock.posZ);

		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glPushMatrix();
		GL11.glTranslatef((float) posX, (float) posY, (float) posZ);
		this.bindEntityTexture(entBlock);

		float scale = entBlock.scale;
		float rot = world.getWorldTime() * 17.6F;

		GL11.glScalef(scale, scale, scale);

		if (entBlock.doesRotate)
		{
			GL11.glRotatef(rot, 0 - world.rand.nextFloat(), 0 - world.rand.nextFloat(), 0 - world.rand.nextFloat());
		}

		this.blockRenderer.blockAccess = entBlock.worldObj;
		this.blockRenderer.useInventoryTint = true;

		this.blockRenderer.setRenderBoundsFromBlock(block);
		this.blockRenderer.renderBlockAsItem(block, entBlock.metadata, 1.0F);

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	protected ResourceLocation getEntityTexture(EntityMinedBlock entBlock)
	{
		return TextureMap.locationBlocksTexture;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityMinedBlock) entity);
	}

	@Override
	public void doRender(Entity entity, double posX, double posY, double posZ, float par8, float renderTick)
	{
		this.doRender((EntityMinedBlock) entity, posX, posY, posZ, par8, renderTick);
	}
}
