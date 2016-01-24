/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package xyz.brassgoggledcoders.boilerplate.api;

import ic2.api.tile.IWrenchable;
import net.minecraftforge.fml.common.Optional;

/**
 *
 * Helper interface that allows implementing blocks to be wrenchable with mod
 * wrenches. Currently only works on IC2.
 *
 * @author warlordjones
 *
 */
@Optional.Interface(iface = "ic2.api.tile.IWrenchable", modid = "IC2")
public interface IUniversallyWrenchable extends IWrenchable
{
}
