package xyz.brassgoggledcoders.boilerplate.utils;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.IFluidHandler;

import javax.annotation.Nullable;

public class Selectors
{
	public final static Predicate<Entity> IFLUID_HANDLER_ENTITIES = new Predicate<Entity>()
	{
		@Override
		public boolean apply(@Nullable Entity input)
		{
			return input instanceof IFluidHandler && input.isEntityAlive();
		}
	};

	public static final Predicate<EnumFacing> NOT_UP = new Predicate<EnumFacing>()
	{
		@Override
		public boolean apply(EnumFacing p_apply_1_)
		{
			return p_apply_1_ != EnumFacing.UP;
		}
	};
}
