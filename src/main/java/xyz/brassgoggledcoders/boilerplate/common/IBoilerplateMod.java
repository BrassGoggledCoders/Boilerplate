package xyz.brassgoggledcoders.boilerplate.common;

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

	String getClientProxyPath();

	String getCommonProxyPath();
}
