package boilerplate.steamapi.vanity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;

public interface IVanityItem
{
	public ModelBase getVanityItemModel();
	public ResourceLocation getVanityTextureLocation();
	public EnumVanityType getVanityItemType();
}
