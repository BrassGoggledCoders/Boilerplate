package xyz.brassgoggledcoders.boilerplate.multiblock;

import java.util.HashMap;

import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;

public final class MultiblockRegistry implements IMultiblockRegistry {

	/**
	 * Register a new part in the system. The part has been created either through user action or via a chunk loading.
	 * 
	 * @param world The world into which this part is loading.
	 * @param part The part being loaded.
	 */
	@Override
	public void onPartAdded(final World world, final IMultiblockPart part) {

		MultiblockWorldRegistry registry;

		if(this._registries.containsKey(world)) {

			registry = this._registries.get(world);

		}
		else {

			registry = new MultiblockWorldRegistry(world);
			this._registries.put(world, registry);
		}

		registry.onPartAdded(part);
	}

	/**
	 * Call to remove a part from world lists.
	 * 
	 * @param world The world from which a multiblock part is being removed.
	 * @param part The part being removed.
	 */
	@Override
	public void onPartRemovedFromWorld(final World world, final IMultiblockPart part) {

		if(this._registries.containsKey(world))
			this._registries.get(world).onPartRemovedFromWorld(part);
	}

	/**
	 * Call to mark a controller as dead. It should only be marked as dead
	 * when it has no connected parts. It will be removed after the next world tick.
	 * 
	 * @param world The world formerly containing the multiblock
	 * @param controller The dead controller
	 */
	@Override
	public void addDeadController(final World world, final MultiblockControllerBase controller) {

		if(this._registries.containsKey(world))
			this._registries.get(world).addDeadController(controller);
		else
			FMLLog.warning(
					"Controller %d in world %s marked as dead, but that world is not tracked! Controller is being ignored.",
					controller.hashCode(), world);
	}

	/**
	 * Call to mark a controller as dirty. Dirty means that parts have
	 * been added or removed this tick.
	 * 
	 * @param world The world containing the multiblock
	 * @param controller The dirty controller
	 */
	@Override
	public void addDirtyController(final World world, final MultiblockControllerBase controller) {

		if(this._registries.containsKey(world))
			this._registries.get(world).addDirtyController(controller);
		else
			throw new IllegalArgumentException(
					"Adding a dirty controller to a world that has no registered controllers!");
	}

	/*
	 * Private implementation
	 */

	/**
	 * Called before Tile Entities are ticked in the world. Do bookkeeping here.
	 * 
	 * @param world The world being ticked
	 */
	protected void tickStart(final World world) {

		if(this._registries.containsKey(world)) {

			final MultiblockWorldRegistry registry = this._registries.get(world);

			registry.processMultiblockChanges();
			registry.tickStart();
		}
	}

	/**
	 * Called when the world has finished loading a chunk.
	 * 
	 * @param world The world which has finished loading a chunk
	 * @param chunkX The X coordinate of the chunk
	 * @param chunkZ The Z coordinate of the chunk
	 */
	protected void onChunkLoaded(final World world, final int chunkX, final int chunkZ) {

		if(this._registries.containsKey(world))
			this._registries.get(world).onChunkLoaded(chunkX, chunkZ);
	}

	/**
	 * Called whenever a world is unloaded. Unload the relevant registry, if we have one.
	 * 
	 * @param world The world being unloaded.
	 */
	protected void onWorldUnloaded(final World world) {

		if(this._registries.containsKey(world)) {
			this._registries.get(world).onWorldUnloaded();
			this._registries.remove(world);
		}
	}

	private MultiblockRegistry() {
		this._registries = new HashMap<World, MultiblockWorldRegistry>(2);
	}

	private HashMap<World, MultiblockWorldRegistry> _registries;

	public static final MultiblockRegistry INSTANCE = new MultiblockRegistry();
}