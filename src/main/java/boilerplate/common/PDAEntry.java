package boilerplate.common;

import java.util.LinkedList;

public class PDAEntry
{
	static String name = "";
	public PDAEntry(EnumEntryType type, String name, String text)
	{
		name = this.name;
	}
	public static class EntryRegistry
	{
		public static LinkedList<PDAEntry> entries = new LinkedList();
	}
	public enum EnumEntryType
	{
		BLOCKS, ITEMS, ENTITIES
	}
	public static String getName()
	{
		return name;
	}
}
