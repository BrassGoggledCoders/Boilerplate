package boilerplate.client;

import boilerplate.client.renderers.block.RenderMinedBlock;
import boilerplate.common.CommonProxy;
import boilerplate.common.entity.EntityMinedBlock;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLLog;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderHandlers()
	{
		FMLLog.bigWarning("Works", "Works");
		RenderingRegistry.registerEntityRenderingHandler(EntityMinedBlock.class, new RenderMinedBlock());
	}
}
