package xyz.brassgoggledcoders.boilerplate.lib.common.config;

public interface IConfigListener
{
	void onConfigChange(String name, ConfigEntry entry);
}
