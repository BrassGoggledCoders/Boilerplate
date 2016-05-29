package xyz.brassgoggledcoders.boilerplate;

import net.minecraft.creativetab.CreativeTabs;

/**
 * @author SkySom
 */
public interface IBoilerplateMod
{
	Object getInstance();

	CreativeTabs getCreativeTab();

	String getID();

	String getName();

	String getVersion();

	String getPrefix();
}
