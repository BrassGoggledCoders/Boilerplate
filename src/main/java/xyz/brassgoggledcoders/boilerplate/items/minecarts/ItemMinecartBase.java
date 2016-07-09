package xyz.brassgoggledcoders.boilerplate.items.minecarts;

import com.mojang.authlib.GameProfile;
import mods.railcraft.api.core.items.IMinecartItem;
import net.minecraft.block.BlockDispenser;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import xyz.brassgoggledcoders.boilerplate.client.models.IHasModel;
import xyz.brassgoggledcoders.boilerplate.entity.minecarts.EntityMinecartBase;
import xyz.brassgoggledcoders.boilerplate.utils.BlockUtils;

import javax.annotation.Nonnull;

@Optional.Interface(modid = "RailcraftAPI|items", iface = "mods.railcraft.api.core.items.IMinecartItem")
public abstract class ItemMinecartBase extends ItemMinecart implements IMinecartItem, IHasModel
{
	public ItemMinecartBase(String name)
	{
		super(EntityMinecart.Type.TNT);
		this.setUnlocalizedName(name);
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, new DispenseItemMinecartBase());
	}

	@Override
	@Nonnull
	public EnumActionResult onItemUse(@Nonnull ItemStack itemStack, EntityPlayer player, World world,
			@Nonnull BlockPos blockPos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		return placeCart(itemStack, world, blockPos, this.getEntityFromItem(world, itemStack));
	}

	@Override
	@Optional.Method(modid = "RailcraftAPI|items")
	public boolean canBePlacedByNonPlayer(ItemStack itemStack)
	{
		return itemStack != null;
	}

	@Override
	@Optional.Method(modid = "RailcraftAPI|items")
	public EntityMinecart placeCart(GameProfile gameProfile, ItemStack itemStack, World world, int posX, int posY, int posZ)
	{
		BlockPos blockPos = new BlockPos(posX, posY, posZ);
		EntityMinecartBase entityMinecart = getEntityFromItem(world, itemStack);
		if (placeCart(itemStack, world, blockPos, entityMinecart) == EnumActionResult.SUCCESS)
		{
			return entityMinecart;
		}
		return null;
	}

	public EnumActionResult placeCart(ItemStack itemStack, World world, BlockPos blockPos, EntityMinecartBase entityMinecart)
	{
		if (itemStack != null && BlockUtils.isRailBlock(world.getBlockState(blockPos)))
		{
			if(itemStack.hasDisplayName())
			{
				entityMinecart.setCustomNameTag(itemStack.getDisplayName());
			}

			if (!world.isRemote)
			{
				entityMinecart.posX = (float)blockPos.getX() + 0.5F;
				entityMinecart.posY = (float)blockPos.getY() + 0.5F;
				entityMinecart.posZ = (float)blockPos.getZ() + 0.5F;
				world.spawnEntityInWorld(entityMinecart);
			}
			--itemStack.stackSize;
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}

	@Override
	public String[] getResourceLocations()
	{
		return new String[] {""};
	}

	@Nonnull
	public abstract EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack);
}

