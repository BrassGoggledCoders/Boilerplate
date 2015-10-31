package boilerplate.common.utils.entity;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author SkySom
 */
public class ModWithEntityList
{
	private String modName;
	private ArrayList<KeyValue<String, Integer>> entityList;
	private int highestID = -1;

	public ModWithEntityList(String name, ArrayList<KeyValue<String, Integer>> entityList, int nextAvailableID)
	{
		this.setModName(name);
		this.setEntityList(entityList);
		this.setHighestID(nextAvailableID);
	}

	public ModWithEntityList(String modid, Configuration configuration)
	{
		ConfigCategory entityIDSection = configuration.getCategory("Entity IDs");
		Map<String, Property> entityIDMap = entityIDSection.getValues();
		ArrayList<KeyValue<String, Integer>> entityIDs = new ArrayList<KeyValue<String, Integer>>();
		int highestID = -1;
		for(Property property: entityIDMap.values())
		{
			if(property.isIntValue())
			{
				if(property.getInt() > highestID)
				{
					highestID = property.getInt();
				}
				entityIDs.add(new KeyValue<String, Integer>(property.getName(), property.getInt()));
			}
		}

		this.setModName(modid);
		this.setEntityList(entityList);
		this.setHighestID(highestID);
	}

	public String getModName()
	{
		return modName;
	}

	public void setModName(String modName)
	{
		this.modName = modName;
	}

	public ArrayList<KeyValue<String, Integer>> getEntityList()
	{
		return entityList;
	}

	public void setEntityList(ArrayList<KeyValue<String, Integer>> entityList)
	{
		this.entityList = entityList;
	}

	public int getNextAvailableID()
	{
		this.setHighestID(++highestID);
		return this.getHighestID();
	}

	public void setHighestID(int highestID)
	{
		this.highestID = highestID;
	}

	public int getHighestID()
	{
		return this.highestID;
	}

	public ConfigCategory toConfigCategory()
	{
		ConfigCategory configCategory = new ConfigCategory("Entity ID's");
		return this.toConfigCategory(configCategory);
	}

	public ConfigCategory toConfigCategory(ConfigCategory configCategory)
	{
		for(KeyValue<String, Integer> entityID: this.getEntityList())
		{
			Property property = new Property(entityID.getKey(), entityID.getValue().toString(), Property.Type.INTEGER);
			configCategory.getValues().put(entityID.getKey(), property);
		}

		return configCategory;
	}
}
