package xyz.brassgoggledcoders.boilerplate.modules.spanner;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import xyz.brassgoggledcoders.boilerplate.items.IHasRecipe;
import xyz.brassgoggledcoders.boilerplate.items.ItemBase;

public class ItemSpanner extends ItemBase implements IHasRecipe {

	public ItemSpanner() {
		super("spanner");
		this.setCreativeTab(CreativeTabs.TOOLS);
		this.setMaxStackSize(1);
	}

	@Override
	public IRecipe[] getRecipes() {
		return new IRecipe[] {new ShapedOreRecipe(this, "I I", "ISI", " S ", 'I', "ingotIron", 'S', "stickWood")};
	}
}
