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
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * The Class PlayerUtils.
 */
public class PlayerUtils
{
	// Grabs a vector from the player
	public static MovingObjectPosition getTargetBlock(World world, Entity player, boolean par3, double range)
	{
		 float f = 1.0F;
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f;
        if (!world.isRemote && player instanceof EntityPlayer)
            d1 += 1.62D;
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = range;
        /*if (player instanceof EntityPlayerMP)
        {
        	d3 = ((EntityPlayerMP) player).theItemInWorldManager.getBlockReachDistance();
        }*/
        Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
        return world.func_147447_a(vec3, vec31, par3, !par3, par3);
	}
	/**
	 * Hmm...I wonder what this method does? Indeed! It *does* spawn potatoes!
	 *
	 * @param player
	 *            - the player to send the message
	 * @param message
	 *            - the message to send
	 */
	public static void sendMessage(EntityPlayer player, String message)
	{
		IChatComponent chat = new ChatComponentText(message);

		if (!player.worldObj.isRemote)
			player.addChatMessage(chat);
	}
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
	public static void sendToPlayers( Packet packet,  World world,
			 int x,  int y,  int z, Integer maxDistance)
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
				 Object player = iterator.next();
				 EntityPlayerMP playerMP = (EntityPlayerMP) player;

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
	@SuppressWarnings("unused")
	private boolean isSC2Developer(String username)
	{
		return (username.equals("Surseance") || (username.equals("decebaldecebal") || (username.equals("warlordjones"))));
	}
	
    @SuppressWarnings("all")
    public static void sendChatToServer(String message)
    {
    	List<EntityPlayerMP> players = MinecraftServer.getServer().worldServers[0].playerEntities;
    	for (int t = 0; t < players.size(); t++)
    	{
    		players.get(t).addChatMessage(new ChatComponentText(message));
    	}
    }
}
