/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Nov 3, 2014, 2:59:55 PM (GMT)]
 */
package xyz.brassgoggledcoders.boilerplate.lib.common.manual;

/**
 * A LexiconPage that implements this has two unlocalized names
 * rather than one. (See brew pages)
 */
public interface ITwoNamedPage {

	public void setSecondUnlocalizedName(String name);

	public String getSecondUnlocalizedName();

}
