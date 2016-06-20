package xyz.brassgoggledcoders.boilerplate;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import xyz.brassgoggledcoders.boilerplate.blocks.BlockBase;
import xyz.brassgoggledcoders.boilerplate.config.ConfigEntry;
import xyz.brassgoggledcoders.boilerplate.config.Type;
import xyz.brassgoggledcoders.boilerplate.items.ItemBase;
import xyz.brassgoggledcoders.boilerplate.utils.ItemStackUtils;

/**
 * TODO:
 * - Tab only appears when needed
 * - Automatic recipe registration
 * - Automatic generation registration
 * - Centralised jsons.
 */
public class OreRequestRegistry
{
	public static OreRequestRegistry instance = new OreRequestRegistry();
	ArrayList<String[]> requested = new ArrayList<String[]>();
	ArrayList<ItemStack> created = new ArrayList<ItemStack>();

	public void requestOre(String[] oreEntry)
	{
		this.requested.add(oreEntry);
	}

	public void registerAllRequests()
	{
		for(String[] oreEntry : requested)
		{
			String ore = oreEntry[0];
			String[] types = Arrays.copyOfRange(oreEntry, 1, oreEntry.length);

			Boilerplate.instance.getRegistryHolder().getConfigRegistry().addEntry("types",
					new ConfigEntry("types", oreEntry[0], Type.BOOLEAN, "false"), "ores");

			for(String type : types)
				createRequest(type, ore);
		}
	}

	private void createRequest(String type, String ore)
	{
		if(Boilerplate.instance.getRegistryHolder().getConfigRegistry().getBoolean(ore, false))
			return;

		if(type == "ore")
		{
			Block oreBlock = new BlockBase(Material.ROCK, toSnakeCase(ore, type));
			Boilerplate.instance.getRegistryHolder().getBlockRegistry().registerBlock(oreBlock, toSnakeCase(ore, type));
			this.created.add(new ItemStack(oreBlock));
		}
		else if(type == "block")
		{
			Block metalBlock = new BlockBase(Material.IRON, toSnakeCase(ore, type));
			Boilerplate.instance.getRegistryHolder().getBlockRegistry().registerBlock(metalBlock,
					toSnakeCase(ore, type));
			this.created.add(new ItemStack(metalBlock));
		}
		else
		{
			Item metalItem = new ItemBase(toSnakeCase(ore, type));
			Boilerplate.instance.getRegistryHolder().getItemRegistry().registerItem(metalItem, toSnakeCase(ore, type));
			this.created.add(new ItemStack(metalItem));
		}
	}

	public void addRequestsToOredict()
	{
		for(ItemStack stack : created)
		{
			if(ItemStackUtils.isItemNonNull(stack))
				OreDictionary.registerOre(stack.getUnlocalizedName(), stack);
		}
	}

	private String toSnakeCase(String ore, String type)
	{
		return ore.toLowerCase() + "_" + type.toLowerCase();
	}
}
