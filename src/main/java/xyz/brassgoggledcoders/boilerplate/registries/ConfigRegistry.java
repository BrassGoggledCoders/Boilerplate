package xyz.brassgoggledcoders.boilerplate.registries;

import net.minecraftforge.common.config.Configuration;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.config.ConfigEntry;
import xyz.brassgoggledcoders.boilerplate.config.IConfigListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigRegistry extends BaseRegistry<ConfigEntry>
{
	private List<IConfigListener> listeners = new ArrayList<IConfigListener>();
	private File bgcFolder;
	private Map<String, Configuration> configurationFiles;

	public ConfigRegistry(IBoilerplateMod mod, IRegistryHolder registryHolder, File suggestConfigFile)
	{
		super(mod, registryHolder);
		configurationFiles = new HashMap<>();
		if(suggestConfigFile.isDirectory())
		{
			String bgcFolderPath = suggestConfigFile.getPath() + "/brassgoggledcoders";
			this.bgcFolder = new File(bgcFolderPath);

			boolean folderExists = bgcFolder.exists();
			if(!folderExists)
			{
				folderExists = bgcFolder.mkdir();
			}

			if(folderExists)
			{
				File modConfigFile = new File(bgcFolderPath + File.separator + mod.getID() + ".cfg");
				boolean fileExists = modConfigFile.exists();
				if(!fileExists)
				{
					try
					{
						fileExists = modConfigFile.createNewFile();
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
				}
				if(fileExists)
				{
					configurationFiles.put(mod.getID(), new Configuration(modConfigFile));
				}
			}
		} else
		{
			configurationFiles.put(mod.getID(), new Configuration(suggestConfigFile));
		}
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
		configurationFiles.get(this.mod.getID()).addCustomCategoryComment(name, comment);
	}

	public void addEntry(ConfigEntry entry)
	{
		addEntry(entry.getPropertyName(), entry);
	}

	public void addEntry(String name, ConfigEntry entry)
	{
		this.entries.put(name, entry);
		entry.toProperty(configurationFiles.get(this.mod.getID()));
		configurationFiles.get(this.mod.getID()).save();
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
