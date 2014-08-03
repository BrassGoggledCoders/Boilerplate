package boilerplate.steamapi;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IModule
{
	//0 is helmet, 1 is plate, 2 is legs and 3 is boots. Return -1 for any piece
	public int getApplicablePiece();
	public String getName();
	public void getArmorEffect(World world, EntityPlayer player, ItemStack stack);
	public EnumArmorEffectType getArmorEffectType();
}
