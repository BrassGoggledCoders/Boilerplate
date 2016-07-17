package xyz.brassgoggledcoders.boilerplate.client.renderers.custom;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.IModelCustomData;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.vecmath.Matrix4f;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@SideOnly(Side.CLIENT)
public class ItemRenderer implements IModelCustomData {
	private final IItemRenderingHandler renderer;

	public ItemRenderer(IItemRenderingHandler renderer) {
		this.renderer = renderer;
	}

	@Override
	public Collection<ResourceLocation> getDependencies() {
		return Collections.emptyList();
	}

	@Override
	public Collection<ResourceLocation> getTextures() {
		return Collections.emptyList();
	}

	@Override
	public BakedSuperModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
		return new BakedSuperModel(format, renderer, bakedTextureGetter);
	}

	@Override
	public IModelState getDefaultState() {
		return TRSRTransformation.identity();
	}

	@Override
	public ItemRenderer process(ImmutableMap<String, String> customData) {
		return this;
	}

	public static class BakedSuperModel implements IBakedModel {
		private final VertexFormat format;
		private final IItemRenderingHandler renderer;
		private final Function<ResourceLocation, TextureAtlasSprite> textures;

		private BakedSuperModel(VertexFormat format, IItemRenderingHandler renderer, Function<ResourceLocation, TextureAtlasSprite> textures) {
			this.format = format;
			this.renderer = renderer;
			this.textures = textures;
		}


		@Override
		public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
			return ImmutableList.of();
		}

		@Override
		public boolean isAmbientOcclusion() {
			return false;
		}

		@Override
		public boolean isGui3d() {
			return true;
		}

		@Override
		public boolean isBuiltInRenderer() {
			return false;
		}

		@Override
		public TextureAtlasSprite getParticleTexture() {
			return null;
		}

		@Override
		public ItemCameraTransforms getItemCameraTransforms() {
			return ItemCameraTransforms.DEFAULT;
		}

		@Override
		public ItemOverride getOverrides() {
			return new ItemOverride<>(format, renderer, textures);
		}
	}

	public static class ItemOverride<I extends Item> extends ItemOverrideList {
		private final VertexFormat format;
		private final IItemRenderingHandler renderer;
		private final Function<ResourceLocation, TextureAtlasSprite> textures;

		private ItemOverride(VertexFormat format, IItemRenderingHandler renderer, Function<ResourceLocation, TextureAtlasSprite> textures) {
			super((ImmutableList.of()));
			this.format = format;
			this.renderer = renderer;
			this.textures = textures;
		}

		@Override
		public BakedItemModel<I> handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity) {
			return new BakedItemModel<>(format, textures, world, stack, entity, renderer);
		}
	}

	public static class BakedItemModel<I extends Item> implements IBakedModel, IPerspectiveAwareModel {
		private final VertexFormat format;
		private final Function<ResourceLocation, TextureAtlasSprite> textures;
		private final IItemRenderingHandler renderer;
		private final ItemStack stack;
		private final World world;
		private final EntityLivingBase entity;
		private ItemCameraTransforms.TransformType transformType;

		private BakedItemModel(VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> textures, World world, ItemStack stack,
				EntityLivingBase entity, IItemRenderingHandler renderer) {
			this.format = format;
			this.textures = textures;
			this.world = world;
			this.stack = stack;
			this.entity = entity;
			this.renderer = renderer;
			this.transformType = ItemCameraTransforms.TransformType.NONE;
		}

		private BakedItemModel setTransformType(ItemCameraTransforms.TransformType type) {
			this.transformType = type;
			return this;
		}

		@Override
		public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
			List<BakedQuad> list = ImmutableList.of();
			if(side == null) {
				Tessellator tessellator = Tessellator.getInstance();
				tessellator.draw();
				this.renderer.render(world, stack.getItem(), stack, transformType);
				VertexBuffer vertexbuffer = tessellator.getBuffer();
				vertexbuffer.begin(7, DefaultVertexFormats.ITEM);
			}
			return list;
		}

		@Override
		public boolean isAmbientOcclusion() {
			return false;
		}

		@Override
		public boolean isGui3d() {
			return true;
		}

		@Override
		public boolean isBuiltInRenderer() {
			return false;
		}

		@Override
		public TextureAtlasSprite getParticleTexture() {
			return null;
		}

		@Override
		public ItemCameraTransforms getItemCameraTransforms() {
			return ItemCameraTransforms.DEFAULT;
		}

		@Override
		public ItemOverrideList getOverrides() {
			return null;
		}

		@Override
		public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
			return new ImmutablePair<>(this.setTransformType(cameraTransformType), null);
		}
	}
}
