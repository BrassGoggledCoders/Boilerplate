package boilerplate.common.utils.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

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
		Block block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);
		Item bucket = this.bucketMap.get(block);

		if ((bucket != null) && (world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) == 0))
		{
			world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
			return new ItemStack(bucket);
		}
		else return null;
	}
}