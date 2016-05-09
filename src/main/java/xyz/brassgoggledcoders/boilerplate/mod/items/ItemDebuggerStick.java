package xyz.brassgoggledcoders.boilerplate.mod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
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

	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand)
	{
		RayTraceResult rayTrace = getMovingObjectPositionFromPlayer(world, player, true);

		LinkedHashMap<String, String> debugStrings = new LinkedHashMap<String, String>();
		if(rayTrace != null && rayTrace.typeOfHit != null)
		{
			if (rayTrace.typeOfHit == RayTraceResult.Type.BLOCK)
			{
				TileEntity tileEntity = world.getTileEntity(rayTrace.getBlockPos());
				{
					if (tileEntity instanceof IDebuggable)
					{
						debugStrings.putAll(((IDebuggable) tileEntity).getDebugStrings());
					} else {
						debugStrings.put("name", world.getBlockState(rayTrace.getBlockPos()).toString());
					}
				}
			}
			else if (rayTrace.typeOfHit == RayTraceResult.Type.ENTITY)
			{
				Entity entity = rayTrace.entityHit;
				if (entity instanceof IDebuggable)
				{
					debugStrings.putAll(((IDebuggable) entity).getDebugStrings());
				} else {
					debugStrings.put("name", entity.getName());
				}
			}
		}

		if(!debugStrings.isEmpty())
		{
			Iterator<String> iterator = debugStrings.values().iterator();
			while(iterator.hasNext())
			{
				Boilerplate.logger.info(iterator.next());
			}
			return ActionResult.newResult(EnumActionResult.SUCCESS, itemStack);
		}

		return ActionResult.newResult(EnumActionResult.PASS, itemStack);
	}

	@Override
	public String[] getResourceLocations()
	{
		return new String[] {"debugger_stick"};
	}
}
