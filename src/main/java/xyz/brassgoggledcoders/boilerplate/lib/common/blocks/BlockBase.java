
package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.common.IBoilerplateMod;

/**
 * @author Surseance
 *
 */
public class BlockBase extends Block
{
	IBoilerplateMod mod;

	public BlockBase(Material mat)
	{
		super(mat);
		this.mod = BoilerplateLib.getMod();
		this.setCreativeTab(mod.getCreativeTab());
		this.setHardness(1F);
	}

	public BlockBase(Material mat, String name)
	{
		this(mat);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
	}
}
