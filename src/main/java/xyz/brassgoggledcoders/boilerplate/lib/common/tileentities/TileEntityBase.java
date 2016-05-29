package xyz.brassgoggledcoders.boilerplate.lib.common.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * @author SkySom
 */
public abstract class TileEntityBase extends TileEntity
{
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound)
	{
		super.readFromNBT(nbtTagCompound);
		this.readFromNBTCustom(nbtTagCompound);
	}

	public abstract void readFromNBTCustom(NBTTagCompound nbtTagCompound);

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound)
	{
		nbtTagCompound = super.writeToNBT(nbtTagCompound);
		nbtTagCompound = this.writeToNBTCustom(nbtTagCompound);
		return nbtTagCompound;
	}

	public abstract NBTTagCompound writeToNBTCustom(NBTTagCompound nbtTagCompound);

	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.writeToNBT(nbttagcompound);
		return new SPacketUpdateTileEntity(this.getPos(), 3, nbttagcompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.getNbtCompound());
	}
}
