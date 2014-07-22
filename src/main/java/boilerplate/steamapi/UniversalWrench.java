package boilerplate.steamapi;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import boilerplate.common.baseclasses.RootItem;
//Reference implementation of IUniversalWrench
public class UniversalWrench extends RootItem implements IUniversalWrench
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
