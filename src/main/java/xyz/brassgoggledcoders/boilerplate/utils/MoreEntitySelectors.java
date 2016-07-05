package xyz.brassgoggledcoders.boilerplate.utils;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;

public class MoreEntitySelectors {
	public static final Predicate<Entity> CAN_COLLIDE = new Predicate<Entity>() {
		@Override
		public boolean apply(Entity apply) {
			return apply.canBeCollidedWith();
		}
	};
}
