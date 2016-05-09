package xyz.brassgoggledcoders.boilerplate.lib.common.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.RayTraceResult;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.client.IBlockOverlayText;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.SideType;
import xyz.brassgoggledcoders.boilerplate.lib.common.registries.ConfigRegistry;

import java.util.Arrays;

/**
 * @author SkySom
 */
public abstract class TileEntitySidedBase extends TileEntityBase implements IBlockOverlayText
{
	private SideType[] sideConfig;

	public TileEntitySidedBase()
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
			this.worldObj.notifyBlockOfStateChange(this.getPos(), worldObj.getBlockState(pos).getBlock());
			return true;
		}
		return false;
	}

	public void sendBlockUpdate()
	{
		if (!worldObj.isRemote)
		{
			this.worldObj.notifyBlockOfStateChange(this.getPos(), worldObj.getBlockState(pos).getBlock());
		}
	}

	@Override
	public String[] getOverlayText(EntityPlayer player, RayTraceResult rayTrace, boolean tool)
	{
		if (tool && ConfigRegistry.getBoolean("colorblind", false))
		{
			SideType facing = sideConfig[rayTrace.sideHit.ordinal()];
			SideType opposite = sideConfig[rayTrace.sideHit.getOpposite().ordinal()];
			return new String[] {
					BoilerplateLib.getProxy().translate("blockSide.facing") + ": " +
							BoilerplateLib.getProxy().translate("sidetype." + facing.name().toLowerCase()),
					BoilerplateLib.getProxy().translate("blockSide.opposite") + ": "
							+ BoilerplateLib.getProxy().translate("sidetype." + opposite.name().toLowerCase()) };
		}
		return null;
	}
}
