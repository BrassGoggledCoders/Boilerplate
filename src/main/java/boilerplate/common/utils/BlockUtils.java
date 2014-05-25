package boilerplate.common.utils;

import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class BlockUtils
{
	public static Material getBlockMaterial(final IBlockAccess world,
			final int x, final int y, final int z)
	{
		if (world.getBlock(x, y, z) != null)
		{
			return world.getBlock(x, y, z).getMaterial();
		}

		return Material.air;
	}
}
