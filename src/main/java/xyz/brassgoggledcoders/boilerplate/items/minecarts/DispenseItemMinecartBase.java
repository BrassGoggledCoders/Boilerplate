package xyz.brassgoggledcoders.boilerplate.items.minecarts;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.entity.minecarts.EntityMinecartBase;
import xyz.brassgoggledcoders.boilerplate.utils.ItemStackUtils;

import javax.annotation.Nonnull;

public class DispenseItemMinecartBase extends BehaviorDefaultDispenseItem
{
	@Override
	@Nonnull
	public ItemStack dispenseStack(IBlockSource dispenser, ItemStack cartItemStack)
	{
		EntityMinecartBase entityMinecartBase;
		World world = dispenser.getWorld();
		EnumActionResult cartSpawned = EnumActionResult.PASS;
		if(ItemStackUtils.isItemInstanceOf(cartItemStack, ItemMinecartBase.class))
		{
			ItemMinecartBase itemMinecartBase = (ItemMinecartBase)cartItemStack.getItem();
			entityMinecartBase = itemMinecartBase.getEntityFromItem(world, cartItemStack);

			if(entityMinecartBase != null)
			{
				EnumFacing enumfacing = BlockDispenser.getFacing(dispenser.getBlockMetadata());
				BlockPos blockpos = dispenser.getBlockPos().offset(enumfacing);
				cartSpawned = itemMinecartBase.placeCart(cartItemStack, world, blockpos, entityMinecartBase);
			}
		}

		if(cartSpawned != EnumActionResult.SUCCESS)
		{
			cartItemStack = super.dispenseStack(dispenser, cartItemStack);
		}

		return cartItemStack;
	}
}
