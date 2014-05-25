/**
 * This class was created by <Surseance> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ 23-May-2014
 */
package boilerplate.common;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import boilerplate.common.utils.recipe.RecipeUtils;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "boilerplate", name = "Boilerplate", version = "1.0.0")
public class Boilerplate
{
	@Mod.EventHandler
	public void preInit(final FMLPreInitializationEvent event)
	{
		RecipeUtils util = new RecipeUtils();
		//util.addArmorSet(new ItemStack(Items.cookie), new ItemStack[]{new ItemStack(Items.bed), new ItemStack(Items.apple), new ItemStack(Items.bone), new ItemStack(Items.book), new ItemStack(Items.blaze_rod)});
		//util.addToolSet(new ItemStack(Items.baked_potato), new ItemStack[]{new ItemStack(Items.bed), new ItemStack(Items.apple), new ItemStack(Items.bone), new ItemStack(Items.book), new ItemStack(Items.blaze_rod)});
	}
}
