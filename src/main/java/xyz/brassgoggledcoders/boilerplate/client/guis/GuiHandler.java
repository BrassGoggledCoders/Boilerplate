package xyz.brassgoggledcoders.boilerplate.client.guis;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.utils.ItemStackUtils;

/**
 * @author Surseance
 *
 */
public class GuiHandler implements IGuiHandler
{

	public GuiHandler(IBoilerplateMod instance)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, this);
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
		IOpenableGUI openableGUI = null;
		Entity entity = world.getEntityByID(id);
		if(entity instanceof IOpenableGUI) {
			openableGUI = (IOpenableGUI)entity;
		} else {
			TileEntity tileEntity = world.getTileEntity(blockPos);
			if(tileEntity instanceof IOpenableGUI) {
				openableGUI = (IOpenableGUI)tileEntity;
			} else {
				for(ItemStack itemStack: player.getHeldEquipment()) {
					if(ItemStackUtils.isItemInstanceOf(itemStack, IOpenableGUI.class)) {
						openableGUI = (IOpenableGUI)itemStack.getItem();
					}
				}
			}
		}

		return openableGUI;
	}
}
