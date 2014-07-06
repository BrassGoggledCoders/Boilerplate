package boilerplate.steamapi;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IModule
{
	public EnumApplicablePiece getApplicablePiece();
	public String getName();
	public void getArmorEffect(World world, EntityPlayer player, ItemStack stack);
	public EnumArmorEffectType getArmorEffectType();
}
