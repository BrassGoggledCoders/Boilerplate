
package boilerplate.common.baseclasses.items.tools;

import boilerplate.common.IBoilerplateMod;

/**
 * @author Surseance
 *
 */
public class BaseAxe extends BaseTool
{

	public BaseAxe(ToolMaterial mat, IBoilerplateMod mod)
	{
		super(2.0F, mat, mod);
		this.setHarvestLevel("axe", mat.getHarvestLevel());
	}
}
