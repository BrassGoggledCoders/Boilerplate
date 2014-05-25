/**
 * This class was created by <Surseance> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ 25-May-2014
 */
package boilerplate.common.utils;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.world.World;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerUtils.
 */
public class PlayerUtils
{
	
	/**
	 * Send to players.
	 *
	 * @param packet the packet
	 * @param world the world
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @param maxDistance the max distance
	 */
	public static void sendToPlayers(final Packet packet, final World world,
			final int x, final int y, final int z, Integer maxDistance)
	{
		if (maxDistance == null)
		{
			maxDistance = Integer.valueOf(128);
		}

		Iterator<?> iterator;

		if (packet != null)
		{
			for (iterator = world.playerEntities.iterator(); iterator.hasNext();)
			{
				final Object player = iterator.next();
				final EntityPlayerMP playerMP = (EntityPlayerMP) player;

				if ((Math.abs(playerMP.posX - x) <= maxDistance.intValue())
						&& (Math.abs(playerMP.posY - y) <= maxDistance
								.intValue())
						&& (Math.abs(playerMP.posZ - z) <= maxDistance
								.intValue()))
				{
					playerMP.playerNetServerHandler.sendPacket(packet);
				}
			}
		}
	}
}
