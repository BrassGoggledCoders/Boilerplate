package xyz.brassgoggledcoders.boilerplate.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.IModAware;
import xyz.brassgoggledcoders.boilerplate.client.models.IHasModel;

public class BlockModFluid extends BlockFluidClassic implements IModAware, IHasModel, IHasItemBlock {
	IBoilerplateMod mod;

	public BlockModFluid(Material mat, String name, Fluid fluid) {
		super(fluid, mat);
		this.setUnlocalizedName(name);
	}

	@Override
	public IBoilerplateMod getMod() {
		return this.mod;
	}

	@Override
	public void setMod(IBoilerplateMod mod) {
		this.mod = mod;
	}

	@Override
	public String[] getResourceLocations() {
		String name = this.getUnlocalizedName();
		if(name.startsWith("tile."))
			name = name.substring(5);
		return new String[] {name};
	}

	@Override
	public ItemBlock getItemBlockClass(Block block) {
		return new ItemBlock(block) {
			@Override
			@SideOnly(Side.CLIENT)
			public CreativeTabs getCreativeTab() {
				return null;
			}
		};
	}
}