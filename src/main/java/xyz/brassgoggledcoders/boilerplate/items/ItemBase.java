package xyz.brassgoggledcoders.boilerplate.items;

import net.minecraft.item.Item;
import xyz.brassgoggledcoders.boilerplate.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.IModAware;
import xyz.brassgoggledcoders.boilerplate.client.models.IHasModel;

public class ItemBase extends Item implements IHasModel, IModAware
{
	protected String texturePath;
	protected String name;
	protected IBoilerplateMod mod;

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

	@Override
	public IBoilerplateMod getMod()
	{
		return mod;
	}

	@Override
	public void setMod(IBoilerplateMod mod)
	{
		this.mod = mod;
	}
}
