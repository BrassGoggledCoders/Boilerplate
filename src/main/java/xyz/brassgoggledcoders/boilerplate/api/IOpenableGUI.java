package xyz.brassgoggledcoders.boilerplate.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Skylar on 8/26/2015.
 */
public interface IOpenableGUI
{
	Object getClientGuiElement(int ID, EntityPlayer player, World world, BlockPos blockPos);

	Object getServerGuiElement(int ID, EntityPlayer player, World world, BlockPos blockPos);
}
