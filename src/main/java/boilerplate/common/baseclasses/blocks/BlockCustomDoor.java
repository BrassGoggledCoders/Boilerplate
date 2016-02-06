
package boilerplate.common.baseclasses.blocks;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;

import boilerplate.common.IBoilerplateMod;

/**
 * @author warlordjones
 *
 */
public class BlockCustomDoor extends BlockDoor
{
	public BlockCustomDoor(String type, IBoilerplateMod mod)
	{
		super(Material.wood);
		this.setBlockTextureName(mod.getModInfo().getPrefix() + "block" + type + "Door");
		this.setCreativeTab(mod.getCreativeTab());
	}
}
