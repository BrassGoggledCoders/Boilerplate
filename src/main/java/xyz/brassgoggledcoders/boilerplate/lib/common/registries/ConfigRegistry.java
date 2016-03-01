package xyz.brassgoggledcoders.boilerplate.lib.common.registries;

import net.minecraftforge.common.config.Property;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.common.config.ConfigEntry;
import xyz.brassgoggledcoders.boilerplate.lib.common.config.IConfigListener;
import xyz.brassgoggledcoders.boilerplate.mod.Boilerplate;

import java.util.ArrayList;
import java.util.List;

public class ConfigRegistry extends BaseRegistry<ConfigEntry>
{
	private static ConfigRegistry instance;

	private List<IConfigListener> listeners = new ArrayList<IConfigListener>();

	public static ConfigRegistry getInstance()
	{
		if(instance == null)
		{
			instance = new ConfigRegistry();
		}
		return instance;
	}

	public void alertTheListeners(String name, ConfigEntry configEntry)
	{
		for(IConfigListener configListener: listeners)
		{
			configListener.onConfigChange(name, configEntry);
		}
	}

	public static void addEntry(String name, ConfigEntry entry)
	{
		getInstance().entries.put(name, entry);
		entry.toProperty(BoilerplateLib.getConfig());
		BoilerplateLib.getConfig().save();
	}

	public static void updateEntry(String name, String value)
	{
		ConfigEntry configEntry = getInstance().entries.get(name);
		configEntry.setValue(value);
		getInstance().alertTheListeners(name, configEntry);
	}

	public static ConfigEntry getEntry(String name)
	{
		return getInstance().entries.get(name);
	}

	public static boolean getBooleanValue(String name, boolean defaultValue)
	{
		boolean returnValue = defaultValue;
		ConfigEntry configEntry = getEntry(name);
		if(configEntry != null)
		{
			returnValue = configEntry.toProperty(BoilerplateLib.getConfig()).getBoolean(defaultValue);
		}
		return returnValue;
	}

	public static void addListener(IConfigListener listener)
	{
		getInstance().listeners.add(listener);
	}
}
