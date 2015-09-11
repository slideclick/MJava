// SortedCollection interface
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;

public interface SortedCollection {

    public boolean add (Object item);
    public boolean addUnique (Object item);
    public void clear ();
    public Object clone();
    public Collection collectionView();
    public boolean contains (Object item);
    public boolean equals (Object other);
    public int indexOf (Object item);
    public Object get (int i);
    public Object get (Object item);
    public int hashCode ();
    public boolean isEmpty ();
    public Iterator iterator ();
    public Object remove (int i);
    public boolean remove (Object item);
    public int size ();
    public Object[] toArray ();
    public String toString();

}
