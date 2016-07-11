package xyz.brassgoggledcoders.boilerplate.modules.spanner;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.Boilerplate;
import xyz.brassgoggledcoders.boilerplate.module.Module;
import xyz.brassgoggledcoders.boilerplate.module.ModuleBase;

@Module(mod = Boilerplate.ID)
public class SpannerModule extends ModuleBase {
	public static Item spanner;

	@Override
	public String getName() {
		return "Spanner";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		spanner = new ItemSpanner();
		this.getItemRegistry().registerItem(spanner);
	}
}
