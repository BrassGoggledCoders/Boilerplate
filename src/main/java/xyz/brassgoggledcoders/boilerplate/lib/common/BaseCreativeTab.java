package xyz.brassgoggledcoders.boilerplate.lib.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @author warlordjones
 *
 */
public abstract class BaseCreativeTab extends CreativeTabs
{
	public BaseCreativeTab(String name)
	{
		super(CreativeTabs.getNextID(), name);
	}

	@Override
	public abstract Item getTabIconItem();
}
