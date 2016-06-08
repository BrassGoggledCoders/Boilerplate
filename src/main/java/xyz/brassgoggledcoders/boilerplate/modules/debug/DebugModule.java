package xyz.brassgoggledcoders.boilerplate.modules.debug;

import net.minecraftforge.classloading.FMLForgePlugin;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.Boilerplate;
import xyz.brassgoggledcoders.boilerplate.items.ItemDebuggerStick;
import xyz.brassgoggledcoders.boilerplate.module.Module;
import xyz.brassgoggledcoders.boilerplate.module.ModuleBase;

@Module(mod=Boilerplate.ID)
public class DebugModule extends ModuleBase
{
	public static ItemDebuggerStick ITEM_DEBUG_STICK;

	@Override
	public String getName()
	{
		return "Debug";
	}

	@Override
	public boolean getActiveDefault()
	{
		return !FMLForgePlugin.RUNTIME_DEOBF;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		ITEM_DEBUG_STICK = new ItemDebuggerStick();
		this.getItemRegistry().registerItem(ITEM_DEBUG_STICK);
		this.getLogger().info("The Debugging Stick of Doom is active!");
	}
}
