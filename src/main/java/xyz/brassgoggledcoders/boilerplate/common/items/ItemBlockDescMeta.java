
package xyz.brassgoggledcoders.boilerplate.common.items;

/**
 * @author warlordjones
 *
 */
public class ItemBlockDescMeta // extends ItemBlockWithMetadata
{
	// Block block;
	//
	// public ItemBlockDescMeta(Block block)
	// {
	// super(block, block);
	// this.block = block;
	// this.setHasSubtypes(true);
	// }
	//
	// @Override
	// public String getUnlocalizedName(ItemStack is)
	// {
	// return super.getUnlocalizedName() + "." + is.getItemDamage();
	// }
	//
	// @SuppressWarnings("all")
	// @Override
	// public void addInformation(ItemStack stack, EntityPlayer entityPlayer,
	// List list, boolean bool)
	// {
	// if (stack.getItemDamage() > 0)
	// {
	// if (!StatCollector.translateToLocal(this.block.getUnlocalizedName() + "."
	// + stack.getItemDamage() + ".desc").contains("tile."))
	// {
	// if (ClientHelper.isShiftKeyDown())
	// {
	// this.getWrappedDesc(list, stack);
	// }
	// else
	// {
	// list.add(ClientHelper.shiftForInfo);
	// }
	// }
	// }
	// else
	// {
	// if (!StatCollector.translateToLocal(this.block.getUnlocalizedName() +
	// ".desc").contains("tile."))
	// {
	// if (ClientHelper.isShiftKeyDown())
	// {
	// this.getWrappedDesc(list, stack);
	// }
	// else
	// {
	// list.add(ClientHelper.shiftForInfo);
	// }
	// }
	// }
	// }
	//
	// public void getWrappedDesc(List<String> list, ItemStack stack)
	// {
	// String[] wrappedDesc;
	// if (stack.getItemDamage() > 0)
	// {
	// wrappedDesc =
	// StringUtils.wrap(StatCollector.translateToLocal(this.getUnlocalizedName()
	// + "." + stack.getItemDamage() + ".desc"), 35);
	// }
	// else
	// {
	// wrappedDesc =
	// StringUtils.wrap(StatCollector.translateToLocal(this.getUnlocalizedName()
	// + ".desc"), 35);
	// }
	// for (String element : wrappedDesc)
	// {
	// list.add(element.trim());
	// }
	// }
}
