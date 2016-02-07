
package boilerplate.common.baseclasses.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.IBoilerplateMod;

/**
 * @author Surseance
 *
 */
public class BaseBlock extends Block
{
	IBoilerplateMod mod;

	public BaseBlock(Material mat, IBoilerplateMod mod)
	{
		super(mat);
		this.setCreativeTab(mod.getCreativeTab());
		this.setHardness(1F);
		this.mod = mod;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.blockIcon = ir.registerIcon(mod.getModInfo().getPrefix() + this.getUnlocalizedName().substring(5));
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return this.blockIcon;
	}
}
