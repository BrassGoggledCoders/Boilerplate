package boilerplate.common.modcompat;

import boilerplate.common.utils.LoggerBoilerplate;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;

/**
 * Created by Skylar on 9/7/2015.
 */
public class CompatibilityHandler
{
	private ArrayList<ModCompat> modCompatEnabled = new ArrayList<ModCompat>();

	public ArrayList<ModCompat> getModCompat()
	{
		return modCompatEnabled;
	}

	public void addModCompat(ModCompat modCompat)
	{
		modCompatEnabled.add(modCompat);
	}

	public void preInit(FMLPreInitializationEvent event)
	{
		for(ModCompat modCompat : getModCompat())
		{
			if(!modCompat.areRequirementsMet() && modCompat.getIsActive())
			{
				modCompat.setIsActive(false);
				LoggerBoilerplate.error("Requirements are not met for " + modCompat.getName() + ". Deactivating");
			}
			if(modCompat.getIsActive())
			{
				LoggerBoilerplate.info("Loading " + modCompat.getName() + " module");
			}
		}

		for(ModCompat modCompat : getModCompat())
		{
			if(modCompat.getIsActive())
				modCompat.preInit(event);
		}
	}

	public void init(FMLInitializationEvent event)
	{
		for(ModCompat modCompat : getModCompat())
		{
			if(modCompat.getIsActive())
				modCompat.init(event);
		}
	}

	public void postInit(FMLPostInitializationEvent event)
	{
		for(ModCompat modCompat : getModCompat())
		{
			if(modCompat.getIsActive())
				modCompat.postInit(event);
		}
	}

	public Configuration configureModCompat(Configuration configuration)
	{
		for(ModCompat modCompat: getModCompat()) {
			modCompat.setIsActive(configuration.get("ModCompat", modCompat.getName() + " Enabled", true).getBoolean(true));
		}
		return configuration;
	}
}
