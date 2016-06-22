/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 */
package xyz.brassgoggledcoders.boilerplate.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.items.ToolUtils;
import xyz.brassgoggledcoders.boilerplate.tileentities.TileEntitySidedBase;

/**
 * @author SkySom
 */
public abstract class BlockSidedBase extends BlockTEBase {
	protected BlockSidedBase(Material material, String name) {
		super(material, name);
		setHardness(3.0F);
		setResistance(15.0F);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!world.isRemote) {
			TileEntity te = world.getTileEntity(pos);
			if(te instanceof TileEntitySidedBase) {
				TileEntitySidedBase tileEntitySided = (TileEntitySidedBase) te;
				if(ToolUtils.isItemATool(heldItem)) {
					if(player.isSneaking())
						side = side.getOpposite();
					tileEntitySided.toggleSide(side.ordinal());
					return true;
				}
			}
		}
		return false;
	}
}
