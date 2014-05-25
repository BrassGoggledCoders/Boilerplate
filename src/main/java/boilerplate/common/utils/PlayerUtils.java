package boilerplate.common.utils;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.world.World;

public class PlayerUtils
{
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
