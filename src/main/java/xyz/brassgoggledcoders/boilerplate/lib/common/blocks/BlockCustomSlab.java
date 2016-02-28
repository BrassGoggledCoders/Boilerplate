
package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.ItemStack;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;

/**
 * @author warlordjones
 *
 */
// TODO
public class BlockCustomSlab extends BlockSlab
{
	String type;
	Block block;

	public BlockCustomSlab(String type, Block block, Material mat)
	{
		super(mat);
		this.type = type;
		this.block = block;
		this.setCreativeTab(BoilerplateLib.getMod().getCreativeTab());
		this.useNeighborBrightness = true;
	}

	// @Override
	// public String func_150002_b(int p_150002_1_)
	// {
	// return BoilerplateLib.getInstance.getMod().getPrefix() + this.type;
	// }
	//
	// @Override
	// public IIcon getIcon(int side, int meta)
	// {
	// return this.block.getIcon(side, meta);
	// }

	@Override
	public String getUnlocalizedName(int meta)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDouble()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IProperty<?> getVariantProperty()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getVariant(ItemStack stack)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
