package xyz.brassgoggledcoders.boilerplate.api;

import net.minecraftforge.fml.common.Optional;

import ic2.api.tile.IWrenchable;

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
