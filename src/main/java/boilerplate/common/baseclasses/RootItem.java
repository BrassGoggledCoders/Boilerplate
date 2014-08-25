/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.common.baseclasses;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import boilerplate.client.ClientHelper;
import boilerplate.common.utils.StringUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 *
 */
public class RootItem extends Item
{
	@SuppressWarnings("all")
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4)
	{
		if(!StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc").contains("item."))
		{
			if(ClientHelper.isShiftKeyDown())
			{
				this.getWrappedDesc(list);
			}
			else
				list.add(ClientHelper.shiftForInfo);
		}
	}

	@SuppressWarnings("all")
	public void getWrappedDesc(List list)
	{
		String[] wrappedDesc = StringUtils.wrap(StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc"), 35);
		for(String element : wrappedDesc)
			list.add(element.trim());
	}
}
