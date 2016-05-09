package xyz.brassgoggledcoders.boilerplate.lib.common.items;

import net.minecraft.item.Item;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.IHasModel;

public class ItemBase extends Item implements IHasModel
{
	protected String texturePath;
	protected String name;

	public ItemBase(String name)
	{
		this("", name);
	}

	public ItemBase(String texturePath, String name)
	{
		super();
		this.name = name;
		this.texturePath = texturePath;
		if(!texturePath.isEmpty() && !texturePath.endsWith("/"))
		{
			this.texturePath += "/";
		}
		this.setUnlocalizedName(name);
		this.setCreativeTab(BoilerplateLib.getMod().getCreativeTab());
	}

	@Override
	public String[] getResourceLocations()
	{
		return new String[] {texturePath + name};
	}
}
