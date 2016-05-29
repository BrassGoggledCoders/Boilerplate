package xyz.brassgoggledcoders.boilerplate.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigEntry
{
	private String category;
	private String propertyName;
	private Type type;
	private String value;
	private String comment;
	private boolean guiChangeable;

	public ConfigEntry(String category, String propertyName, Type type, String value)
	{
		this(category, propertyName, type, value, "");
	}

	public ConfigEntry(String category, String propertyName, Type type, String value, String comment)
	{
		this(category, propertyName, type, value, comment, true);
	}

	public ConfigEntry(String category, String propertyName, Type type, String value, String comment, boolean guiChangeable)
	{
		this.setCategory(category);
		this.setPropertyName(propertyName);
		this.setType(type);
		this.setValue(value);
		this.setComment(comment);
		this.setGuiChangeable(guiChangeable);
	}

	public Property toProperty(Configuration configuration)
	{
		Property property = null;
		switch(getType()) {
			case INTEGER:
				property = configuration.get(getCategory(), getPropertyName(), Integer.parseInt(getValue()), getComment());
				break;
			case BOOLEAN:
				property = configuration.get(getCategory(), getPropertyName(), Boolean.parseBoolean(getValue()), getComment());
				break;
			case DOUBLE:
				property = configuration.get(getCategory(), getPropertyName(), Double.parseDouble(getValue()), getComment());
				break;
			default:
				property = configuration.get(getCategory(), getPropertyName(), getValue(), getComment());
				break;
		}
		return property;
	}

	public int getInt(int defaultValue)
	{
		try
		{
			return Integer.parseInt(getValue());
		}
		catch (NumberFormatException e)
		{
			return defaultValue;
		}
	}

	public boolean getBoolean(boolean defaultValue)
	{
		if (getType() == Type.BOOLEAN)
		{
			return Boolean.parseBoolean(getValue());
		}
		else
		{
			return defaultValue;
		}
	}

	public double getDouble(double defaultValue)
	{
		try
		{
			return Double.parseDouble(getValue());
		}
		catch (NumberFormatException e)
		{
			return defaultValue;
		}
	}

	public String getString()
	{
		return getValue();
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public String getPropertyName()
	{
		return propertyName;
	}

	public void setPropertyName(String propertyName)
	{
		this.propertyName = propertyName;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public boolean isGuiChangeable()
	{
		return guiChangeable;
	}

	public void setGuiChangeable(boolean guiChangeable)
	{
		this.guiChangeable = guiChangeable;
	}

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}
}
