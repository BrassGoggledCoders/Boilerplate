/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.common;

import net.minecraft.util.StatCollector;

/**
 * @author Surseance
 *
 */
public class CommonProxy
{
	public void registerRenderHandlers()
	{
	}

	public String translate(String text)
	{
		return StatCollector.translateToLocal("boilerplate." + text);
	}
}
