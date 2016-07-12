package xyz.brassgoggledcoders.boilerplate.api;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class BoilerplateAPI {
	@CapabilityInject(ISpanner.class)
	public static final Capability<ISpanner> SPANNER_CAPABILITY = null;
}
