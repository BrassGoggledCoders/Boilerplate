package boilerplate.common;

import java.util.Arrays;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ForgeEventHandler
{
	@SubscribeEvent
	public void tickPlayer(PlayerTickEvent event)
	{
		if (event.side.isClient())
		{
			World world = event.player.worldObj;
			EntityPlayer player = event.player;
			if (Arrays.asList(Boilerplate.donors).contains(player.getCommandSenderName()))
			{
				for (int i = 0; i < 10; i++)
					world.spawnParticle("flame", player.posX + world.rand.nextDouble() - 0.5D, player.posY + world.rand.nextDouble() - 3D,
							player.posZ + world.rand.nextDouble() - 0.5D, -player.motionX, -player.motionY, -player.motionZ);
			}
		}
	}
}
