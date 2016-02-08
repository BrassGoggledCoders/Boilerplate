
package boilerplate.common.baseclasses.blocks;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;

import boilerplate.common.utils.Utils;

/**
 * @author warlordjones
 *
 */
public class BlockCustomDoor extends BlockDoor
{
	public BlockCustomDoor(String type)
	{
		super(Material.wood);
		this.setBlockTextureName(Utils.getCurrentExtendingMod().getPrefix() + "block" + type + "Door");
		this.setCreativeTab(Utils.getCurrentExtendingMod().getCreativeTab());
	}
}
