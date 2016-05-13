package xyz.brassgoggledcoders.boilerplate.lib.common.blocks;

import net.minecraft.block.properties.PropertyEnum;

public class Properties
{
	public static final PropertyEnum[] SIDECONFIG = {
			PropertyEnum.create("sideconfig_down", SideType.class),
			PropertyEnum.create("sideconfig_up", SideType.class),
			PropertyEnum.create("sideconfig_north", SideType.class),
			PropertyEnum.create("sideconfig_south", SideType.class),
			PropertyEnum.create("sideconfig_west", SideType.class),
			PropertyEnum.create("sideconfig_east", SideType.class)
	};
}

