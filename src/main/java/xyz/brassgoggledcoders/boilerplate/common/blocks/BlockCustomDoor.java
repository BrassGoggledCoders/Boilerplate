
package xyz.brassgoggledcoders.boilerplate.common.blocks;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;

import xyz.brassgoggledcoders.boilerplate.common.utils.Utils;

/**
 * @author warlordjones
 *
 */
public class BlockCustomDoor extends BlockDoor
{
	public BlockCustomDoor(String type)
	{
		super(Material.wood);
		// TODO this.setBlockTextureName(Utils.getCurrentMod().getPrefix() +
		// "block" + type + "Door");
		this.setCreativeTab(Utils.getCurrentMod().getCreativeTab());
	}
}
