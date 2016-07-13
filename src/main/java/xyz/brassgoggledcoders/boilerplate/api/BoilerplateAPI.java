package xyz.brassgoggledcoders.boilerplate.api;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class BoilerplateAPI {
	@CapabilityInject(ITool.class)
	public static final Capability<ITool> TOOL_CAPABILITY = null;
}
