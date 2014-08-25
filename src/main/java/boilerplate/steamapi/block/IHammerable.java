package boilerplate.steamapi.block;

import net.minecraft.item.ItemStack;

public interface IHammerable
{
	public abstract ItemStack getOutput(int meta);
}
