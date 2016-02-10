
package boilerplate.common.baseclasses.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

import boilerplate.common.utils.Utils;

/**
 * @author warlordjones
 *
 */
public class BlockCustomSlab extends BlockSlab
{
	String type;
	Block block;

	public BlockCustomSlab(String type, Block block, Material mat)
	{
		super(false, mat);
		this.type = type;
		this.block = block;
		this.setCreativeTab(Utils.getCurrentMod().getCreativeTab());
		this.useNeighborBrightness = true;
	}

	@Override
	public String func_150002_b(int p_150002_1_)
	{
		return Utils.getCurrentMod().getPrefix() + this.type;
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return this.block.getIcon(side, meta);
	}
}
