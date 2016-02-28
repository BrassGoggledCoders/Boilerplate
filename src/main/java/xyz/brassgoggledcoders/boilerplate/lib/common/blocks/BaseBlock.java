
package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.common.IBoilerplateMod;

/**
 * @author Surseance
 *
 */
public class BaseBlock extends Block
{
	IBoilerplateMod mod;

	public BaseBlock(Material mat)
	{
		super(mat);
		this.mod = BoilerplateLib.getInstance().getMod();
		this.setCreativeTab(mod.getCreativeTab());
		this.setHardness(1F);
	}

	public BaseBlock(Material mat, String name)
	{
		this(mat);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
	}

	/*
	 * TODO
	 * 
	 * @Override
	 * 
	 * @SideOnly(Side.CLIENT) public void registerBlockIcons(IIconRegister ir) {
	 * this.blockIcon = ir.registerIcon(mod.getPrefix() +
	 * this.getUnlocalizedName().substring(5)); }
	 * 
	 * @Override
	 * 
	 * @SideOnly(Side.CLIENT) public IIcon getIcon(int p_149691_1_, int
	 * p_149691_2_) { return this.blockIcon; }
	 */
}
