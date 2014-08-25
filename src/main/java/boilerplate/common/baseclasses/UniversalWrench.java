/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.common.baseclasses;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import boilerplate.steamapi.item.IUniversalWrench;

/**
 * @author decebaldecebal
 * 
 */
public abstract class UniversalWrench extends RootItem implements IUniversalWrench
{
	@Override
	public boolean canWrench(EntityPlayer player, int x, int y, int z)
	{
		return true;
	}

	@Override
	public void wrenchUsed(EntityPlayer player, int x, int y, int z)
	{
		player.inventory.addItemStackToInventory(new ItemStack(player.worldObj.getBlock(x, y, z)));
		player.worldObj.setBlockToAir(x, y, z);
	}

}
