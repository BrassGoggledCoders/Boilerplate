package xyz.brassgoggledcoders.boilerplate.utils;

import net.minecraftforge.classloading.FMLForgePlugin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author SkySom
 */
public class ModLogger implements ILogger
{
	private Logger modLog;
	private boolean isDev;

	public ModLogger(String modid)
	{
		this(LogManager.getLogger(modid));
	}

	public ModLogger(Logger modLog)
	{
		this.modLog = modLog;
		this.isDev = !FMLForgePlugin.RUNTIME_DEOBF;
	}

	@Override
	public void warning(String message)
	{
		this.modLog.warn(message);
	}

	@Override
	public void info(String message)
	{
		this.modLog.info(message);
	}

	@Override
	public void fatal(String message)
	{
		this.modLog.fatal(message);
	}

	@Override
	public void error(String message)
	{
		this.modLog.fatal(message);
	}

	@Override
	public void devWarning(String message)
	{
		if (this.isDev)
		{
			this.warning(message);
		}
	}

	@Override
	public void devInfo(String message)
	{
		if (this.isDev)
		{
			this.info(message);
		}
	}

	@Override
	public void devFatal(String message)
	{
		if (this.isDev)
		{
			this.info(message);
		}
	}

	@Override
	public void devError(String message)
	{
		if (this.isDev)
		{
			this.error(message);
		}
	}
}
