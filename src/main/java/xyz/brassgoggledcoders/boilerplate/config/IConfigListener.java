package xyz.brassgoggledcoders.boilerplate.config;

public interface IConfigListener
{
	void onConfigChange(String name, ConfigEntry entry);
}
