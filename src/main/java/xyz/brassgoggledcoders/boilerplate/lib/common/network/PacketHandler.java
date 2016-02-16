package xyz.brassgoggledcoders.boilerplate.lib.common.network;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler
{
	private SimpleNetworkWrapper networkWrapper;
	private int id = -1;

	public PacketHandler(String modid)
	{
		networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(modid);
	}

	public <REQ extends IMessage, REPLY extends IMessage> void registerPacket(
			Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side)
	{
		networkWrapper.registerMessage(messageHandler, requestMessageType, ++id, side);
	}

	public void sendToAllAround(IMessage message, Entity entity)
	{
		TargetPoint targetPoint = new TargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 64);
		sendToAllAround(message, targetPoint);
	}

	public void sendToAllAround(IMessage message, BlockPos blockPos, int dimensionId)
	{
		TargetPoint targetPoint = new TargetPoint(dimensionId, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 64);
		sendToAllAround(message, targetPoint);
	}

	public void sendToAllAround(IMessage message, TargetPoint targetPoint)
	{
		networkWrapper.sendToAllAround(message, targetPoint);
	}

	public void sendToServer(IMessage message)
	{
		networkWrapper.sendToServer(message);
	}

	public void sendToDimension(IMessage message, int dimensionId)
	{
		networkWrapper.sendToDimension(message, dimensionId);
	}


}
