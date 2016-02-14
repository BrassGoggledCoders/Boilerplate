package xyz.brassgoggledcoders.boilerplate.lib.common.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.client.IBlockOverlayText;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.SideType;
import xyz.brassgoggledcoders.boilerplate.mod.Boilerplate;

import java.util.Arrays;

/**
 * @author SkySom
 */
public abstract class TileEntitySided extends TileEntityBase implements IBlockOverlayText
{
	private SideType[] sideConfig;

	public TileEntitySided()
	{
		super();
		sideConfig = new SideType[6];
		Arrays.fill(sideConfig, SideType.NONE);
	}

	public void toggleSide(int side)
	{
		int type = sideConfig[side].ordinal();
		type++;
		if (type >= SideType.values().length || type < 0)
		{
			type = 0;
		}
		this.sideConfig[side] = SideType.values()[type];
		worldObj.addBlockEvent(this.getPos(), this.getBlockType(), 0, 0);
	}

	public void setSideConfig(int side, SideType sideType)
	{
		this.sideConfig[side] = sideType;
	}

	public SideType getSideValue(int side)
	{
		return this.sideConfig[side];
	}

	@Override
	public void readFromNBTCustom(NBTTagCompound nbtTagCompound)
	{
		int[] array = nbtTagCompound.getIntArray("sideConfig");
		if (this.sideConfig == null)
		{
			this.sideConfig = new SideType[6];
		}
		if (array != null)
		{
			for (int i = 0; i < array.length; i++)
			{
				this.sideConfig[i] = SideType.values()[array[i]];
			}
		}
	}

	@Override
	public void writeToNBTCustom(NBTTagCompound nbtTagCompound)
	{
		int[] array = new int[6];
		for (int i = 0; i < this.sideConfig.length; i++)
		{
			array[i] = this.sideConfig[i].ordinal();
		}
		nbtTagCompound.setIntArray("sideConfig", array);
	}

	@Override
	public boolean receiveClientEvent(int id, int arg)
	{
		if (id == 0)
		{
			this.worldObj.markBlockForUpdate(this.getPos());
			return true;
		}
		return false;
	}

	public void sendBlockUpdate()
	{
		if (!worldObj.isRemote)
		{
			this.worldObj.markBlockForUpdate(this.getPos());
		}
	}

	@Override
	public String[] getOverlayText(EntityPlayer player, MovingObjectPosition mop, boolean tool)
	{
		if (tool && BoilerplateLib.COLORBLIND)
		{
			SideType facing = sideConfig[mop.sideHit.ordinal()];
			SideType opposite = sideConfig[mop.sideHit.getOpposite().ordinal()];
			return new String[] {
					Boilerplate.proxy.translate("blockSide.facing") + ": " + Boilerplate.proxy.translate("sidetype." + facing.name().toLowerCase()),
					Boilerplate.proxy.translate("blockSide.opposite") + ": "
							+ Boilerplate.proxy.translate("sidetype." + opposite.name().toLowerCase()) };
		}
		return null;
	}
}
