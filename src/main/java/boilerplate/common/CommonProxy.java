
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
