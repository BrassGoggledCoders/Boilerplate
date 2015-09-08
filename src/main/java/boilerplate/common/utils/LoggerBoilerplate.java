package boilerplate.common.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Skylar on 9/7/2015.
 */
public class LoggerBoilerplate
{
	private static LoggerBoilerplate instance = null;

	protected LoggerBoilerplate() {}

	public static LoggerBoilerplate getInstance()
	{
		if (instance == null) {
			instance = new LoggerBoilerplate();
		}
		return instance;
	}

	public static void log(Level level, String message)
	{
		getInstance().getLogger().log(level, message);
	}

	public static void warning(String message)
	{
		log(Level.WARN, message);
	}

	public static void info(String message)
	{
		log(Level.INFO, message);
	}

	public static void fatal(String message)
	{
		log(Level.FATAL, message);
	}

	public static void error(String message)
	{
		log(Level.ERROR, message);
	}

	protected Logger getLogger()
	{
		return LogManager.getLogger(getInstance().getLoggerName());
	}

	protected String getLoggerName()
	{
		return "boilerplate";
	}
}
