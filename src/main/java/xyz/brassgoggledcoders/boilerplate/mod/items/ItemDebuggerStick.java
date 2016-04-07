package xyz.brassgoggledcoders.boilerplate.mod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.api.IDebuggable;
import xyz.brassgoggledcoders.boilerplate.lib.common.items.ItemBase;
import xyz.brassgoggledcoders.boilerplate.mod.Boilerplate;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * @author SkySom
 */
public class ItemDebuggerStick extends ItemBase
{
	public ItemDebuggerStick()
	{
		super("debugger_stick");
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		MovingObjectPosition rayTrace = this.getMovingObjectPositionFromPlayer(world, entityPlayer, true);
		IDebuggable debuggable = null;
		if(rayTrace != null && rayTrace.typeOfHit != null)
		{
			if (rayTrace.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
			{
				TileEntity tileEntity = world.getTileEntity(rayTrace.getBlockPos());
				{
					if (tileEntity instanceof IDebuggable)
					{
						debuggable = (IDebuggable)tileEntity;
					}
				}
			}
			else if (rayTrace.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY)
			{
				Entity entity = rayTrace.entityHit;
				if (entity instanceof IDebuggable)
				{
					debuggable = (IDebuggable)entity;
				}
			}
		}

		if (debuggable != null)
		{
			LinkedHashMap<String, String> debugStrings = debuggable.getDebugStrings();
			if(debugStrings != null && !debugStrings.isEmpty())
			{
				Iterator<String> iterator = debugStrings.values().iterator();
				while(iterator.hasNext())
				{
					Boilerplate.logger.info(iterator.next());
				}
			}

		}

		return itemStack;
	}
}
