package xyz.brassgoggledcoders.boilerplate;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.classloading.FMLForgePlugin;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.config.ConfigEntry;
import xyz.brassgoggledcoders.boilerplate.config.Type;
import xyz.brassgoggledcoders.boilerplate.items.ItemDebuggerStick;
import xyz.brassgoggledcoders.boilerplate.proxies.CommonProxy;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = ModInfo.DEPENDENCIES)
public class Boilerplate extends BoilerplateModBase
{
	/**
	 * warlordjones - c2e83bd4-e8df-40d6-a639-58ba8b05401e
	 *
	 * decebaldecebal - 5eed1615-0ec9-4f4b-a4c9-58454ad5b04f
	 *
	 * Sky_Som - 27672103-b8c7-400d-8817-49de433336dd
	 *
	 * Snurly - ???
	 */

	public static String[] donors = {};
	public static String[] devs = { "c2e83bd4-e8df-40d6-a639-58ba8b05401e", "5eed1615-0ec9-4f4b-a4c9-58454ad5b04f",
			"27672103-b8c7-400d-8817-49de433336dd" };

	public static ItemDebuggerStick ITEM_DEBUG_STICK;

	@SidedProxy(clientSide = "xyz.brassgoggledcoders.boilerplate.proxies.ClientProxy", serverSide = "xyz.brassgoggledcoders.boilerplate.proxies.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Instance(ModInfo.ID)
	public static Boilerplate instance;

	public Boilerplate()
	{
		super(ModInfo.ID, ModInfo.NAME, ModInfo.VERSION, CreativeTabs.MISC);
	}

	@Override
	public void modPreInit(FMLPreInitializationEvent event)
	{
		this.getRegistryHolder().getConfigRegistry().addEntry("debugging",
				new ConfigEntry("debugging", "activateDebuggingStickOfDoom", Type.BOOLEAN, "false", "True to enable"));

		if (this.getRegistryHolder().getConfigRegistry().getBoolean("debugging", false) || !FMLForgePlugin.RUNTIME_DEOBF)
		{
			ITEM_DEBUG_STICK = new ItemDebuggerStick();
			this.getRegistryHolder().getItemRegistry().registerItem(ITEM_DEBUG_STICK);
			this.getLogger().info("The Debugging Stick of Doom is active!");
		}
	}

	@Override
	public void modInit(FMLInitializationEvent event)
	{

	}

	@Override
	protected void modPostInit(FMLPostInitializationEvent event)
	{
		this.getLogger().info("GNU Terry Prachett");
	}

	@Override
	public Object getInstance()
	{
		return instance;
	}

	@Override
	public CommonProxy getProxy()
	{
		return proxy;
	}
}
