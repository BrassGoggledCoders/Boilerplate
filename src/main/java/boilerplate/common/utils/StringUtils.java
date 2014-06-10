package boilerplate.common.utils;

import java.util.Enumeration;
import java.util.Vector;

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
	public static String[] wrap(String input, int len)
	{
		// return empty array for null text
		  if (input == null)
		  return new String [] {};

		  // return text if len is zero or less
		  if (len <= 0)
		  return new String [] {input};

		  // return text if less than length
		  if (input.length() <= len)
		  return new String [] {input};

		  char [] chars = input.toCharArray();
		  Vector lines = new Vector();
		  StringBuffer line = new StringBuffer();
		  StringBuffer word = new StringBuffer();

		  for (int i = 0; i < chars.length; i++) {
		    word.append(chars[i]);

		    if (chars[i] == ' ') {
		      if ((line.length() + word.length()) > len) {
		        lines.add(line.toString());
		        line.delete(0, line.length());
		      }

		      line.append(word);
		      word.delete(0, word.length());
		    }
		  }

		  // handle any extra chars in current word
		  if (word.length() > 0) {
		    if ((line.length() + word.length()) > len) {
		      lines.add(line.toString());
		      line.delete(0, line.length());
		    }
		    line.append(word);
		  }

		  // handle extra line
		  if (line.length() > 0) {
		    lines.add(line.toString());
		  }

		  String [] ret = new String[lines.size()];
		  int c = 0; // counter
		  for (Enumeration e = lines.elements(); e.hasMoreElements(); c++) {
		    ret[c] = (String) e.nextElement();
		  }

		  return ret;
	}

}