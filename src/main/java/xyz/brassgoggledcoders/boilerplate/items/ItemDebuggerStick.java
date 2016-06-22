package xyz.brassgoggledcoders.boilerplate.items;

import java.util.LinkedHashMap;

import javax.annotation.Nonnull;

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

/**
 * @author SkySom
 */
public class ItemDebuggerStick extends ItemBase {
	public ItemDebuggerStick() {
		super("debugger_stick");
		this.setCreativeTab(CreativeTabs.MISC);
	}

	@Override
	@Nonnull
	public ActionResult<ItemStack> onItemRightClick(@Nonnull ItemStack itemStack, World world, EntityPlayer player,
			EnumHand hand) {
		RayTraceResult rayTrace = rayTrace(world, player, true);
		LinkedHashMap<String, String> debugStrings = new LinkedHashMap<String, String>();

		if(rayTrace != null && rayTrace.typeOfHit != null)
			if(rayTrace.typeOfHit == RayTraceResult.Type.BLOCK) {
				TileEntity tileEntity = world.getTileEntity(rayTrace.getBlockPos());
				{
					if(tileEntity instanceof IDebuggable)
						debugStrings.putAll(((IDebuggable) tileEntity).getDebugStrings());
					else
						debugStrings.put("name", world.getBlockState(rayTrace.getBlockPos()).toString());
				}
			}
			else if(rayTrace.typeOfHit == RayTraceResult.Type.ENTITY) {
				Entity entity = rayTrace.entityHit;
				if(entity instanceof IDebuggable)
					debugStrings.putAll(((IDebuggable) entity).getDebugStrings());
				else
					debugStrings.put("name", entity.getName());
			}

		if(!debugStrings.isEmpty()) {
			for(String debugString : debugStrings.values())
				getMod().getLogger().info(debugString);
			return ActionResult.newResult(EnumActionResult.SUCCESS, itemStack);
		}

		return ActionResult.newResult(EnumActionResult.PASS, itemStack);
	}

	@Override
	public String[] getResourceLocations() {
		return new String[] {"debugger_stick"};
	}
}
