/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package xyz.brassgoggledcoders.boilerplate.common.items.tools;

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
	public BaseSword(ToolMaterial mat, String prefix)
	{
		super(4.0F, mat, prefix);
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