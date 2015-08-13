/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package boilerplate.common.baseclasses;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.client.ClientHelper;
import boilerplate.common.utils.StringUtils;

/**
 * @author Surseance
 *
 */
public class BaseArmor extends ItemArmor
{

	String textureName;
	String prefix;

	public BaseArmor(ArmorMaterial mat, int type, String textureName, String prefix)
	{
		super(mat, 0, type);
		this.setMaxStackSize(1);
		this.textureName = textureName;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type)
	{
		return slot == 2 ? prefix + "textures/models/armor/" + type + "_2.png" : prefix + "textures/models/armor/" + type + "_1.png";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon(prefix + "armor/" + this.getUnlocalizedName().substring(5));
	}

	@SuppressWarnings("all")
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack parO1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4)
	{
		if (!StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc").contains("item."))
		{
			if (ClientHelper.isShiftKeyDown())
				this.getWrappedDesc(list);
			else
				list.add(ClientHelper.shiftForInfo);
		}
	}

	@SuppressWarnings("all")
	public void getWrappedDesc(List list)
	{
		String[] wrappedDesc = StringUtils.wrap(StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc"), 30);
		for (String element : wrappedDesc)
			list.add(element.trim());
	}
}
