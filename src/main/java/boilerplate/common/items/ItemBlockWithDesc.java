/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.common.items;

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

	public ItemBlockWithDesc(Block block)
	{
		super(block);
		this.block = block;
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if (stack.getItemDamage() > 0)
		{
			if (!StatCollector.translateToLocal(this.block.getUnlocalizedName() + "." + stack.getItemDamage() + ".desc").contains("tile."))
			{
				if (ClientHelper.isShiftKeyDown())
				{
					this.getWrappedDesc(list, stack);
				}
				else
				{
					list.add(ClientHelper.shiftForInfo);
				}
			}
		}
		else
		{
			if (!StatCollector.translateToLocal(this.block.getUnlocalizedName() + ".desc").contains("tile."))
			{
				if (ClientHelper.isShiftKeyDown())
				{
					this.getWrappedDesc(list, stack);
				}
				else
				{
					list.add(ClientHelper.shiftForInfo);
				}
			}
		}
	}

	public void getWrappedDesc(List<String> list, ItemStack stack)
	{
		String[] wrappedDesc;
		if (stack.getItemDamage() > 0)
		{
			wrappedDesc = StringUtils.wrap(StatCollector.translateToLocal(this.getUnlocalizedName() + "." + stack.getItemDamage() + ".desc"), 40);
		}
		else
		{
			wrappedDesc = StringUtils.wrap(StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc"), 40);
		}
		for (String element : wrappedDesc)
		{
			list.add(element.trim());
		}
	}
}
