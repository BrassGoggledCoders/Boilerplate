package xyz.brassgoggledcoders.boilerplate.modules.spanner;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.oredict.ShapedOreRecipe;
import xyz.brassgoggledcoders.boilerplate.api.BoilerplateAPI;
import xyz.brassgoggledcoders.boilerplate.api.ISpanner;
import xyz.brassgoggledcoders.boilerplate.api.SpannerImpl;
import xyz.brassgoggledcoders.boilerplate.items.IHasRecipe;
import xyz.brassgoggledcoders.boilerplate.items.ItemBase;

public class ItemSpanner extends ItemBase implements IHasRecipe {

	public ItemSpanner() {
		super("spanner");
		this.setCreativeTab(CreativeTabs.TOOLS);
		this.setMaxStackSize(1);
	}

	@Override
	public IRecipe[] getRecipes() {
		return new IRecipe[] {new ShapedOreRecipe(this, "I I", "ISI", " S ", 'I', "ingotIron", 'S', "stickWood")};
	}

	@Override
	public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack,
			NBTTagCompound nbt) {
		return new CapabilityProvider(stack);
	}

	public static class CapabilityProvider implements ICapabilityProvider {
		private final ItemStack stack;
		private ISpanner spanner;

		public CapabilityProvider(ItemStack stack) {
			this.stack = stack;
			this.spanner = new SpannerImpl();
		}

		public CapabilityProvider(ItemStack stack, ISpanner cap) {
			this.stack = stack;
			this.spanner = cap;
		}

		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
			if(capability == BoilerplateAPI.SPANNER_CAPABILITY)
				return true;
			return false;
		}

		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
			if(capability == BoilerplateAPI.SPANNER_CAPABILITY)
				return BoilerplateAPI.SPANNER_CAPABILITY.cast(spanner);
			return null;
		}
	}

}
