/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.steamapi;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author warlordjones
 *
 */
public interface IArmorModule
{
	// 0 is helmet, 1 is plate, 2 is legs and 3 is boots. Return -1 for any piece
	public int getApplicablePiece();

	public String getName();

	public void getArmorEffect(World world, EntityPlayer player, ItemStack stack);

	public EnumArmorEffectType getArmorEffectType();

	public static enum EnumArmorEffectType
	{
		ONTICK,
		DEFENSIVE
	}
}
