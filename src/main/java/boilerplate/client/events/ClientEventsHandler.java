package boilerplate.client.events;

import boilerplate.api.IBlockOverlayText;
import boilerplate.client.ClientHelper;
import boilerplate.common.utils.Tools;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author SkySom
 */
public class ClientEventsHandler
{
	@SubscribeEvent()
	public void onRenderOverlayPost(RenderGameOverlayEvent.Post event)
	{
		//TODO: Try with Entities eventually
		if(ClientHelper.mop() != null)
		{
			EntityPlayer entityPlayer = Minecraft.getMinecraft().thePlayer;
			boolean hammer = entityPlayer.getCurrentEquippedItem() != null &&
					Tools.isItemATool(entityPlayer.getCurrentEquippedItem());
			MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
			TileEntity tileEntity = entityPlayer.worldObj.getTileEntity(mop.getBlockPos());
			if(tileEntity instanceof IBlockOverlayText)
			{
				IBlockOverlayText overlayBlock = (IBlockOverlayText) tileEntity;
				String[] text = overlayBlock.getOverlayText(entityPlayer, mop, hammer);
				if(text!=null && text.length>0)
				{
					FontRenderer font = ClientHelper.fontRenderer();
					int col = 0xffffff;
					int i = 0;
					for(String s : text)
					{
						if(s != null)
						{
							font.drawString(s, event.resolution.getScaledWidth() / 2 + 8, event.resolution.getScaledHeight()
									/ 2+8+(i++) * font.FONT_HEIGHT, col, true);
						}
					}

				}
			}
		}
	}
}
