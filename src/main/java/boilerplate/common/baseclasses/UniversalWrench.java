/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.common.baseclasses;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityMinecart;
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

	}

	@Override
	public boolean isUsable(ItemStack item, EntityLivingBase user, int x, int y, int z)
	{
		return true;
	}

	@Override
	public void toolUsed(ItemStack item, EntityLivingBase user, int x, int y, int z)
	{

	}

	@Override
	public boolean canWhack(EntityPlayer player, ItemStack crowbar, int x, int y, int z)
	{
		return true;
	}

	@Override
	public void onWhack(EntityPlayer player, ItemStack crowbar, int x, int y, int z)
	{
		crowbar.damageItem(2, player);
		player.swingItem();
	}

	@Override
	public boolean canLink(EntityPlayer player, ItemStack crowbar, EntityMinecart cart)
	{
		return true;
	}

	@Override
	public void onLink(EntityPlayer player, ItemStack crowbar, EntityMinecart cart)
	{
		crowbar.damageItem(2, player);
		player.swingItem();
	}

	@Override
	public boolean canBoost(EntityPlayer player, ItemStack crowbar, EntityMinecart cart)
	{
		return true;
	}

	@Override
	public void onBoost(EntityPlayer player, ItemStack crowbar, EntityMinecart cart)
	{
		crowbar.damageItem(2, player);
		player.swingItem();
	}
}
