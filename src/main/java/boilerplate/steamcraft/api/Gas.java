/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [May 7, 2014, 11:31:42 AM]
 */
package boilerplate.steamcraft.api;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;

// TODO: Auto-generated Javadoc
/**
 * The Class Gas.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class Gas extends BlockContainer implements IFluidBlock
{
	
	/** The gas. */
	public Fluid gas;
	
	/** The is flammable. */
	public static boolean isFlammable;
	
	/** The is explosive. */
	public static boolean isExplosive;
	
	/** The dissipation height. */
	public int dissipationHeight;
	
	/** The viscosity. */
	public int viscosity;

	/** The icon. */
	protected IIcon[] icon = new IIcon[16];

	/**
	 * Instantiates a new gas.
	 */
	public Gas()
	{
		super(new MaterialGas(false));
		setCreativeTab((CreativeTabs) null);
		Gas.isFlammable = false;
		Gas.isExplosive = false;
		dissipationHeight = 10;
		viscosity = 5;
	}

	/**
	 * Instantiates a new gas.
	 *
	 * @param canBurn the can burn
	 * @param lightLevel the light level
	 * @param isFlammable the is flammable
	 * @param isExplosive the is explosive
	 * @param disHeight the dis height
	 * @param viscosity the viscosity
	 */
	public Gas(final boolean canBurn, final int lightLevel,
			final boolean isFlammable, final boolean isExplosive,
			final int disHeight, final int viscosity)
	{
		super(new MaterialGas(canBurn));
		setLightLevel(lightLevel);
		setCreativeTab((CreativeTabs) null);
		Gas.isFlammable = isFlammable;
		Gas.isExplosive = isExplosive;
		dissipationHeight = disHeight;
		this.viscosity = viscosity;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.ITileEntityProvider#createNewTileEntity(net.minecraft.world.World, int)
	 */
	@Override
	public TileEntity createNewTileEntity(final World world, final int metadata)
	{
		return new TileGas(getFluid(), dissipationHeight, viscosity);
	}

	/* (non-Javadoc)
	 * @see net.minecraftforge.fluids.IFluidBlock#getFluid()
	 */
	@Override
	public Fluid getFluid()
	{
		return gas;
	}

	/* (non-Javadoc)
	 * @see net.minecraftforge.fluids.IFluidBlock#drain(net.minecraft.world.World, int, int, int, boolean)
	 */
	@Override
	public FluidStack drain(final World world, final int x, final int y,
			final int z, final boolean doDrain)
	{
		final TileEntity te = world.getTileEntity(x, y, z);

		if (te != null && te instanceof TileGas)
		{
			final FluidStack fluid = TileGas.getGas();

			if (doDrain)
			{
				world.setBlockToAir(x, y, z);
				world.removeTileEntity(x, y, z);
			}

			return fluid;
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see net.minecraftforge.fluids.IFluidBlock#canDrain(net.minecraft.world.World, int, int, int)
	 */
	@Override
	public boolean canDrain(final World world, final int x, final int y,
			final int z)
	{
		final TileEntity te = world.getTileEntity(x, y, z);

		if (te != null && te instanceof TileGas)
		{
			return true;
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see net.minecraftforge.fluids.IFluidBlock#getFilledPercentage(net.minecraft.world.World, int, int, int)
	 */
	@Override
	public float getFilledPercentage(final World world, final int x,
			final int y, final int z)
	{
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#getRenderBlockPass()
	 */
	@Override
	public int getRenderBlockPass()
	{
		return 1; // 0 for solids, 1 for alpha
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#isOpaqueCube()
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#renderAsNormalBlock()
	 */
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#quantityDropped(java.util.Random)
	 */
	@Override
	public int quantityDropped(final Random random)
	{
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#getCollisionBoundingBoxFromPool(net.minecraft.world.World, int, int, int)
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world,
			final int x, final int y, final int z)
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#onBlockDestroyedByExplosion(net.minecraft.world.World, int, int, int, net.minecraft.world.Explosion)
	 */
	@Override
	public void onBlockDestroyedByExplosion(final World world, final int x,
			final int y, final int z, final Explosion explosion)
	{
		if (Gas.isExplosive)
		{
			Gas.createExplosion(world, x, y, z);
		}
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#onNeighborBlockChange(net.minecraft.world.World, int, int, int, net.minecraft.block.Block)
	 */
	@Override
	public void onNeighborBlockChange(final World world, final int x,
			final int y, final int z, final Block block)
	{
		if ((block == Blocks.torch) || (block == Blocks.fire)
				|| (block == Blocks.lava))
		{
			if (Gas.isExplosive)
			{
				Gas.createExplosion(world, x, y, z);
			}
			else if (Gas.isFlammable)
			{
				Gas.setFire(world, x, y, z);
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.BlockContainer#onBlockAdded(net.minecraft.world.World, int, int, int)
	 */
	@Override
	public void onBlockAdded(final World world, final int x, final int y,
			final int z)
	{
		super.onBlockAdded(world, x, y, z);

		final TileEntity te = world.getTileEntity(x, y, z);

		if (te != null && te instanceof TileGas)
		{
			final int volume = TileGas.getGasAmount();
			final int metadata = (volume * 15) / TileGas.VOLUME;
			world.setBlockMetadataWithNotify(x, y, z, metadata, 4);
		}
	}

	/**
	 * Creates the explosion.
	 *
	 * @param world the world
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public static void createExplosion(final World world, final int x,
			final int y, final int z)
	{
		// TODO: send to packet handlers
		world.newExplosion((Entity) null, x, y, z,
				(world.rand.nextFloat() / 0.5F) + 0.9F, isFlammable, true);
	}

	/**
	 * Sets the fire.
	 *
	 * @param world the world
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public static void setFire(final World world, final int x, final int y,
			final int z)
	{
		// TODO: send to packet handlers
		int tempX = x - 1;
		int tempY = y - 1;
		int tempZ = y - 2;

		while ((world.getBlock(tempX, tempY, tempZ) instanceof Gas)
				|| (world.getBlock(tempX, tempY, tempZ).isAir(world, tempX,
						tempY, tempZ))
				|| (world.getBlock(tempX, tempY, tempZ).isReplaceable(world,
						tempX, tempY, tempZ)))
		{
			tempX--;
			tempY--;
			tempZ--;
		}

		tempX++;
		tempY++;
		tempZ++;

		world.setBlockToAir(x, y, z);
		world.removeTileEntity(x, y, z);
		world.setBlock(tempX, tempY, tempZ, Blocks.fire);
	}
}
