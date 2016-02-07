
package boilerplate.common.baseclasses.items.tools;

import boilerplate.common.IBoilerplateMod;

/**
 * @author Surseance
 *
 */
public class BaseShovel extends BaseTool
{

	public BaseShovel(ToolMaterial mat, IBoilerplateMod mod)
	{
		super(1.0F, mat, mod);
		this.setHarvestLevel("shovel", mat.getHarvestLevel());
	}
}