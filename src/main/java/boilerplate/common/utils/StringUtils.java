package boilerplate.common.utils;

import net.minecraft.util.StatCollector;

// TODO: Auto-generated Javadoc
/**
 * The Class StringUtils.
 */
public final class StringUtils
{

	/**
	 * Camel case.
	 *
	 * @param input the input
	 * @return the string
	 */
	public static String camelCase(final String input)
	{

		return input.substring(0, 1).toLowerCase() + input.substring(1);
	}

	/**
	 * Title case.
	 *
	 * @param input the input
	 * @return the string
	 */
	public static String titleCase(final String input)
	{

		return input.substring(0, 1).toUpperCase() + input.substring(1);
	}

	/**
	 * Localize.
	 *
	 * @param key the key
	 * @return the string
	 */
	public static String localize(final String key)
	{

		return StatCollector.translateToLocal(key);
	}

}