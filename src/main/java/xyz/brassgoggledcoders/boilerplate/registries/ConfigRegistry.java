package xyz.brassgoggledcoders.boilerplate.registries;

import net.minecraftforge.common.config.Configuration;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.config.ConfigEntry;
import xyz.brassgoggledcoders.boilerplate.config.IConfigListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigRegistry extends BaseRegistry<ConfigEntry>
{
	private List<IConfigListener> listeners = new ArrayList<IConfigListener>();
	private Configuration configuration;

	public ConfigRegistry(IBoilerplateMod mod, IRegistryHolder registryHolder, File suggestConfigFile)
	{
		super(mod, registryHolder);
		configuration = new Configuration(suggestConfigFile);
	}

	public void alertTheListeners(String name, ConfigEntry configEntry)
	{
		for(IConfigListener configListener: listeners)
		{
			configListener.onConfigChange(name, configEntry);
		}
	}

	public void addCategoryComment(String name, String comment)
	{
		configuration.addCustomCategoryComment(name, comment);
	}

	public void addEntry(ConfigEntry entry)
	{
		addEntry(entry.getPropertyName(), entry);
	}

	public void addEntry(String name, ConfigEntry entry)
	{
		this.entries.put(name, entry);
		entry.toProperty(configuration);
		configuration.save();
	}

	public void updateEntry(String name, String value)
	{
		ConfigEntry configEntry = this.entries.get(name);
		if(configEntry != null)
		{
			configEntry.setValue(value);
			this.alertTheListeners(name, configEntry);
		} else
		{
			mod.getLogger().error("Config Entry for " + name + " not found");
		}
	}

	public ConfigEntry getEntry(String name)
	{
		return this.entries.get(name);
	}

	public boolean getBoolean(String name, boolean defaultValue)
	{
		boolean returnValue = defaultValue;
		ConfigEntry configEntry = getEntry(name);
		if(configEntry != null)
		{
			returnValue = configEntry.getBoolean(defaultValue);
		}
		return returnValue;
	}

	public int getInt(String name, int defaultValue)
	{
		int returnValue = defaultValue;
		ConfigEntry configEntry = getEntry(name);
		if(configEntry != null)
		{
			returnValue = configEntry.getInt(defaultValue);
		}
		return returnValue;
	}

	public double getDouble(String name, double defaultValue)
	{
		double returnValue = defaultValue;
		ConfigEntry configEntry = getEntry(name);
		if(configEntry != null)
		{
			returnValue = configEntry.getDouble(defaultValue);
		}
		return returnValue;
	}

	public String getString(String name, String defaultValue)
	{
		String returnValue = defaultValue;
		ConfigEntry configEntry = getEntry(name);
		if(configEntry != null)
		{
			returnValue = configEntry.getString();
		}
		return returnValue;
	}

	public void addListener(IConfigListener listener)
	{
		this.listeners.add(listener);
	}
}
