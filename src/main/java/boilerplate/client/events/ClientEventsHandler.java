
package boilerplate.client.events;

import boilerplate.api.IBlockOverlayText;
import boilerplate.common.utils.ToolUtils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

/**
 * @author SkySom
 */
public class ClientEventsHandler
{
	@SubscribeEvent()
	public void onRenderOverlayPost(RenderGameOverlayEvent.Post event)
	{
		//TODO: Try with Entities eventually
		if(Minecraft.getMinecraft().objectMouseOver!=null)
		{
			EntityPlayer entityPlayer = Minecraft.getMinecraft().thePlayer;
			boolean hammer = entityPlayer.getCurrentEquippedItem() != null &&
					ToolUtils.isItemATool(entityPlayer.getCurrentEquippedItem());
			MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
			TileEntity tileEntity = entityPlayer.worldObj.getTileEntity(mop.blockX, mop.blockY, mop.blockZ);
			if(tileEntity instanceof IBlockOverlayText)
			{
				IBlockOverlayText overlayBlock = (IBlockOverlayText) tileEntity;
				String[] text = overlayBlock.getOverlayText(entityPlayer, mop, hammer);
				if(text!=null && text.length>0)
				{
					FontRenderer font = Minecraft.getMinecraft().fontRenderer;
					int col = 0xffffff;
					int i = 0;
					for(String s : text)
					{
						if(s!=null)
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
