package xyz.brassgoggledcoders.boilerplate.lib.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ModLogger;

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

	ModLogger getLogger();

	Configuration getConfig();

	String getClientProxyPath();

	String getCommonProxyPath();
}
