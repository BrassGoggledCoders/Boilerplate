/**
 * This class was orginally created by <Vazkii>.
 */
package xyz.brassgoggledcoders.boilerplate.client.manual;

public final class FontHelper {

	public static boolean isFormatColor(char par0) {
		return par0 >= 48 && par0 <= 57 || par0 >= 97 && par0 <= 102 || par0 >= 65 && par0 <= 70;
	}

	public static boolean isFormatSpecial(char par0) {
		return par0 >= 107 && par0 <= 111 || par0 >= 75 && par0 <= 79 || par0 == 114 || par0 == 82;
	}

	public static String getFormatFromString(String par0Str) {
		String s1 = "";
		int i = -1;
		int j = par0Str.length();

		while ((i = par0Str.indexOf(167, i + 1)) != -1) {
			if (i < j - 1) {
				char c0 = par0Str.charAt(i + 1);

				if (isFormatColor(c0))
					s1 = "\u00a7" + c0;
				else if (isFormatSpecial(c0))
					s1 = s1 + "\u00a7" + c0;
			}
		}

		return s1;
	}

}
