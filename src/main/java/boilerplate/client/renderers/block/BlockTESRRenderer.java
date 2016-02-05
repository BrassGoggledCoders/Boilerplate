
package boilerplate.client.renderers.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

import org.lwjgl.opengl.GL11;

/**
 * @author warlordjones
 *
 */
public class BlockTESRRenderer implements ISimpleBlockRenderingHandler
{
	private TileEntity tile;
	private int renderID;

	public BlockTESRRenderer(TileEntity tile, int renderID)
	{
		this.tile = tile;
		this.renderID = renderID;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		TileEntityRendererDispatcher.instance.renderTileEntityAt(this.tile, 0.0D, 0.0D, 0.0D, 0.0F);
		GL11.glEnable(32826);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer)
	{
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int i)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return this.renderID;
	}
}
