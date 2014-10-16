package boilerplate.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class PDAPage
{
    private String name;
    private LinkedList<PDAEntry> pdaEntries;

    public PDAPage(String name, PDAEntry... entries)
    {
        this.name = name;
        this.pdaEntries = new LinkedList<PDAEntry>(Arrays.asList(entries));
    }

    public String getName()
    {
        return name;
    }

    public List<PDAEntry> getPDAs()
    {
        return pdaEntries;
    }

    private static LinkedList<PDAPage> pdaPages = new LinkedList<PDAPage>();

    /**
     * Registers an PDA page.
     * @param page The page.
     */
    public static void registerPDAPage(PDAPage page)
    {
        if (getPDAPage(page.getName()) != null)
        {
            throw new RuntimeException("Duplicate PDA page name \"" + page.getName() + "\"!");
        }
        pdaPages.add(page);
    }

    /**
     * Will return an PDA page by its index on the list.
     * @param index The page's index.
     * @return the PDA page corresponding to the index or null if invalid index
     */
    public static PDAPage getPDAPage(int index)
    {
        return pdaPages.get(index);
    }

    /**
     * Will return an PDA page by its name.
     * @param name The page's name.
     * @return the PDA page with the given name or null if no such page
     */
    public static PDAPage getPDAPage(String name)
    {
        for (PDAPage page : pdaPages)
        {
            if (page.getName().equals(name))
            {
                return page;
            }
        }
        return null;
    }

    /**
     * Will return the list of PDA pages.
     * @return the list's size
     */
    public static Set<PDAPage> getPDAPages()
    {
        return new HashSet<PDAPage>(pdaPages);
    }

    /**
     * Will return whether an PDA is in any page or not.
     * @param PDA The PDA.
     */
    public static boolean isPDAInPages(PDAEntry PDA)
    {
        for (PDAPage page : pdaPages)
        {
            if (page.getPDAs().contains(PDA))
            {
                return true;
            }
        }
        return false;
    }

    public static String getTitle(int index)
    {
        return index == -1 ? "Minecraft" : getPDAPage(index).getName();
    }
}