package boilerplate.common;

import net.minecraft.entity.Entity;
import cpw.mods.fml.common.registry.EntityRegistry;


public class EntityHelper
{
	public static void addNormalEntity(Class<? extends Entity> entity, String name, int id, Object instance)
	{
		EntityRegistry.registerModEntity(entity, name, id/*This ID is internal!*/, instance, 64, 20, true);
	}
}
