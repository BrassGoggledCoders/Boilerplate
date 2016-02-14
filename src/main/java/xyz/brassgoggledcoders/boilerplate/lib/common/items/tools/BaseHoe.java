package xyz.brassgoggledcoders.boilerplate.lib.common.items.tools;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * @author Surseance
 *
 */
public class BaseHoe extends BaseTool
{
	public BaseHoe(ToolMaterial mat)
	{
		super(1F, mat);
		this.setMaxDamage(mat.getMaxUses());
		this.setHarvestLevel("hoe", mat.getHarvestLevel());
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing facing, float f1, float f2, float f3)
	{
		if (player.canPlayerEdit(blockPos, facing, stack))
		{
			this.executeHoeAction(stack, player, world, blockPos, facing, f1, f2, f3);
			return true;
		}
		return false;
	}

	private void executeHoeAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing facing, float f1, float f2,
			float f3)
	{
		IBlockState i1 = world.getBlockState(blockPos);

		if (((facing.ordinal() != 0) && world.isAirBlock(blockPos) && (i1 == Blocks.grass)) || (i1 == Blocks.dirt))
		{
			Block block = Blocks.farmland;
			world.playSoundEffect(blockPos.getX() + 0.5F, blockPos.getY() + 0.5F, blockPos.getZ() + 0.5F, block.stepSound.getBreakSound(),
					(block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getFrequency());

			if (!world.isRemote)
			{
				world.setBlockState(blockPos, block.getDefaultState());

				stack.damageItem(1, player);
			}
		}
	}
}