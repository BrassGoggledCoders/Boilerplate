package xyz.brassgoggledcoders.boilerplate.lib.common.items.tools;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * @author Surseance
 *
 */
public class BaseSword extends BaseTool
{
	public BaseSword(ToolMaterial mat)
	{
		super(4.0F, mat);
		this.setHarvestLevel("sword", mat.getHarvestLevel());
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, BlockPos blockPos, EntityLivingBase living)
	{
		if (block.getBlockHardness(world, blockPos) != 0.0D)
		{
			stack.damageItem(2, living);
		}

		return true;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.BLOCK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 72000;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		return stack;
	}
}