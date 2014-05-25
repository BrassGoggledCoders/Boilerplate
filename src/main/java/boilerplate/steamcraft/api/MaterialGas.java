/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [May 7, 2014, 11:21:06 AM]
 */
package boilerplate.steamcraft.api;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;

// TODO: Auto-generated Javadoc
/**
 * The Class MaterialGas.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class MaterialGas extends MaterialLiquid
{
	
	/** The can burn. */
	private final boolean canBurn;

	/**
	 * Instantiates a new material gas.
	 *
	 * @param canBurn the can burn
	 */
	public MaterialGas(final boolean canBurn)
	{
		super(MapColor.airColor);
		this.canBurn = canBurn;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.material.Material#setRequiresTool()
	 */
	@Override
	public Material setRequiresTool()
	{
		return Material.air;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.material.Material#getCanBurn()
	 */
	@Override
	public boolean getCanBurn()
	{
		return canBurn;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.material.Material#isReplaceable()
	 */
	@Override
	public boolean isReplaceable()
	{
		return true;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.material.Material#isOpaque()
	 */
	@Override
	public boolean isOpaque()
	{
		return false;
	}
}
