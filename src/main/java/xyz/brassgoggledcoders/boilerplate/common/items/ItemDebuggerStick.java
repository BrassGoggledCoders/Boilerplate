package xyz.brassgoggledcoders.boilerplate.common.items;

import java.util.ArrayList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import xyz.brassgoggledcoders.boilerplate.api.IDebuggable;
import xyz.brassgoggledcoders.boilerplate.common.Boilerplate;

/**
 * @author SkySom
 */
public class ItemDebuggerStick extends Item
{
	// TODO: Figure out models
	public ItemDebuggerStick()
	{
		super();
		this.setUnlocalizedName("debuggerstick");
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		MovingObjectPosition rayTrace = this.getMovingObjectPositionFromPlayer(world, entityPlayer, true);
		ArrayList<String> chatArray = new ArrayList<String>();
		if (rayTrace.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
		{
			TileEntity tileEntity = world.getTileEntity(rayTrace.getBlockPos());
			{
				if (tileEntity instanceof IDebuggable)
				{
					chatArray = ((IDebuggable) tileEntity).debug();
				}
			}
		}
		else if (rayTrace.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY)
		{
			Entity entity = rayTrace.entityHit;
			if (entity instanceof IDebuggable)
			{
				chatArray = ((IDebuggable) entity).debug();
			}
		}

		if (chatArray != null && chatArray.size() > 0)
		{
			for (String string : chatArray)
			{
				Boilerplate.logger.info(string);
			}
		}

		return itemStack;
	}
}
