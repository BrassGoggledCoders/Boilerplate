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

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import boilerplate.client.ClientHelper;
import boilerplate.common.utils.StringUtils;


/**
 * @author warlordjones
 *
 */
public class ItemBlockWithDesc extends ItemBlock
{
	Block block;
	boolean descNeedsShift = true;

	public ItemBlockWithDesc(Block block)
	{
		super(block);
		this.block = block;
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if(!StatCollector.translateToLocal(this.block.getUnlocalizedName() + ".desc").contains("tile."))
			if(this.descNeedsShift)
			{
				if(ClientHelper.isShiftKeyDown())
					this.getWrappedDesc(list);
				else
					list.add(ClientHelper.shiftForInfo);
			}
			else
				this.getWrappedDesc(list);
	}

	public void getWrappedDesc(List<String> list)
	{
		String[] wrappedDesc = StringUtils.wrap(StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc"), 35);
		for(String element : wrappedDesc)
			list.add(element.trim());
	}
}
