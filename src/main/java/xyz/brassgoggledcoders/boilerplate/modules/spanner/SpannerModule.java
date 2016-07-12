package xyz.brassgoggledcoders.boilerplate.modules.spanner;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.Boilerplate;
import xyz.brassgoggledcoders.boilerplate.api.ISpanner;
import xyz.brassgoggledcoders.boilerplate.api.SpannerImpl;
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
		CapabilityManager.INSTANCE.register(ISpanner.class, new Capability.IStorage<ISpanner>() {
			@Override
			public NBTTagCompound writeNBT(Capability<ISpanner> capability, ISpanner instance, EnumFacing side) {
				return instance.serializeNBT();
			}

			@Override
			public void readNBT(Capability<ISpanner> capability, ISpanner instance, EnumFacing side, NBTBase nbt) {
				if(nbt instanceof NBTTagCompound)
					instance.deserializeNBT((NBTTagCompound) nbt);
			}
		}, SpannerImpl.class);
		spanner = new ItemSpanner();
		this.getItemRegistry().registerItem(spanner);
	}
}
