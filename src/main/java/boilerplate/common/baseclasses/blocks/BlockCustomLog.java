
package boilerplate.common.baseclasses.blocks;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.utils.Utils;

/**
 * @author warlordjones
 *
 */
public class BlockCustomLog extends BlockLog
{
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;

	String type;

	public BlockCustomLog(String type)
	{
		super();
		this.type = type;
		this.setCreativeTab(Utils.getCurrentExtendingMod().getCreativeTab());
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getSideIcon(int p_150163_1_)
	{
		return this.blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getTopIcon(int p_150161_1_)
	{
		return this.iconTop;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister ir)
	{
		this.blockIcon = ir.registerIcon(Utils.getCurrentExtendingMod().getPrefix() + "block" + this.type + "LogSide");
		this.iconTop = ir.registerIcon(Utils.getCurrentExtendingMod().getPrefix() + "block" + this.type + "LogTop");
	}
}
