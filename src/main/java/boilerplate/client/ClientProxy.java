
package boilerplate.client;

import cpw.mods.fml.client.registry.RenderingRegistry;

import boilerplate.client.renderers.block.RenderMinedBlock;
import boilerplate.common.CommonProxy;
import boilerplate.common.entity.EntityMinedBlock;

/**
 * @author Surseance
 *
 */
public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderHandlers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityMinedBlock.class, new RenderMinedBlock());
	}
}
