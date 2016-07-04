package xyz.brassgoggledcoders.boilerplate.client.renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import xyz.brassgoggledcoders.boilerplate.utils.ClassLoading;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

public class TESRLoader {
	public Map<String, TileEntitySpecialRenderer> tileEntitySpecialRendererMap;

	public TESRLoader(ASMDataTable asmDataTable) {
		List<TileEntitySpecialRenderer> tileEntitySpecialRenderers = ClassLoading.getInstances(asmDataTable,
				TESpecialRenderer.class, TileEntitySpecialRenderer.class);
		for(TileEntitySpecialRenderer renderer : tileEntitySpecialRenderers) {
			for(Annotation annotation : renderer.getClass().getDeclaredAnnotations()) {
				if(annotation instanceof TESpecialRenderer) {
						tileEntitySpecialRendererMap.put(((TESpecialRenderer) annotation).name(), renderer);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void registerTESRToTile(String tesrName, Class<? extends TileEntity> tileEntityClass) {
		TileEntitySpecialRenderer tileEntitySpecialRenderer = tileEntitySpecialRendererMap.get(tesrName);
		if(tileEntitySpecialRenderer != null) {
			ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, tileEntitySpecialRenderer);
		}
	}
}
