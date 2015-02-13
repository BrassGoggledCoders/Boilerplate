/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package boilerplate.common;

import java.util.Arrays;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ForgeEventHandler
{
	@SubscribeEvent
	public void tickPlayer(PlayerTickEvent event)
	{
		if (event.side.isClient() && (Boilerplate.trailParticles > 0))
		{
			World world = event.player.worldObj;
			EntityPlayer player = event.player;
			if(player.isAirBorne)
			{
				if (Arrays.asList(Boilerplate.donors).contains(player.getCommandSenderName()))
				{
					for (int i = 0; i < Boilerplate.trailParticles; i++)
						world.spawnParticle("iconcrack_" + Item.getIdFromItem(Items.gold_ingot), (player.posX + world.rand.nextDouble()) - 0.7D,
								(player.posY + world.rand.nextDouble()) - 2.2D, (player.posZ + world.rand.nextDouble()) - 0.7D, -player.motionX,
								-player.motionY, -player.motionZ);
				}
				/* else */if (Arrays.asList(Boilerplate.devs).contains(player.getCommandSenderName()))
				{
					for (int i = 0; i < Boilerplate.trailParticles; i++)
						world.spawnParticle("flame", (player.posX + world.rand.nextDouble()) - 0.7D, (player.posY + world.rand.nextDouble()) - 3.2D,
								(player.posZ + world.rand.nextDouble()) - 0.7D, -player.motionX, -player.motionY, -player.motionZ);
				}
			}
		}
	}
}
