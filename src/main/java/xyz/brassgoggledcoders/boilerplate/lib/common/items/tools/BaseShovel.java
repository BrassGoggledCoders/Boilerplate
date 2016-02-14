package xyz.brassgoggledcoders.boilerplate.lib.common.items.tools;

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