
package boilerplate.common.utils.helpers;

import net.minecraft.block.material.Material;

public class MaterialHelper
{
	// Block Materials
	private static Material[] pickaxeMaterials = { Material.rock, Material.iron, Material.anvil, Material.circuits, Material.glass, Material.ice,
			Material.piston };
	private static Material[] axeMaterials = { Material.wood, Material.leaves, Material.plants, Material.vine, Material.circuits, Material.cactus,
			Material.gourd };
	private static Material[] shovelMaterials = { Material.grass, Material.ground, Material.clay, Material.sand, Material.snow };
	private static Material[] swordMaterials = { Material.web };

	public static Material[] getMaterialForTool(String tool)
	{
		if (tool == "pickaxe")
		{
			return pickaxeMaterials;
		}
		if (tool == "axe")
		{
			return axeMaterials;
		}
		if (tool == "shovel")
		{
			return shovelMaterials;
		}
		if (tool == "sword")
		{
			return swordMaterials;
		}
		return null;
	}
}
