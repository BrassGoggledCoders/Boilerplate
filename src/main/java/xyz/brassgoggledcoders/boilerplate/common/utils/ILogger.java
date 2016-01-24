/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package xyz.brassgoggledcoders.boilerplate.common.utils;

/**
 * @author SkySom
 */
public interface ILogger
{
	void warning(String message);

	void info(String message);

	void fatal(String message);

	void error(String message);

	void devWarning(String message);

	void devInfo(String message);

	void devFatal(String message);

	void devError(String message);
}
