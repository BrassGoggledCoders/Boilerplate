/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.common.baseclasses;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

/**
 * @author warlordjones
 * 
 */
public class BaseItemBlockWithMetadata extends ItemBlockWithMetadata
{
	public BaseItemBlockWithMetadata(Block block)
	{
		super(block, block);
	}
	
	public BaseItemBlockWithMetadata(Block block1, Block block2)
	{
		super(block1, block2);
		
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int metadata)
	{
		return metadata;
	}

	@Override
	public String getUnlocalizedName(ItemStack is)
	{
		return super.getUnlocalizedName() + "." + is.getItemDamage();
	}
}
