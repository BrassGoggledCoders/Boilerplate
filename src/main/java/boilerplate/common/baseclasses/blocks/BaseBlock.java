
package boilerplate.common.baseclasses.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.utils.Utils;

/**
 * @author Surseance
 *
 */
public class BaseBlock extends Block
{

	public BaseBlock(Material mat)
	{
		super(mat);
		this.setCreativeTab(Utils.getCurrentExtendingMod().getCreativeTab());
		this.setHardness(1F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.blockIcon = ir.registerIcon(Utils.getCurrentExtendingMod().getPrefix() + this.getUnlocalizedName().substring(5));
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
