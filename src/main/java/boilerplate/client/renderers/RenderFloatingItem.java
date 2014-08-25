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

/**
 * @author warlordjones
 *
 */
public class RenderFloatingItem extends RenderItem
{
	private boolean bob;
	private boolean spread;

	public RenderFloatingItem(boolean bob, boolean spread)
	{
		this.bob = bob;
		this.spread = spread;
		this.setRenderManager(RenderManager.instance);
	}

	public void render(EntityItem item, float x, float y, float z)
	{
		this.doRender(item, x, y, z, 0, 0);
	}

	@Override
	public boolean shouldBob()
	{
		return this.bob;
	}

	@Override
	public boolean shouldSpreadItems()
	{
		return this.spread;
	}
}