package boilerplate.common.baseclasses;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

public class BaseMetadataBlock extends Block
{
	public IIcon[] icon = new IIcon[] { null };

	protected BaseMetadataBlock(Material p_i45394_1_)
	{
		super(p_i45394_1_);
	}
}
