
package boilerplate.common.baseclasses.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

import boilerplate.common.IBoilerplateMod;

/**
 * @author warlordjones
 *
 */
public class BlockCustomSlab extends BlockSlab
{
	String type;
	Block block;
	IBoilerplateMod mod;

	public BlockCustomSlab(String type, Block block, Material mat, IBoilerplateMod mod)
	{
		super(false, mat);
		this.type = type;
		this.block = block;
		this.setCreativeTab(mod.getCreativeTab());
		this.useNeighborBrightness = true;
		this.mod = mod;
	}

	@Override
	public String func_150002_b(int p_150002_1_)
	{
		return mod.getModInfo().getPrefix() + this.type;
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return this.block.getIcon(side, meta);
	}
}
