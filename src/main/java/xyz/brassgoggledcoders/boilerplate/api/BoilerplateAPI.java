package xyz.brassgoggledcoders.boilerplate.api;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class BoilerplateAPI {
	@CapabilityInject(IPipeWrench.class)
	public static final Capability<IPipeWrench> PIPE_WRENCH_CAPABILITY = null;
}
