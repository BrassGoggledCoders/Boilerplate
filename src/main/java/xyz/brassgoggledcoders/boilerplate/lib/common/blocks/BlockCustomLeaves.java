
package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;

import java.util.List;

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
		this.setCreativeTab(BoilerplateLib.getMod().getCreativeTab());
	}

	// @Override
	// public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	// {
	// return this.blockIcon;
	// }

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumType getWoodType(int meta)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
