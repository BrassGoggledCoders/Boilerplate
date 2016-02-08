
package boilerplate.common.baseclasses.items.tools;

/**
 * @author Surseance
 *
 */
public class BaseShovel extends BaseTool
{

	public BaseShovel(ToolMaterial mat)
	{
		super(1.0F, mat);
		this.setHarvestLevel("shovel", mat.getHarvestLevel());
	}
}