package xyz.brassgoggledcoders.boilerplate;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.brassgoggledcoders.boilerplate.proxies.CommonProxy;
import xyz.brassgoggledcoders.boilerplate.worldgen.OreGenerationHandler;

@Mod(modid = Boilerplate.ID, name = Boilerplate.NAME, version = Boilerplate.VERSION,
		dependencies = Boilerplate.DEPENDENCIES)
public class Boilerplate extends BoilerplateModBase {
	public final static String ID = "boilerplate";
	public final static String NAME = "Boilerplate";
	public final static String VERSION = "@VERSION@";
	public final static String DEPENDENCIES = "";

	@SidedProxy(clientSide = "xyz.brassgoggledcoders.boilerplate.proxies.ClientProxy",
			serverSide = "xyz.brassgoggledcoders.boilerplate.proxies.ServerProxy")
	public static CommonProxy proxy;

	@Instance(Boilerplate.ID)
	public static Boilerplate instance;

	public static CreativeTabs tabOres;

	public Boilerplate() {
		super(Boilerplate.ID, Boilerplate.NAME, Boilerplate.VERSION, CreativeTabs.MISC);
	}

	@Override
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		proxy.registerBlockModels();
	}

	@Override
	@EventHandler
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@Override
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
		if(OreGenerationHandler.haveAnyOresBeenRequested()) {
			GameRegistry.registerWorldGenerator(new OreGenerationHandler(), 2);
		}
		this.getLogger().info("GNU Terry Prachett");
	}

	@Override
	public Object getInstance() {
		return instance;
	}
}
