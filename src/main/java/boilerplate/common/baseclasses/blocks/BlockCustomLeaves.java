
package boilerplate.common.baseclasses.blocks;

import net.minecraft.block.BlockLeaves;
import net.minecraft.util.IIcon;

import boilerplate.common.utils.Utils;

/**
 * @author warlordjones
 *
 */
public class BlockCustomLeaves extends BlockLeaves
{
	String type;

	public BlockCustomLeaves(String type)
	{
		super();
		this.type = type;
		this.setCreativeTab(Utils.getCurrentMod().getCreativeTab());
	}

	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return this.blockIcon;
	}

	@Override
	public String[] func_150125_e()
	{
		return new String[] { this.type };
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
}
