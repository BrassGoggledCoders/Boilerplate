
package boilerplate.common.baseclasses.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.IBoilerplateMod;
import boilerplate.common.baseclasses.BaseTileWithInventory;
import boilerplate.common.utils.Utils;
import cofh.api.block.IDismantleable;

/**
 * @author decebaldecebal
 *
 */
public abstract class BaseContainerBlock extends BlockContainer implements IDismantleable
{
	IBoilerplateMod mod;
	protected static boolean keepInventory = true;

	protected BaseContainerBlock(Material mat)
	{
		super(mat);
		this.mod = Utils.getCurrentMod();
		this.setCreativeTab(mod.getCreativeTab());
		this.setHardness(5.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.blockIcon = ir.registerIcon(mod.getPrefix() + this.getUnlocalizedName().substring(5));
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block block, int par6)
	{
		if (!keepInventory)
		{
			TileEntity tile = par1World.getTileEntity(par2, par3, par4);

			if ((tile != null) && (tile instanceof BaseTileWithInventory))
			{
				BaseTileWithInventory invTile = (BaseTileWithInventory) tile;

				for (int var8 = 0; var8 < invTile.getSizeInventory(); ++var8)
				{
					ItemStack var9 = invTile.getStackInSlot(var8);

					if (var9 != null)
					{
						Random random = par1World.rand;
						float var10 = (random.nextFloat() * 0.8F) + 0.1F;
						float var11 = (random.nextFloat() * 0.8F) + 0.1F;
						float var12 = (random.nextFloat() * 0.8F) + 0.1F;

						while (var9.stackSize > 0)
						{
							int var13 = random.nextInt(21) + 10;

							if (var13 > var9.stackSize)
								var13 = var9.stackSize;

							var9.stackSize -= var13;
							EntityItem var14 = new EntityItem(par1World, par2 + var10, par3 + var11, par4 + var12,
									new ItemStack(var9.getItem(), var13, var9.getItemDamage()));

							if (var9.hasTagCompound())
								var14.getEntityItem().setTagCompound((NBTTagCompound) var9.getTagCompound().copy());

							float var15 = 0.05F;
							var14.motionX = (float) random.nextGaussian() * var15;
							var14.motionY = ((float) random.nextGaussian() * var15) + 0.2F;
							var14.motionZ = (float) random.nextGaussian() * var15;
							par1World.spawnEntityInWorld(var14);
						}
					}
				}
			}
		}

		super.breakBlock(par1World, par2, par3, par4, block, par6);
	}

	@Override
	public ArrayList<ItemStack> dismantleBlock(EntityPlayer player, World world, int x, int y, int z, boolean returnDrops)
	{
		this.breakBlock(world, x, y, z, this, 0);

		ArrayList<ItemStack> localArrayList = new ArrayList<ItemStack>();
		localArrayList.add(new ItemStack(this));

		return localArrayList;

	}

	@Override
	public boolean canDismantle(EntityPlayer player, World world, int x, int y, int z)
	{
		return (world.getTileEntity(x, y, z) != null) && (world.getTileEntity(x, y, z) instanceof BaseTileWithInventory);
	}
}
