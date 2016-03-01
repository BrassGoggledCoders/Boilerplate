package xyz.brassgoggledcoders.boilerplate.lib.client.guis;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public interface IOpenableGUI
{
	Object getClientGuiElement(int ID, EntityPlayer player, World world, BlockPos blockPos);

	Object getServerGuiElement(int ID, EntityPlayer player, World world, BlockPos blockPos);
}