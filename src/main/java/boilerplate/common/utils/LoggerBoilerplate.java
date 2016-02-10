
package boilerplate.common.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author skysom
 *
 */
public class LoggerBoilerplate
{
	private static Logger logger;

	public static void log(Level level, String message)
	{
		getLogger().log(level, message);
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

	protected static Logger getLogger()
	{
		if (logger == null) logger = LogManager.getLogger("boilerplate");
		return logger;
	}
}