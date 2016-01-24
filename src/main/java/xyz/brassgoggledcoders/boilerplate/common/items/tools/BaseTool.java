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
package xyz.brassgoggledcoders.boilerplate.common.items.tools;

import xyz.brassgoggledcoders.boilerplate.common.items.RootItem;
import xyz.brassgoggledcoders.boilerplate.common.utils.ItemStackUtils;
import xyz.brassgoggledcoders.boilerplate.common.utils.helpers.MaterialHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * @author Surseance
 *
 */
public abstract class BaseTool extends RootItem
{
	//TODO: Rendering stuff
	String prefix;

	public static final int steamForRepair = 20;
	public float efficiencyOnProperMaterial = 4.0F;
	public float damageVsEntity;
	protected ToolMaterial toolMaterial;

	protected BaseTool(float damage, ToolMaterial toolMat, String prefix)
	{
		super();
		this.prefix = prefix;
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
	public float getDigSpeed(ItemStack stack, IBlockState state)
	{
		Block block = state.getBlock();
		if (block.getHarvestLevel(state) <= this.toolMaterial.getHarvestLevel())
		{
			for (String elem : this.getToolClasses(stack))
			{
				if (block.isToolEffective(elem, state))
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
		return this.getDigSpeed(stack, block.getBlockState().getBaseState()) > 1.0f;
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
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, BlockPos pos, EntityLivingBase player)
	{
		stack.damageItem(1, player);
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

	/* TODO: What this do
	@SuppressWarnings("all")
	@Override
	public Multimap getAttributeModifiers(ItemStack stack)
	{
		Multimap multimap = super.getAttributeModifiers(stack);
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
				new AttributeModifier(field_111210_e, "Tool modifier", this.damageVsEntity, 0));
		return multimap;
	}*/
}
