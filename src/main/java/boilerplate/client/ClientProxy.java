/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
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
