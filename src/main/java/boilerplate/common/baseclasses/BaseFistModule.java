package boilerplate.common.baseclasses;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import boilerplate.client.ClientHelper;
import boilerplate.steamapi.item.IFistModule;
import boilerplate.steamapi.item.ModuleRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BaseFistModule extends RootItem implements IFistModule
{
	public BaseFistModule()
	{
		ModuleRegistry.setModuleIncompatibilities(this, new String[] {});
	}

	@SuppressWarnings("all")
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4)
	{
		if (ClientHelper.isShiftKeyDown())
		{
			list.add("Module ID: " + this.getModuleId());
			if (!StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc").contains("item."))
			{
				list.add("Module Effect: ");
				this.getWrappedDesc(list, stack);
			}
			if (this.getEnergyConsumedOnEffect() != 0)
				list.add("Energy Usage on Effect: " + this.getEnergyConsumedOnEffect());
			if (this.getSteamConsumedOnEffect() != 0)
				list.add("Steam Usage on Effect: " + this.getSteamConsumedOnEffect());
		}
		else
			list.add(ClientHelper.shiftForInfo);
	}

	public String getEffectTypeStringFromEnum(EnumModuleEffectType type)
	{
		if (type == EnumModuleEffectType.RIGHTCLICK)
			return "On Right-click";
		else if (type == EnumModuleEffectType.ATTACK)
			return "On Attack";
		else
			return "Error!";
	}
}
