/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.steamapi.vanity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;

/**
 * @author warlordjones
 * 
 */
public interface IVanityItem
{
	public ModelBase getVanityItemModel();

	public ResourceLocation getItemTextureLocation();

	public EnumVanityType getVanityItemType();

	public float getModelOffsetX();

	public float getModelOffsetY();

	public float getModelOffsetZ();
}
