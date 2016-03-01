package xyz.brassgoggledcoders.boilerplate.lib.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

/**
 * @author IE Team
 */
public interface IBlockOverlayText
{
	String[] getOverlayText(EntityPlayer player, MovingObjectPosition mop, boolean tool);
}
