package boilerplate.common;

import java.util.LinkedList;

public class PDAEntry
{
	public PDAEntry(EnumEntryType type, String name, String text)
	{

	}
	public class EntryRegistry
	{
		public LinkedList<PDAEntry> entries = new LinkedList();
	}
	public enum EnumEntryType
	{
		BLOCKS, ITEMS, ENTITIES
	}
}
