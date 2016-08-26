package xyz.brassgoggledcoders.boilerplate.tileentities;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.utils.Utils;

/**
 * @author SkySom
 */
public abstract class TileEntityBase extends TileEntity {
	protected IBoilerplateMod mod;

	public TileEntityBase() {
		this.mod = Utils.getCurrentMod();
	}

	@Deprecated
	public abstract void readFromNBTCustom(NBTTagCompound nbtTagCompound);

	@Deprecated
	public abstract NBTTagCompound writeToNBTCustom(NBTTagCompound nbtTagCompound);

	/*
	 * Everything below this line is #StolenByTheBrassGoggledCoders from ZeroCore. Temporarily!
	 * TODO
	 */

	public enum SyncReason {
		FullSync, // full sync from storage
		NetworkUpdate // update from the other side
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		this.readFromNBTCustom(data);
		this.syncDataFrom(data, SyncReason.FullSync);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound data) {
		data = this.writeToNBTCustom(data);
		this.syncDataTo(super.writeToNBT(data), SyncReason.FullSync);
		return data;
	}

	@Override
	public void handleUpdateTag(NBTTagCompound data) {

		super.readFromNBT(data);
		this.syncDataFrom(data, SyncReason.NetworkUpdate);
	}

	@Override
	public NBTTagCompound getUpdateTag() {

		NBTTagCompound data = super.getUpdateTag();

		this.syncDataTo(data, SyncReason.NetworkUpdate);
		return data;
	}

	@Override
	public final void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		this.syncDataFrom(packet.getNbtCompound(), SyncReason.NetworkUpdate);
	}

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {

		NBTTagCompound data = new NBTTagCompound();

		this.syncDataTo(data, SyncReason.NetworkUpdate);
		return new SPacketUpdateTileEntity(this.getPos(), 0, data);
	}

	/**
	 * Sync tile entity data from the given NBT compound
	 * 
	 * @param data the data
	 * @param syncReason the reason why the synchronization is necessary
	 */
	protected /* abstract */ void syncDataFrom(NBTTagCompound data, SyncReason syncReason) {

	}

	/**
	 * Sync tile entity data to the given NBT compound
	 * 
	 * @param data the data
	 * @param syncReason the reason why the synchronization is necessary
	 */
	protected /* abstract */ void syncDataTo(NBTTagCompound data, SyncReason syncReason) {

	}

}
