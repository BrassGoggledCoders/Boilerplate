
package boilerplate.common.baseclasses.blocks;

import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;

import boilerplate.common.utils.Utils;

/**
 * @author warlordjones
 *
 */
public class BlockCustomFence extends BlockFence
{
	public BlockCustomFence(String type, Material mat)
	{
		super(Utils.getCurrentExtendingMod().getPrefix() + type, mat);
		this.setCreativeTab(Utils.getCurrentExtendingMod().getCreativeTab());
	}
}
