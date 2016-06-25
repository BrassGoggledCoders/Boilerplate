package xyz.brassgoggledcoders.boilerplate.modules.materials;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.commons.lang3.ArrayUtils;
import xyz.brassgoggledcoders.boilerplate.Boilerplate;
import xyz.brassgoggledcoders.boilerplate.Boilerplate.TabOres;
import xyz.brassgoggledcoders.boilerplate.blocks.material.BlockMetal;
import xyz.brassgoggledcoders.boilerplate.blocks.material.BlockMetalOre;
import xyz.brassgoggledcoders.boilerplate.items.ItemSubBase;
import xyz.brassgoggledcoders.boilerplate.module.Module;
import xyz.brassgoggledcoders.boilerplate.module.ModuleBase;
import xyz.brassgoggledcoders.boilerplate.module.dependencies.IDependency;
import xyz.brassgoggledcoders.boilerplate.module.dependencies.ModDependency;
import xyz.brassgoggledcoders.boilerplate.recipes.RecipeUtils;

import java.util.Arrays;
import java.util.List;

// TODO Ore Gen. Auto-deactivate if other ores are detected in the dict. Configurable.
@Module(mod = Boilerplate.ID)
public class MaterialsModule extends ModuleBase {
	public static List<String> oreMetals = Arrays.asList(BlockMetalOre.EnumBlockType.names());
	public static List<String> ourMetals = Arrays.asList(BlockMetal.EnumBlockType.names());
	public static List<String> almostAllMetals =
			Arrays.asList(ArrayUtils.addAll(BlockMetalOre.EnumBlockType.names(), new String[] {"iron"}));
	public static List<String> allMetals =
			Arrays.asList(ArrayUtils.addAll(BlockMetalOre.EnumBlockType.names(), new String[] {"iron", "gold"}));

	public static Block metal_ore, metal_block;
	public static Item ingot, nugget;

	@Override
	public List<IDependency> getDependencies(List<IDependency> dependencyList) {
		dependencyList.add(new ModDependency("steamagerevolution"));
		return dependencyList;
	}

	@Override
	public void preInit(FMLPreInitializationEvent fmlPreInitializationEvent) {
		// this.getConfigRegistry().addNewConfigFile("materials");
		// Boilerplate.instance.getRegistryHolder().getConfigRegistry().addCategoryComment("types",
		// "Disable registration of ore/metal block etc of given type?", "materials");
		Boilerplate.tabOres = new TabOres();
		metal_ore = new BlockMetalOre();
		this.getBlockRegistry().registerBlock(metal_ore, "metal_ore");
		metal_block = new BlockMetal();
		this.getBlockRegistry().registerBlock(metal_block, "metal_block");

		ingot = new ItemSubBase("metals/", "ingot", ourMetals);
		getItemRegistry().registerItem(ingot);
		nugget = new ItemSubBase("metals/", "nugget", almostAllMetals);
		getItemRegistry().registerItem(nugget);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(metal_ore, 1, 0), new ItemStack(ingot, 1, 0), 0.5F);
		GameRegistry.addSmelting(new ItemStack(metal_ore, 1, 1), new ItemStack(ingot, 1, 1), 0.5F);

		for(int i = 0; i < BlockMetal.EnumBlockType.values().length; i++)
			RecipeUtils.addMetalRecipes(metal_block, ingot, nugget, i);
	}

	@Override
	public String getName() {
		return "Materials";
	}
}
