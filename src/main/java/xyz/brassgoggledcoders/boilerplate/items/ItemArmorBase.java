package xyz.brassgoggledcoders.boilerplate.items;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.IModAware;
import xyz.brassgoggledcoders.boilerplate.client.ClientHelper;
import xyz.brassgoggledcoders.boilerplate.utils.StringUtils;

/**
 * @author Surseance
 */
public class ItemArmorBase extends ItemArmor implements IModAware {
	String textureName;
	IBoilerplateMod mod;

	boolean creativeTabSet = false;

	public ItemArmorBase(ArmorMaterial mat, EntityEquipmentSlot slot, String name, String textureName) {
		super(mat, 0, slot);
		this.setMaxStackSize(1);
		this.setUnlocalizedName(name);
		this.textureName = textureName;
	}

	@Override
	@Nonnull
	public Item setCreativeTab(@Nonnull CreativeTabs tab) {
		if(!creativeTabSet) {
			super.setCreativeTab(tab);
			this.creativeTabSet = true;
		}
		return this;
	}

	@Override
	@Nonnull
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, EntityEquipmentSlot slot, String stuff) {
		// TODO: Figure this out for sure. SAR will use it for sure.
		return slot.ordinal() == 2 ? mod.getPrefix() + "textures/models/armor/" + this.textureName + "_2.png"
				: mod.getPrefix() + "textures/models/armor/" + this.textureName + "_1.png";
	}

	@SuppressWarnings("all")
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack parO1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
		if(!I18n.format(this.getUnlocalizedName() + ".desc").contains("item."))
			if(ClientHelper.isShiftKeyDown())
				this.getWrappedDesc(list);
			else
				list.add(ClientHelper.shiftForInfo);
	}

	@SuppressWarnings("all")
	public void getWrappedDesc(List list) {
		String[] wrappedDesc = StringUtils.wrap(I18n.format(this.getUnlocalizedName() + ".desc"), 30);
		for(String element : wrappedDesc)
			list.add(element.trim());
	}

	@Override
	public IBoilerplateMod getMod() {
		return mod;
	}

	@Override
	public void setMod(IBoilerplateMod mod) {
		this.mod = mod;
		this.setCreativeTab(mod.getCreativeTab());
	}
}
