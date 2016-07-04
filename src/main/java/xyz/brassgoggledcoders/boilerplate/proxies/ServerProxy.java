package xyz.brassgoggledcoders.boilerplate.proxies;

import xyz.brassgoggledcoders.boilerplate.module.IModule;
import xyz.brassgoggledcoders.boilerplate.module.IModuleProxy;

public class ServerProxy extends CommonProxy {
	@Override
	public IModuleProxy getModuleProxy(IModule module) {
		return this.getModuleProxy(module.getServerProxyPath());
	}
}
