
package boilerplate.common.baseclasses.items.tools;

/**
 * @author Surseance
 *
 */
public class BasePickaxe extends BaseTool
{
	public BasePickaxe(ToolMaterial mat)
	{
		super(1.0F, mat);
		this.setHarvestLevel("pickaxe", mat.getHarvestLevel());
	}
}