
package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;

/**
 * @author warlordjones
 *
 */
public class BlockCustomFence extends BlockFence
{
	public BlockCustomFence(Material mat)
	{
		super(/* BoilerplateLib.getInstance.getMod().getPrefix() + type, */ mat);
		this.setCreativeTab(BoilerplateLib.getMod().getCreativeTab());
	}
}
