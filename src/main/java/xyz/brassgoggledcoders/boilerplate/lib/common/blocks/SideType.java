package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.util.IStringSerializable;

/**
 * @author SkySom
 */
public enum SideType implements IStringSerializable
{
	INPUT, OUTPUT, NONE;

	@Override
	public String getName()
	{
		return this.toString();
	}
}
