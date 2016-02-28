
package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;

/**
 * @author warlordjones
 *
 */
public class BlockCustomDoor extends BlockDoor
{
	public BlockCustomDoor(String type)
	{
		super(Material.wood);
		// TODO this.setBlockTextureName(BoilerplateLib.getInstance.getMod().getPrefix() +
		// "block" + type + "Door");
		this.setCreativeTab(BoilerplateLib.getMod().getCreativeTab());
	}
}
