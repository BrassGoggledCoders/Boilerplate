package xyz.brassgoggledcoders.boilerplate.lib.common.items.tools;

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
