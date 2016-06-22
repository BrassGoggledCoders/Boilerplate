package xyz.brassgoggledcoders.boilerplate.tileentities;

import javax.annotation.Nonnull;

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

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		this.readFromNBTCustom(nbtTagCompound);
	}

	public abstract void readFromNBTCustom(NBTTagCompound nbtTagCompound);

	@Override
	@Nonnull
	public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
		nbtTagCompound = super.writeToNBT(nbtTagCompound);
		nbtTagCompound = this.writeToNBTCustom(nbtTagCompound);
		return nbtTagCompound;
	}

	public abstract NBTTagCompound writeToNBTCustom(NBTTagCompound nbtTagCompound);

	@Override
	@Nonnull
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.writeToNBT(nbttagcompound);
		return new SPacketUpdateTileEntity(this.getPos(), 3, nbttagcompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}
}
