package boilerplate.common;

import java.util.ArrayList;
import java.util.List;

public class PDAEntryList
{
    /** Is the smallest column used to display a achievement on the GUI. */
    public static int minDisplayColumn;
    /** Is the smallest row used to display a achievement on the GUI. */
    public static int minDisplayRow;
    /** Is the biggest column used to display a achievement on the GUI. */
    public static int maxDisplayColumn;
    /** Is the biggest row used to display a achievement on the GUI. */
    public static int maxDisplayRow;
    /** Holds a list of all registered achievements. */
    public static List pdaList = new ArrayList();
    /**
     * A stub function called to make the static initializer for this class run.
     */
    public static void init() {}
}