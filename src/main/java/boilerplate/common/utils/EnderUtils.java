/**
 * This class was created by <Surseance> as a part of the
 * EnderGlove mod for Minecraft.
 *
 * This mod is registered under the WTFPL v2.0. Please read the
 * COPYING.WTFPL file for more details.
 *
 * File created @[May 14, 2014, 9:07:32 PM]
 */
package boilerplate.common.utils;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import boilerplate.common.entity.EntityMinedBlock;

/**
 * @author Surseance (Johnny Eatmon)
 * Email: surseance@autistici.org
 *
 */
public class EnderUtils
{

	//private boolean handleTileEntities() // TODO: Some handling for TileEntities
	//{
	//	return false;
	//}

	// XXX: doesn't work for some weird reason; keeps returning 4
	public static int getRotationMeta(EntityLivingBase entLiving)
	{
		int md = 0;
		int rot = MathHelper.floor_double((double)(entLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		switch (rot)
		{
			case 0:
				md = 2;
			case 1:
				md = 5;
			case 2:
				md = 3;
			case 3:
				md = 4;
		}

		return md;
	}

	@Deprecated
	public static void spawnBlockEntity(EntityPlayer player, Block block, int x, int y, int z, int md, ItemStack drops)
	{
		InventoryEnderChest enderInv = InventoryUtils.getPlayerEnderChest(player);

		if ((player.worldObj.isRemote) && (InventoryUtils.isInvEmpty(enderInv, drops)))
			player.worldObj.spawnEntityInWorld(new EntityMinedBlock(player.worldObj, x + 0.5F, y + 0.5F, z + 0.5F, block, md, true));
	}
}
