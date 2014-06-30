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

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class Boilerplate.
 */
@Mod(modid = "boilerplate", name = "Boilerplate", version = "1.0.2")
public class Boilerplate
{
	public Block testBlock;
	public Item  testItem;

	@SidedProxy(clientSide = "boilerplate.client.ClientProxy", serverSide = "boilerplate.common.CommonProxy")
	public static CommonProxy proxy;

	@Instance
	public static Boilerplate instance;
	/**
	 * Pre init.
	 *
	 * @param event the event
	 */
	@Mod.EventHandler
	public void preInit(final FMLPreInitializationEvent event)
	{
		/**
		 * Derpy Recipe testing RecipeUtils util = new RecipeUtils();
		 * util.addMetalRecipes(Blocks.bedrock,Items.arrow, Items.boat);
		 * //util.addArmorSet(new ItemStack(Items.cookie), new ItemStack[]{new
		 * ItemStack(Items.bed), new ItemStack(Items.apple), new
		 * ItemStack(Items.bone), new ItemStack(Items.book), new
		 * ItemStack(Items.blaze_rod)}); //util.addToolSet(new
		 * ItemStack(Items.baked_potato), new ItemStack[]{new
		 * ItemStack(Items.bed), new ItemStack(Items.apple), new
		 * ItemStack(Items.bone), new ItemStack(Items.book), new
		 * ItemStack(Items.blaze_rod)});
		 */
		//testItem = new ItemTest();
		//testBlock = new BlockTest(Material.web);
		//GameRegistry.registerBlock(testBlock, "TestBlock");
		//GameRegistry.registerItem(testItem, "TestItem");
	}
	@Mod.EventHandler
	public void serverStart(FMLServerStartingEvent event)
    {

    }
}
