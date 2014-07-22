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

public class RootItem extends Item
{
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4)
    {
    	if(!StatCollector.translateToLocal(getUnlocalizedName() + ".desc").contains("item."))
    	{
    		if(ClientHelper.isShiftKeyDown())
    		{
    			getWrappedDesc(list);
    		}
    		else
    		list.add(ClientHelper.shiftForInfo);
    	}
    }
    public void getWrappedDesc(List list)
    {
		String[] wrappedDesc = StringUtils.wrap(StatCollector.translateToLocal(getUnlocalizedName() + ".desc"), 35);
		for(int i = 0; i<wrappedDesc.length; i++)
			list.add(wrappedDesc[i].trim());
    }
}
