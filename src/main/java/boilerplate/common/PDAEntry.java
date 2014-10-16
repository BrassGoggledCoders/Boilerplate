package boilerplate.common;

import java.util.ArrayList;
import java.util.List;

public class PDAEntry
{
	static String name = "";
	public PDAEntry(EnumEntryType type, String name, String text)
	{
		name = this.name;
	}
	public static class EntryRegistry
	{
		public static List<PDAEntry> entries = new ArrayList();
	}
	public enum EnumEntryType
	{
		BLOCKS, ITEMS, ENTITIES
	}
	public static String getName()
	{
		return name;
	}
	public PDAEntry register()
	{
		EntryRegistry.entries.add(this);
		return this;
	}
}
