/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.common.baseclasses;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @author warlordjones
 *
 */
public class CreativeTabBase extends CreativeTabs
{
	public CreativeTabBase(String name)
	{
		super(CreativeTabs.getNextID(), name);
	}

	@Override
	public Item getTabIconItem()
	{
		return null;
	}
}
