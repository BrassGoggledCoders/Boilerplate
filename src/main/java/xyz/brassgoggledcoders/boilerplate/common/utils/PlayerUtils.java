package xyz.brassgoggledcoders.boilerplate.common.utils;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class PlayerUtils
{
	// DOES NOT get entities
	public static MovingObjectPosition getTargetBlock(World world, Entity player, boolean par3, double range)
	{
		float f = 1.0F;
		float f1 = player.prevRotationPitch + ((player.rotationPitch - player.prevRotationPitch) * f);
		float f2 = player.prevRotationYaw + ((player.rotationYaw - player.prevRotationYaw) * f);
		double d0 = player.prevPosX + ((player.posX - player.prevPosX) * f);
		double d1 = player.prevPosY + ((player.posY - player.prevPosY) * f);
		if (!world.isRemote && (player instanceof EntityPlayer))
		{
			d1 += 1.62D;
		}
		double d2 = player.prevPosZ + ((player.posZ - player.prevPosZ) * f);
		Vec3 vec3 = new Vec3(d0, d1, d2);
		float f3 = MathHelper.cos((-f2 * 0.017453292F) - (float) Math.PI);
		float f4 = MathHelper.sin((-f2 * 0.017453292F) - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = range;
		Vec3 vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);
		return world.rayTraceBlocks(vec3, vec31, par3, !par3, par3);
	}

	public static Entity getPointedEntity(World world, Entity entityplayer, double range)
	{
		Entity pointedEntity = null;
		double d = range;
		Vec3 vec3d = new Vec3(entityplayer.posX, entityplayer.posY + entityplayer.getEyeHeight(), entityplayer.posZ);

		Vec3 vec3d1 = entityplayer.getLookVec();
		Vec3 vec3d2 = vec3d.addVector(vec3d1.xCoord * d, vec3d1.yCoord * d, vec3d1.zCoord * d);

		float f1 = 1.1F;
		List list = world.getEntitiesWithinAABBExcludingEntity(entityplayer,
				entityplayer.getEntityBoundingBox().addCoord(vec3d1.xCoord * d, vec3d1.yCoord * d, vec3d1.zCoord * d).expand(f1, f1, f1));

		double d2 = 0.0D;
		for (int i = 0; i < list.size(); i++)
		{
			Entity entity = (Entity) list.get(i);
			if (((entity.canBeCollidedWith())
					&& (world.rayTraceBlocks(new Vec3(entityplayer.posX, entityplayer.posY + entityplayer.getEyeHeight(), entityplayer.posZ),
							new Vec3(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ), false, true, false) == null)))
			{
				float f2 = Math.max(0.8F, entity.getCollisionBorderSize());
				AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand(f2, f2, f2);
				MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3d, vec3d2);

				if (axisalignedbb.isVecInside(vec3d))
				{
					if ((0.0D < d2) || (d2 == 0.0D))
					{
						pointedEntity = entity;
						d2 = 0.0D;
					}

				}
				else if (movingobjectposition != null)
				{
					double d3 = vec3d.distanceTo(movingobjectposition.hitVec);
					if ((d3 < d2) || (d2 == 0.0D))
					{
						pointedEntity = entity;
						d2 = d3;
					}
				}
			}
		}
		return pointedEntity;
	}

	public static void sendMessage(EntityPlayer player, String message)
	{
		IChatComponent chat = new ChatComponentText(message);

		if (!player.worldObj.isRemote)
		{
			player.addChatMessage(chat);
		}
	}

	public static void sendToPlayers(Packet packet, World world, int x, int y, int z, Integer maxDistance)
	{
		if (maxDistance == null)
		{
			maxDistance = 128;
		}

		Iterator<?> iterator;

		if (packet != null)
		{
			for (iterator = world.playerEntities.iterator(); iterator.hasNext();)
			{
				Object player = iterator.next();
				EntityPlayerMP playerMP = (EntityPlayerMP) player;

				if ((Math.abs(playerMP.posX - x) <= maxDistance) && (Math.abs(playerMP.posY - y) <= maxDistance)
						&& (Math.abs(playerMP.posZ - z) <= maxDistance))
				{
					playerMP.playerNetServerHandler.sendPacket(packet);
				}
			}
		}
	}

	/*
	 * TODO: Chat stuff
	 * 
	 * @SuppressWarnings("all") public static void sendChatToServer(String
	 * message) { List<EntityPlayerMP> players =
	 * MinecraftServer.getServer().worldServers[0].playerEntities; for (int t =
	 * 0; t < players.size(); t++) { players.get(t).addChatMessage(new
	 * ChatComponentText(message)); } }
	 */
}
