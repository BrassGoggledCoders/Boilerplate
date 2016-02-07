
package boilerplate.common.baseclasses.items.tools;

import boilerplate.common.IBoilerplateMod;

/**
 * @author Surseance
 *
 */
public class BasePickaxe extends BaseTool
{
	public BasePickaxe(ToolMaterial mat, IBoilerplateMod mod)
	{
		super(1.0F, mat, mod);
		this.setHarvestLevel("pickaxe", mat.getHarvestLevel());
	}
}