package xyz.brassgoggledcoders.boilerplate.multiblock;

import java.util.HashMap;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public final class BlockFacings {

	public static final BlockFacings NONE;
	public static final BlockFacings ALL;
	public static final BlockFacings DOWN;
	public static final BlockFacings UP;
	public static final BlockFacings NORTH;
	public static final BlockFacings SOUTH;
	public static final BlockFacings WEST;
	public static final BlockFacings EAST;

	public static final PropertyBool FACING_DOWN = PropertyBool.create("downFacing");
	public static final PropertyBool FACING_UP = PropertyBool.create("upFacing");
	public static final PropertyBool FACING_WEST = PropertyBool.create("westFacing");
	public static final PropertyBool FACING_EAST = PropertyBool.create("eastFacing");
	public static final PropertyBool FACING_NORTH = PropertyBool.create("northFacing");
	public static final PropertyBool FACING_SOUTH = PropertyBool.create("southFacing");

	/**
	 * Check if a specific face is "set"
	 * 
	 * @param facing the face to check
	 * @return true if the face is "set", false otherwise
	 */
	public boolean isSet(EnumFacing facing) {

		return 0 != (this._value & (1 << facing.getIndex()));
	}

	public boolean none() {
		return 0 == this._value;
	}

	public boolean all() {
		return 0x3f == this._value;
	}

	public boolean down() {
		return this.isSet(EnumFacing.DOWN);
	}

	public boolean up() {
		return this.isSet(EnumFacing.UP);
	}

	public boolean north() {
		return this.isSet(EnumFacing.NORTH);
	}

	public boolean south() {
		return this.isSet(EnumFacing.SOUTH);
	}

	public boolean west() {
		return this.isSet(EnumFacing.WEST);
	}

	public boolean east() {
		return this.isSet(EnumFacing.EAST);
	}

	public IBlockState toBlockState(IBlockState state) {

		return state.withProperty(FACING_DOWN, this.isSet(EnumFacing.DOWN))
				.withProperty(FACING_UP, this.isSet(EnumFacing.UP))
				.withProperty(FACING_WEST, this.isSet(EnumFacing.WEST))
				.withProperty(FACING_EAST, this.isSet(EnumFacing.EAST))
				.withProperty(FACING_NORTH, this.isSet(EnumFacing.NORTH))
				.withProperty(FACING_SOUTH, this.isSet(EnumFacing.SOUTH));
	}

	/**
	 * Return a BlockFacing object that describe the current facing with the given face set or unset
	 *
	 * @param facing the face to modify
	 * @param value the new value for the state of the face
	 * @return a BlockFacing object
	 */
	public BlockFacings set(EnumFacing facing, boolean value) {

		byte newHash = this._value;

		if(value)
			newHash |= (1 << facing.getIndex());
		else
			newHash &= ~(1 << facing.getIndex());

		return BlockFacings.from(Byte.valueOf(newHash));
	}

	/**
	 * Count the number of faces that are in the required state
	 *
	 * @param areSet specify if you are looking for "set" faces (true) or not (false)
	 * @return the number of faces found in the required state
	 */
	public int countFacesIf(boolean areSet) {

		int checkFor = areSet ? 1 : 0;
		int mask = this._value;
		int faces = 0;

		for(int i = 0; i < 6; ++i, mask = mask >>> 1) {

			if((mask & 1) == checkFor)
				++faces;
		}

		return faces;
	}

	/**
	 * Return a PropertyBlockFacings for the current facing
	 *
	 * @return a PropertyBlockFacings value
	 */
	public PropertyBlockFacings toProperty() {

		PropertyBlockFacings[] values = PropertyBlockFacings.values();

		for(int i = 0; i < values.length; ++i)
			if(values[i]._hash == this._value)
				return values[i];

		return PropertyBlockFacings.None;
	}

	/**
	 * Offset the given BlockPos in all direction set in this object
	 *
	 * @param originalPosition the original position
	 * @return the new position
	 */
	public BlockPos offsetBlockPos(BlockPos originalPosition) {

		int x = 0, y = 0, z = 0;

		for(EnumFacing facing : EnumFacing.VALUES)
			if(this.isSet(facing)) {

				x += facing.getFrontOffsetX();
				y += facing.getFrontOffsetY();
				z += facing.getFrontOffsetZ();
			}

		return originalPosition.add(x, y, z);
	}

	/**
	 * Return the first face that is in the required state
	 *
	 * @param isSet specify if you are looking for "set" faces (true) or not (false)
	 * @return the first face that match the required state or null if no face is found
	 */
	public EnumFacing firstIf(boolean isSet) {

		for(EnumFacing facing : EnumFacing.VALUES)
			if(isSet == this.isSet(facing))
				return facing;

		return null;
	}

	/**
	 * Return a BlockFacing object that describe the passed in state
	 * 
	 * @param down the state of the "down" face
	 * @param up the state of the "up" face
	 * @param north the state of the "north" face
	 * @param south the state of the "south" face
	 * @param west the state of the "west" face
	 * @param east the state of the "east" face
	 * @return a BlockFacing object
	 */
	public static BlockFacings from(boolean down, boolean up, boolean north, boolean south, boolean west,
			boolean east) {

		return BlockFacings.from(BlockFacings.computeHash(down, up, north, south, west, east));
	}

	/**
	 * Return a BlockFacing object that describe the passed in state
	 * 
	 * @param facings an array describing the state. the elements of the array must be filled in following the order in
	 *            EnumFacing.VALUES
	 * @return a BlockFacing object
	 */
	public static BlockFacings from(boolean[] facings) {

		return BlockFacings.from(BlockFacings.computeHash(facings));
	}

	@Override
	public String toString() {

		return String.format("Facings: %s%s%s%s%s%s", this.isSet(EnumFacing.DOWN) ? "DOWN " : "",
				this.isSet(EnumFacing.UP) ? "UP " : "", this.isSet(EnumFacing.NORTH) ? "NORTH " : "",
				this.isSet(EnumFacing.SOUTH) ? "SOUTH " : "", this.isSet(EnumFacing.WEST) ? "WEST " : "",
				this.isSet(EnumFacing.EAST) ? "EAST " : "");
	}

	static BlockFacings from(Byte hash) {

		BlockFacings facings = BlockFacings.s_cache.get(hash);

		if(null == facings) {

			facings = new BlockFacings(hash.byteValue());
			BlockFacings.s_cache.put(hash, facings);
		}

		return facings;
	}

	private BlockFacings(byte value) {

		this._value = value;
	}

	static Byte computeHash(boolean down, boolean up, boolean north, boolean south, boolean west, boolean east) {

		byte hash = 0;

		if(down)
			hash |= (1 << EnumFacing.DOWN.getIndex());

		if(up)
			hash |= (1 << EnumFacing.UP.getIndex());

		if(north)
			hash |= (1 << EnumFacing.NORTH.getIndex());

		if(south)
			hash |= (1 << EnumFacing.SOUTH.getIndex());

		if(west)
			hash |= (1 << EnumFacing.WEST.getIndex());

		if(east)
			hash |= (1 << EnumFacing.EAST.getIndex());

		return Byte.valueOf(hash);
	}

	static Byte computeHash(boolean[] facings) {

		byte hash = 0;
		int len = null == facings ? -1 : facings.length;

		if(len < 0 || len > EnumFacing.VALUES.length)
			throw new IllegalArgumentException("Invalid length of facings array");

		for(int i = 0; i < len; ++i) {

			if(facings[i])
				hash |= (1 << EnumFacing.VALUES[i].getIndex());
		}

		return Byte.valueOf(hash);
	}

	private byte _value;

	private static HashMap<Byte, BlockFacings> s_cache;

	static {

		Byte hash;

		s_cache = new HashMap<Byte, BlockFacings>(8);

		hash = BlockFacings.computeHash(false, false, false, false, false, false);
		s_cache.put(hash, NONE = new BlockFacings(hash.byteValue()));

		hash = BlockFacings.computeHash(true, true, true, true, true, true);
		s_cache.put(hash, ALL = new BlockFacings(hash.byteValue()));

		hash = BlockFacings.computeHash(true, false, false, false, false, false);
		s_cache.put(hash, DOWN = new BlockFacings(hash.byteValue()));

		hash = BlockFacings.computeHash(false, true, false, false, false, false);
		s_cache.put(hash, UP = new BlockFacings(hash.byteValue()));

		hash = BlockFacings.computeHash(false, false, true, false, false, false);
		s_cache.put(hash, NORTH = new BlockFacings(hash.byteValue()));

		hash = BlockFacings.computeHash(false, false, false, true, false, false);
		s_cache.put(hash, SOUTH = new BlockFacings(hash.byteValue()));

		hash = BlockFacings.computeHash(false, false, false, false, true, false);
		s_cache.put(hash, WEST = new BlockFacings(hash.byteValue()));

		hash = BlockFacings.computeHash(false, false, false, false, false, true);
		s_cache.put(hash, EAST = new BlockFacings(hash.byteValue()));
	}
}