
package boilerplate.common.baseclasses.items.tools;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.baseclasses.items.BaseItem;
import boilerplate.common.utils.ItemStackUtils;
import boilerplate.common.utils.helpers.MaterialHelper;
import com.google.common.collect.Multimap;

/**
 * @author Surseance
 *
 */
public abstract class BaseTool extends BaseItem
{

	public static final int steamForRepair = 20;
	public float efficiencyOnProperMaterial = 4.0F;
	public float damageVsEntity;
	protected ToolMaterial toolMaterial;

	protected BaseTool(float damage, ToolMaterial toolMat)
	{
		super("tools/");
		this.toolMaterial = toolMat;
		this.setMaxStackSize(1);
		this.efficiencyOnProperMaterial = toolMat.getEfficiencyOnProperMaterial();
		this.damageVsEntity = damage + toolMat.getDamageVsEntity();
		this.setFull3D();
		this.setMaxDamage(toolMat.getMaxUses());
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List l)
	{
		l.add(new ItemStack(item, 1));
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta)
	{
		if (block.getHarvestLevel(meta) <= this.toolMaterial.getHarvestLevel())
		{
			for (String elem : this.getToolClasses(stack))
			{
				if (block.isToolEffective(elem, meta))
				{
					return this.efficiencyOnProperMaterial;
				}
				else
				{
					Material mat = block.getMaterial();
					for (Material m : MaterialHelper.getMaterialForTool(elem))
					{
						if (m == mat)
						{
							return this.efficiencyOnProperMaterial;
						}
					}
				}
			}
		}

		return 1.0F;
	}

	@Override
	public boolean canHarvestBlock(Block block, ItemStack stack)
	{
		return this.getDigSpeed(stack, block, 0) > 1.0f;
	}

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase living1, EntityLivingBase living2)
	{
		if (this instanceof BaseSword)
		{
			itemstack.damageItem(1, living2);
		}
		else
		{
			itemstack.damageItem(2, living2);
		}

		return true;
	}

	@Override
	public int getItemEnchantability()
	{
		return this.toolMaterial.getEnchantability();
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_,
			EntityLivingBase living)
	{
		stack.damageItem(1, living);
		return true;
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if (!(this instanceof BaseSword))
		{
			list.add("Efficiency: " + this.toolMaterial.getEfficiencyOnProperMaterial());
		}
	}

	protected void changeToolDamage(ItemStack itemStack, double damage)
	{
		ItemStackUtils.addModifier(itemStack, SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), damage, 0);
	}

	@SuppressWarnings("all")
	@Override
	public Multimap getAttributeModifiers(ItemStack stack)
	{
		Multimap multimap = super.getAttributeModifiers(stack);
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
				new AttributeModifier(field_111210_e, "Tool modifier", this.damageVsEntity, 0));
		return multimap;
	}
}
