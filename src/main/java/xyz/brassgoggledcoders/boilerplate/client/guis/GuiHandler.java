package xyz.brassgoggledcoders.boilerplate.client.guis;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import xyz.brassgoggledcoders.boilerplate.api.IOpenableGUI;
import xyz.brassgoggledcoders.boilerplate.common.IBoilerplateMod;

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
		BlockPos blockPos = new BlockPos(x, y, z);
		IOpenableGUI openableGUI = this.getOpenableGUI(id, player, world, blockPos);
		return openableGUI != null ? openableGUI.getServerGuiElement(id, player, world, blockPos) : null;
	}

	@Override
	public Object getClientGuiElement(final int id, final EntityPlayer player, final World world, final int x, final int y, final int z)
	{
		BlockPos blockPos = new BlockPos(x, y, z);
		IOpenableGUI openableGUI = this.getOpenableGUI(id, player, world, blockPos);
		return openableGUI != null ? openableGUI.getClientGuiElement(id, player, world, blockPos) : null;
	}

	private IOpenableGUI getOpenableGUI(int id, EntityPlayer player, World world, BlockPos blockPos)
	{
		Entity entity = world.getEntityByID(id);
		TileEntity tileEntity = world.getTileEntity(blockPos);
		ItemStack itemStack = player.getHeldItem();
		return entity instanceof IOpenableGUI ? (IOpenableGUI) entity
				: tileEntity instanceof IOpenableGUI ? (IOpenableGUI) tileEntity
						: (itemStack != null) && (itemStack.getItem() instanceof IOpenableGUI) ? (IOpenableGUI) itemStack.getItem() : null;
	}
}
