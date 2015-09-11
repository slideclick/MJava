// Tree interface
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;

public interface Tree {

    public boolean      addFirstChild (int index[], Object item);
    public boolean      addRoot (Object item);
    public boolean      addRightSibling (int index[], Object item);
    public void         clear ();
    public Object       clone ();
    public Collection   collectionView ();
    public boolean      contains (Object item);
    public boolean      equals (Object other);
    public Object       get (Object item);
    public Object       get (int index[]);
    public int          hashCode ();
    public String       hierarchicalList ();
    public int[]        indexOf (Object item);
    public boolean      isEmpty ();
    public Iterator     iterator ();
    public boolean      remove (Object item);
    public Object       remove (int index[]);
    public Object       set (int index[], Object item);
    public int          size ();
    public Object[]     toArray ();
    public String       toString ();
    public TreeIterator treeIterator();
}
