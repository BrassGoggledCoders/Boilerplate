package boilerplate.common.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Skylar on 9/7/2015.
 */
public class LoggerBoilerplate
{
	private static final Logger logger = LogManager.getLogger("boilerplate");

	public static void log(Level level, String message)
	{
		logger.log(level, message);
	}

	public static void warning(String message)
	{
		logger.log(Level.WARN, message);
	}

	public static void info(String message)
	{
		logger.log(Level.INFO, message);
	}

	public static void fatal(String message)
	{
		logger.log(Level.FATAL, message);
	}

	public static void error(String message)
	{
		logger.log(Level.ERROR, message);
	}
}
