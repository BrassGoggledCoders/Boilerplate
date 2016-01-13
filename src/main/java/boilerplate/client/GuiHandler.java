/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package boilerplate.client;

import boilerplate.api.IOpenableGUI;
import boilerplate.common.IBoilerplateMod;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Surseance
 *
 */
public class GuiHandler implements IGuiHandler
{
	public GuiHandler(IBoilerplateMod instance)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(instance.getInstance(), this);
	}

	@Override
	public Object getServerGuiElement(final int id, final EntityPlayer player, final World world, final int x, final int y, final int z)
	{
		IOpenableGUI openableGUI = this.getOpenableGUI(id, player, world, x, y, z);
		return openableGUI != null ? openableGUI.getServerGuiElement(id, player, world, x, y, z) : null;
	}

	@Override
	public Object getClientGuiElement(final int id, final EntityPlayer player, final World world, final int x, final int y, final int z)
	{
		IOpenableGUI openableGUI = this.getOpenableGUI(id, player, world, x, y, z);
		return openableGUI != null ? openableGUI.getClientGuiElement(id, player, world, x, y, z) : null;
	}

	private IOpenableGUI getOpenableGUI(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		Entity entity = world.getEntityByID(id);
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		ItemStack itemStack = player.getHeldItem();
		return entity instanceof IOpenableGUI ? (IOpenableGUI)entity : tileEntity instanceof IOpenableGUI ?
				(IOpenableGUI)tileEntity : (itemStack != null) && (itemStack.getItem() instanceof IOpenableGUI) ?
				(IOpenableGUI) itemStack.getItem() : null;
	}
}
