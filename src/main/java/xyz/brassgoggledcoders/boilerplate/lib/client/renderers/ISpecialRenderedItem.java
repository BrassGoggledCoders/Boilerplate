package xyz.brassgoggledcoders.boilerplate.lib.client.renderers;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.IHasModel;

public interface ISpecialRenderedItem extends IHasModel {
	String getSpecialRendererPath();
}