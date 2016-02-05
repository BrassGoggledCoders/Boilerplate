
package boilerplate.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Skylar on 8/26/2015.
 */
public interface IOpenableGUI
{
	Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z);

	Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z);
}
