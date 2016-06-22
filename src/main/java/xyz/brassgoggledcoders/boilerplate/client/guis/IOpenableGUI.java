package xyz.brassgoggledcoders.boilerplate.client.guis;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IOpenableGUI {
	Gui getClientGuiElement(int ID, EntityPlayer player, World world, BlockPos blockPos);

	Container getServerGuiElement(int ID, EntityPlayer player, World world, BlockPos blockPos);
}
