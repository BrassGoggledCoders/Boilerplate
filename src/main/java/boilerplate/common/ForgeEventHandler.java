package boilerplate.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ForgeEventHandler
{
	@SubscribeEvent
	public void onPlayerUseItem(PlayerUseItemEvent.Tick event)
	{
		// if (event.item.getItem() == Items.potionitem &&
		// event.item.getItemDamage() == 8238)
		{
			EntityPlayer player = event.entityPlayer;
			player.worldObj.playAuxSFXAtEntity(player, 2000, player.serverPosX, player.serverPosY, player.serverPosZ, 4);
		}
	}
}
