package boilerplate.common.utils.helpers;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class StructureHelper
{
	//Herpa derpa
	public static boolean isStructureValid(World world, int startX, int startY, int startZ, int xSize, int ySize, int zSize, Block[] blocks)
	{
		for(int x = 0; x<xSize; x++)
		{
			for(int y = 0; y < ySize; y++)
			{
				for(int z = 0; z < zSize; z++)
				{
					if(world.getBlock(startX + x, startY + y, startZ + z) == blocks[x])
						return true;
				}
			}
		}
		return false;
	}
}
