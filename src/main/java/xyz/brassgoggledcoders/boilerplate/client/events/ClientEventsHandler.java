package xyz.brassgoggledcoders.boilerplate.client.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.brassgoggledcoders.boilerplate.client.ClientHelper;
import xyz.brassgoggledcoders.boilerplate.client.IBlockOverlayText;
import xyz.brassgoggledcoders.boilerplate.items.ToolUtils;

/**
 * @author SkySom
 */
public class ClientEventsHandler {
	@SubscribeEvent()
	public void onRenderOverlayPost(RenderGameOverlayEvent.Post event) {
		// TODO: Try with Entities eventually
		RayTraceResult rayTrace = ClientHelper.rayTrace();
		if(rayTrace != null) {
			EntityPlayer entityPlayer = Minecraft.getMinecraft().thePlayer;
			boolean hammer = entityPlayer.getHeldItemMainhand() != null
					&& ToolUtils.isItemATool(entityPlayer.getHeldItemMainhand())
					|| entityPlayer.getHeldItemOffhand() != null
							&& ToolUtils.isItemATool(entityPlayer.getHeldItemOffhand());

			if(rayTrace.getBlockPos() != null) {
				TileEntity tileEntity = entityPlayer.worldObj.getTileEntity(rayTrace.getBlockPos());
				if(tileEntity instanceof IBlockOverlayText) {
					IBlockOverlayText overlayBlock = (IBlockOverlayText) tileEntity;
					String[] text = overlayBlock.getOverlayText(entityPlayer, rayTrace, hammer);
					if(text != null && text.length > 0) {
						FontRenderer font = ClientHelper.fontRenderer();
						int col = 0xffffff;
						int i = 0;
						for(String s : text)
							if(s != null)
								font.drawString(s, event.getResolution().getScaledWidth() / 2 + 8,
										event.getResolution().getScaledHeight() / 2 + 8 + i++ * font.FONT_HEIGHT, col,
										true);
					}
				}
			}
		}
	}
}
