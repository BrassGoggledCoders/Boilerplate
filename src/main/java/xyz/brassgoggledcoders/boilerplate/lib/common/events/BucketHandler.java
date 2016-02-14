package xyz.brassgoggledcoders.boilerplate.lib.common.events;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class BucketHandler
{
	private static BucketHandler INSTANCE = new BucketHandler();
	public Map<Block, Item> bucketMap = new HashMap<Block, Item>();

	public BucketHandler()
	{
	}

	public static BucketHandler getInstance()
	{
		return INSTANCE;
	}

	@SubscribeEvent
	public void onBucketFill(FillBucketEvent event)
	{
		ItemStack result = this.fillModBucket(event.world, event.target);

		if (result == null)
			return;

		event.result = result;
		event.setResult(Event.Result.ALLOW);
	}

	private ItemStack fillModBucket(World world, MovingObjectPosition pos)
	{
		Block block = world.getBlockState(pos.getBlockPos()).getBlock();
		Item bucket = this.bucketMap.get(block);

		if ((bucket != null) && (Block.getStateId(world.getBlockState(pos.getBlockPos())) == 0))
		{
			world.setBlockToAir(pos.getBlockPos());
			return new ItemStack(bucket);
		}
		else
			return null;
	}
}