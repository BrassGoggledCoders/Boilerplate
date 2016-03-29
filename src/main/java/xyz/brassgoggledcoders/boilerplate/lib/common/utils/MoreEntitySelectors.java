package xyz.brassgoggledcoders.boilerplate.lib.common.utils;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;

public class MoreEntitySelectors
{
	public static final Predicate<Entity> CAN_COLLIDE = new Predicate<Entity>()
	{
		public boolean apply(Entity apply)
		{
			return apply.canBeCollidedWith();
		}
	};
}
