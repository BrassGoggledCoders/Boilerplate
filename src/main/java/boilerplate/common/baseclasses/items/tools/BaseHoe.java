
package boilerplate.common.baseclasses.items.tools;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Surseance
 *
 */
public class BaseHoe extends BaseTool
{
	public BaseHoe(ToolMaterial mat, String prefix)
	{
		super(1F, mat, prefix);
		this.setMaxDamage(mat.getMaxUses());
		this.setHarvestLevel("hoe", mat.getHarvestLevel());
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int l, float f1, float f2, float f3)
	{
		if (player.canPlayerEdit(x, y, z, l, stack))
		{
			this.executeHoeAction(stack, player, world, x, y, z, l, f1, f2, f3);
			return true;
		}
		return false;
	}

	private void executeHoeAction(ItemStack stack, EntityPlayer player, World world, int i, int j, int k, int l, float f1, float f2, float f3)
	{
		Block i1 = world.getBlock(i, j, k);

		if (((l != 0) && world.isAirBlock(i, j, k) && (i1 == Blocks.grass)) || (i1 == Blocks.dirt))
		{
			Block block = Blocks.farmland;
			world.playSoundEffect(i + 0.5F, j + 0.5F, k + 0.5F, block.stepSound.getBreakSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F,
					block.stepSound.getPitch() * 0.8F);

			if (!world.isRemote)
			{
				world.setBlock(i, j, k, block);

				stack.damageItem(1, player);
			}
		}
	}
}