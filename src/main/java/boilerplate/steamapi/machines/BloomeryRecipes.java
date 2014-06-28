package boilerplate.steamapi.machines;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BloomeryRecipes
{
    private static final BloomeryRecipes smeltingBase = new BloomeryRecipes();
    /** The list of smelting results. */
    private Map<ItemStack, ItemStack> smeltingList = new HashMap<ItemStack, ItemStack>();

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static BloomeryRecipes smelting()
    {
        return smeltingBase;
    }

    private BloomeryRecipes()
    {
        this.addBloomeryBlockRecipe(Blocks.iron_ore, new ItemStack(Items.apple));
    }

    public void addBloomeryBlockRecipe(Block input, ItemStack result)
    {
        this.addBloomeryItemRecipe(Item.getItemFromBlock(input), result);
    }

    public void addBloomeryItemRecipe(Item input, ItemStack result)
    {
        this.addBloomeryItemStackRecipe(new ItemStack(input, 1, 32767), result);
    }

    public void addBloomeryItemStackRecipe(ItemStack input, ItemStack result)
    {
        this.smeltingList.put(input, result);
    }


    /**
     * Returns the smelting result of an item.
     */
    public ItemStack getSmeltingResult(ItemStack p_151395_1_)
    {
        Iterator iterator = this.smeltingList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.func_151397_a(p_151395_1_, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }

    private boolean func_151397_a(ItemStack p_151397_1_, ItemStack p_151397_2_)
    {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

    public Map<ItemStack, ItemStack> getSmeltingList()
    {
        return this.smeltingList;
    }
}