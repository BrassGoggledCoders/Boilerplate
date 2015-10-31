/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.common.utils.entity;

import java.util.Map;

/**
 * @author SkySom
 */
public class KeyValue<K, V> implements Map.Entry<K, V>
{
	private K key;
	private V value;

	public KeyValue(K key, V value)
	{
		this.key = key;
		this.value = value;
	}

	public K getKey()
	{
		return this.key;
	}

	public V getValue()
	{
		return this.value;
	}

	public K setKey(K key)
	{
		return this.key = key;
	}

	public V setValue(V value)
	{
		return this.value = value;
	}
}

