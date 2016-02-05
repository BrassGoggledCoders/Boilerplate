
package boilerplate.common.baseclasses.items.tools;

/**
 * @author Surseance
 *
 */
public class BaseShovel extends BaseTool
{

	public BaseShovel(ToolMaterial mat, String prefix)
	{
		super(1.0F, mat, prefix);
		this.setHarvestLevel("shovel", mat.getHarvestLevel());
	}
}