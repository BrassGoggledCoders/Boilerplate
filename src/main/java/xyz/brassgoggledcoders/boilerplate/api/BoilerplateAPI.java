package xyz.brassgoggledcoders.boilerplate.api;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class BoilerplateAPI {
	@CapabilityInject(IWrench.class)
	public static final Capability<IWrench> WRENCH_CAPABILITY = null;
}
