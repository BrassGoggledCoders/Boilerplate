
package boilerplate.common.baseclasses.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.IBoilerplateMod;
import boilerplate.common.utils.Utils;

/**
 * @author warlordjones
 *
 */
public class BaseMetadataItem extends BaseItem
{
	protected IIcon[] itemIcon;
	String[] subNames;
	String path;
	String type;
	IBoilerplateMod mod;

	public BaseMetadataItem(String[] subNames)
	{
		this("", "", subNames);
	}

	public BaseMetadataItem(String type, String[] subNames)
	{
		this("", type, subNames);
	}

	public BaseMetadataItem(String path, String type, String[] subNames)
	{
		super();
		this.itemIcon = new IIcon[subNames.length];
		this.subNames = subNames;
		this.path = path;
		this.type = type;
		this.setHasSubtypes(true);
		this.mod = Utils.getCurrentMod();
	}

	@Override
	public String getUnlocalizedName(ItemStack is)
	{
		return super.getUnlocalizedName() + "." + is.getItemDamage();
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int var4 = 0; var4 < subNames.length; ++var4)
			list.add(new ItemStack(this, 1, var4));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		for (int i = 0; i < this.itemIcon.length; i++)
		{
			if (subNames[i] != null)
				this.itemIcon[i] = ir.registerIcon(mod.getPrefix() + path + "item" + type + subNames[i]);
			else
				this.itemIcon[i] = ir.registerIcon(mod.getPrefix() + path + "item" + type);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int itemDamage)
	{
		return this.itemIcon[itemDamage];
	}
}