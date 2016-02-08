
package boilerplate.common.baseclasses.items.tools;

/**
 * @author Surseance
 *
 */
public class BaseAxe extends BaseTool
{

	public BaseAxe(ToolMaterial mat)
	{
		super(2.0F, mat);
		this.setHarvestLevel("axe", mat.getHarvestLevel());
	}
}
