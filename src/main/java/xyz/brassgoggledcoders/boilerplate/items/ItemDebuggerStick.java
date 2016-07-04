package xyz.brassgoggledcoders.boilerplate.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.api.IDebuggable;

import javax.annotation.Nonnull;
import java.util.LinkedHashMap;

public class ItemDebuggerStick extends ItemBase {
	public ItemDebuggerStick() {
		super("debugger_stick");
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if(entity instanceof IDebuggable) {
			this.writeDebug((IDebuggable)entity);
			return true;
		}
		return false;
	}

	@Override
	@Nonnull
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		TileEntity tileEntity = world.getTileEntity(pos);
		if(tileEntity instanceof IDebuggable) {
			this.writeDebug((IDebuggable)tileEntity);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}

	public void writeDebug(IDebuggable debuggable) {
		LinkedHashMap<String, String> debugStrings = new LinkedHashMap<String, String>();

		debugStrings = debuggable.getDebugStrings(debugStrings);

		if(!debugStrings.isEmpty()) {
			for(String debugString : debugStrings.values()) {
				this.getMod().getLogger().info(debugString);
			}
		}
	}

	@Override
	public String[] getResourceLocations() {
		return new String[] {"debugger_stick"};
	}
}
