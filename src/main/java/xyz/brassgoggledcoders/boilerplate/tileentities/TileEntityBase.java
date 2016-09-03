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

	/* Orginally inspired by ZeroCore */

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		this.readFromDisk(data);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound data) {
		this.writeToDisk(super.writeToNBT(data));
		return data;
	}

	protected void readFromDisk(NBTTagCompound data) {
		this.mod.getLogger().devInfo("Read from Disk");
	};

	protected NBTTagCompound writeToDisk(NBTTagCompound data) {
		this.mod.getLogger().devInfo("Written to Disk");
		return data;
	};

	@Override
	public void handleUpdateTag(NBTTagCompound data) {
		super.readFromNBT(data);
		this.readFromUpdatePacket(data);
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound data = super.getUpdateTag();
		this.writeToUpdatePacket(data);
		return data;
	}

	@Override
	public final void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		this.readFromUpdatePacket(packet.getNbtCompound());
	}

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound data = new NBTTagCompound();
		this.writeToUpdatePacket(data);
		return new SPacketUpdateTileEntity(this.getPos(), 0, data);
	}

	protected void readFromUpdatePacket(NBTTagCompound data) {
		this.mod.getLogger().devInfo("Read from Packet");
	};

	protected NBTTagCompound writeToUpdatePacket(NBTTagCompound data) {
		this.mod.getLogger().devInfo("Written to Packet");
		return data;
	};

	public void sendBlockUpdate() {
		if(!worldObj.isRemote)
			this.worldObj.notifyBlockOfStateChange(this.getPos(), worldObj.getBlockState(pos).getBlock());
	}

}
