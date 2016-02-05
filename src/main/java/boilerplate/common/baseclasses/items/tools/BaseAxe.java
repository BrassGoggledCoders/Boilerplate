
package boilerplate.common.baseclasses.items.tools;

/**
 * @author Surseance
 *
 */
public class BaseAxe extends BaseTool
{

	public BaseAxe(ToolMaterial mat, String prefix)
	{
		super(2.0F, mat, prefix);
		this.setHarvestLevel("axe", mat.getHarvestLevel());
	}
}
