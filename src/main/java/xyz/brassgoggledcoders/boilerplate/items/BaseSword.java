package xyz.brassgoggledcoders.boilerplate.items;

import net.minecraft.item.ItemSword;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.IModAware;
import xyz.brassgoggledcoders.boilerplate.client.models.IHasModel;

public class BaseSword extends ItemSword implements IHasModel, IModAware {

	protected String texturePath;
	protected String name;
	protected IBoilerplateMod mod;
	
	public BaseSword(ToolMaterial material, String name)
	{
		this(material, "", name);
	}
	
	public BaseSword(ToolMaterial material, String texturePath, String name) {
		super(material);
		this.name = name;
		this.texturePath = texturePath;
		if(!texturePath.isEmpty() && !texturePath.endsWith("/"))
		{
			this.texturePath += "/";
		}
		this.setUnlocalizedName(name);
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
