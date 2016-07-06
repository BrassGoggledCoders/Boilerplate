package xyz.brassgoggledcoders.boilerplate.utils;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class VillagerUtils {
	public static void addSellTrade(VillagerRegistry.VillagerCareer career, ItemStack forSale, int minPrice,
			int maxPrice) {
		addSellTrade(1, career, forSale, minPrice, maxPrice);
	}

	public static void addSellTrade(int level, VillagerRegistry.VillagerCareer career, ItemStack forSale, int minPrice,
			int maxPrice) {
		career.addTrade(level,
				new EntityVillager.ListItemForEmeralds(forSale, new EntityVillager.PriceInfo(minPrice, maxPrice)));
	}
}
