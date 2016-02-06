
package boilerplate.common;

import net.minecraft.creativetab.CreativeTabs;

/**
 * @author SkySom
 */
public interface IBoilerplateMod
{
	IModInfo getModInfo();

	Object getInstance();

	CreativeTabs getCreativeTab();
}
