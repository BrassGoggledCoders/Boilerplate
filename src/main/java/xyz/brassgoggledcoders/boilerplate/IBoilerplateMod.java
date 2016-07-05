package xyz.brassgoggledcoders.boilerplate;

import net.minecraft.creativetab.CreativeTabs;
import xyz.brassgoggledcoders.boilerplate.client.guis.GuiHandler;
import xyz.brassgoggledcoders.boilerplate.module.ModuleHandler;
import xyz.brassgoggledcoders.boilerplate.network.PacketHandler;
import xyz.brassgoggledcoders.boilerplate.proxies.CommonProxy;
import xyz.brassgoggledcoders.boilerplate.registries.IRegistryHolder;
import xyz.brassgoggledcoders.boilerplate.utils.ModLogger;

/**
 * @author SkySom
 */
public interface IBoilerplateMod {
	Object getInstance();

	CreativeTabs getCreativeTab();

	String getID();

	String getName();

	String getVersion();

	String getPrefix();

	CommonProxy getBoilerplateProxy();

	ModLogger getLogger();

	GuiHandler getGuiHandler();

	PacketHandler getPacketHandler();

	IRegistryHolder getRegistryHolder();

	ModuleHandler getModuleHandler();
}
