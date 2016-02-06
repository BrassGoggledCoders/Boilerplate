
package boilerplate.common.baseclasses.blocks;

import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;

import boilerplate.common.IBoilerplateMod;

/**
 * @author warlordjones
 *
 */
public class BlockCustomFence extends BlockFence
{
	public BlockCustomFence(String type, Material mat, IBoilerplateMod mod)
	{
		super(mod.getModInfo().getPrefix() + type, mat);
		this.setCreativeTab(mod.getCreativeTab());
	}
}
