/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.lib.common.items.ToolUtils;
import xyz.brassgoggledcoders.boilerplate.lib.common.tileentities.TileEntitySided;

/**
 * @author SkySom
 */
public abstract class SidedBlock extends BaseTEBlock
{
	protected SidedBlock(Material material)
	{
		super(material);
		setHardness(3.0F);
		setResistance(15.0F);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		TileEntity te = world.getTileEntity(pos);
		if(te instanceof TileEntitySided)
		{
			if(!world.isRemote)
			{
				TileEntitySided tileEntitySided = (TileEntitySided) te;
				if(ToolUtils.isItemATool(heldItem))
				{
					if(player.isSneaking())
					{
						side = side.getOpposite();
					}
					tileEntitySided.toggleSide(side.ordinal());
					return true;
				}
			}
		}
		return false;
	}
}
