package boilerplate.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockTest extends Block
{

	protected BlockTest(Material mat)
	{
		super(mat);
		setCreativeTab(CreativeTabs.tabMisc);
	}

}
