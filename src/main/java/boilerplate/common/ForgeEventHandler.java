package boilerplate.common;

import java.util.Arrays;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ForgeEventHandler
{
	// TODO There must be a more efficient way to handle this!
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void tickPlayer(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if (Arrays.asList(Boilerplate.donors).contains(player.getCommandSenderName()))
			{
				World world = player.worldObj;
				for (int i = 0; i < 10; i++)
					world.spawnParticle("flame", player.posX + world.rand.nextDouble() - 0.5D, player.posY + world.rand.nextDouble() - 3D,
							player.posZ + world.rand.nextDouble() - 0.5D, -player.motionX, -player.motionY, -player.motionZ);
			}
		}
	}
}
