package xyz.brassgoggledcoders.boilerplate.items;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;
import xyz.brassgoggledcoders.boilerplate.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.IModAware;
import xyz.brassgoggledcoders.boilerplate.client.models.IHasModel;

public class BaseTool extends ItemTool implements IHasModel, IModAware {
	protected String texturePath;
	protected String name;
	protected IBoilerplateMod mod;

	public BaseTool(Item.ToolMaterial materialIn, Set<Block> effectiveBlocksIn, String texturePath, String name) {
		this(0.0F, 0.0F, materialIn, effectiveBlocksIn, texturePath, name);
	}

	public BaseTool(Item.ToolMaterial materialIn, Set<Block> effectiveBlocksIn, String name) {
		this(0.0F, 0.0F, materialIn, effectiveBlocksIn, "", name);
	}

	public BaseTool(float attackDamageIn, float attackSpeedIn, Item.ToolMaterial materialIn,
			Set<Block> effectiveBlocksIn, String name) {
		this(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn, "", name);
	}

	public BaseTool(float attackDamageIn, float attackSpeedIn, Item.ToolMaterial materialIn,
			Set<Block> effectiveBlocksIn, String texturePath, String name) {
		super(attackDamageIn, attackSpeedIn, materialIn, effectiveBlocksIn);
		this.setMaxDamage(materialIn.getMaxUses());
		this.setMaxStackSize(1);
		this.name = name;
		this.texturePath = texturePath;
		if(!texturePath.isEmpty() && !texturePath.endsWith("/"))
			this.texturePath += "/";
		this.setUnlocalizedName(name);
	}

	@Override
	public String[] getResourceLocations() {
		return new String[] {texturePath + name};
	}

	@Override
	public IBoilerplateMod getMod() {
		return mod;
	}

	@Override
	public void setMod(IBoilerplateMod mod) {
		this.mod = mod;
	}
}
