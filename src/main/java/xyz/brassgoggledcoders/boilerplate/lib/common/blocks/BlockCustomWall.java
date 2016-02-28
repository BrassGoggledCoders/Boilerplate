
package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;

import java.util.List;

/**
 * @author warlordjones
 *
 */
public class BlockCustomWall extends BlockWall
{
	Block block;
	// int metadata;

	public BlockCustomWall(Block block/* , int meta */)
	{
		super(block);
		this.block = block;
		// this.metadata = meta;
		this.setCreativeTab(BoilerplateLib.getInstance().getMod().getCreativeTab());
	}

	// @Override
	// @SideOnly(Side.CLIENT)
	// public IIcon getIcon(int side, int meta)
	// {
	// return this.block.getIcon(0, this.metadata);
	// }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(item, 1, 0));
	}
}
