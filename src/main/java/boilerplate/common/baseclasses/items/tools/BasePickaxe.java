
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