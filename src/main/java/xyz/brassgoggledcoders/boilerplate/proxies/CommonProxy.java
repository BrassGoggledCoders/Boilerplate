package xyz.brassgoggledcoders.boilerplate.proxies;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;

public class CommonProxy
{
	protected IBoilerplateMod mod;

	public String translate(String text)
	{
		return "";
	}

	public void loadItemModel(Item item, int metadata, ResourceLocation location)
	{
	}

	public void addVariantName(Item item, String... variantNames)
	{
	}

	public void registerItemModelVariant(Item item, int metadata, String itemModelName)
	{
	}

	public void registerISpecialRendererItem(Item item)
	{
	}

	public void registerEvents()
	{
	}

	public void setLexiconStack(ItemStack stack)
	{	
	}

	public void setMod(IBoilerplateMod mod)
	{
		this.mod = mod;
	}
}
