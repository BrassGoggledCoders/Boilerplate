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
package boilerplate.common.baseclasses.items.tools;

/**
 * @author Surseance
 *
 */
public class BasePickaxe extends BaseTool
{
	public BasePickaxe(ToolMaterial mat, String prefix)
	{
		super(1.0F, mat, prefix);
		this.setHarvestLevel("pickaxe", mat.getHarvestLevel());
	}
}