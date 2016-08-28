package xyz.brassgoggledcoders.boilerplate.modules.pipewrench;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.Boilerplate;
import xyz.brassgoggledcoders.boilerplate.api.ITool;
import xyz.brassgoggledcoders.boilerplate.api.ToolImpl;
import xyz.brassgoggledcoders.boilerplate.module.Module;
import xyz.brassgoggledcoders.boilerplate.module.ModuleBase;

@Module(mod = Boilerplate.ID)
public class PipeWrenchModule extends ModuleBase {
	public static Item pipeWrench;

	@Override
	public String getName() {
		return "Pipe Wrench";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		CapabilityManager.INSTANCE.register(ITool.class, new Capability.IStorage<ITool>() {
			@Override
			public NBTTagCompound writeNBT(Capability<ITool> capability, ITool instance, EnumFacing side) {
				return instance.serializeNBT();
			}

			@Override
			public void readNBT(Capability<ITool> capability, ITool instance, EnumFacing side, NBTBase nbt) {
				if(nbt instanceof NBTTagCompound)
					instance.deserializeNBT((NBTTagCompound) nbt);
			}
		}, ToolImpl.class);
		pipeWrench = new ItemPipeWrench();
		this.getItemRegistry().registerItem(pipeWrench);
	}
}
