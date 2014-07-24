package boilerplate.client.renderers;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;

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
    doRender(item, x, y, z, 0, 0);
  }

  public boolean shouldBob()
  {
    return this.bob;
  }

  public boolean shouldSpreadItems()
  {
    return this.spread;
  }
}