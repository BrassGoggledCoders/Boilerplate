package xyz.brassgoggledcoders.boilerplate.lib.common.items.tools;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
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
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (player.canPlayerEdit(blockPos, facing, stack))
		{
			this.executeHoeAction(stack, player, world, blockPos, facing, hitX, hitY, hitZ);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}

	//TODO: Fix Hoe stuff to match Vanilla
	private void executeHoeAction(ItemStack stack, EntityPlayer player, World world, BlockPos blockPos, EnumFacing facing, float f1, float f2,
			float f3)
	{
		IBlockState i1 = world.getBlockState(blockPos);

		if (((facing.ordinal() != 0) && world.isAirBlock(blockPos) && (i1 == Blocks.grass)) || (i1 == Blocks.dirt))
		{
			Block block = Blocks.farmland;
			world.playSound(player, blockPos, SoundEvents.item_hoe_till, SoundCategory.BLOCKS, 1.0F, 1.0F);

			if (!world.isRemote)
			{
				world.setBlockState(blockPos, block.getDefaultState());

				stack.damageItem(1, player);
			}
		}
	}
}