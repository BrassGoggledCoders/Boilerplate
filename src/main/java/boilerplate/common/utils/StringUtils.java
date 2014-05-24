package boilerplate.common.utils;

import net.minecraft.util.StatCollector;

public final class StringUtils {
	
	public static String camelCase(String input) {

		return input.substring(0, 1).toLowerCase() + input.substring(1);
	}

	public static String titleCase(String input) {

		return input.substring(0, 1).toUpperCase() + input.substring(1);
	}

	public static String localize(String key) {

		return StatCollector.translateToLocal(key);
	}


}