package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.lib.common.tileentities.BaseTileWithInventory;

import java.util.Random;

/**
 * @author decebaldecebal
 *
 */
public abstract class BaseContainerBlock extends BlockTEBase
{
	protected static boolean keepInventory = true;

	protected BaseContainerBlock(Material mat, String name)
	{
		super(mat, name);
		this.setHardness(5.0F);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		if (!keepInventory)
		{
			TileEntity tile = world.getTileEntity(pos);

			if ((tile != null) && (tile instanceof BaseTileWithInventory))
			{
				BaseTileWithInventory invTile = (BaseTileWithInventory) tile;

				for (int var8 = 0; var8 < invTile.getSizeInventory(); ++var8)
				{
					ItemStack var9 = invTile.getStackInSlot(var8);

					if (var9 != null)
					{
						Random random = world.rand;
						float var10 = (random.nextFloat() * 0.8F) + 0.1F;
						float var11 = (random.nextFloat() * 0.8F) + 0.1F;
						float var12 = (random.nextFloat() * 0.8F) + 0.1F;

						while (var9.stackSize > 0)
						{
							int var13 = random.nextInt(21) + 10;

							if (var13 > var9.stackSize)
								var13 = var9.stackSize;

							var9.stackSize -= var13;
							EntityItem var14 = new EntityItem(world, pos.getX() + var10, pos.getY() + var11, pos.getZ() + var12,
									new ItemStack(var9.getItem(), var13, var9.getItemDamage()));

							if (var9.hasTagCompound())
								var14.getEntityItem().setTagCompound((NBTTagCompound) var9.getTagCompound().copy());

							float var15 = 0.05F;
							var14.motionX = (float) random.nextGaussian() * var15;
							var14.motionY = ((float) random.nextGaussian() * var15) + 0.2F;
							var14.motionZ = (float) random.nextGaussian() * var15;
							world.spawnEntityInWorld(var14);
						}
					}
				}
			}
		}

		super.breakBlock(world, pos, state);
	}
}
