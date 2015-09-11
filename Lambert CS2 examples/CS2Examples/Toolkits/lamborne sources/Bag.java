// Bag interface
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;

public interface Bag {

    public int         add (Object item);
    public void        clear ();
    public Object      clone ();
    public Collection  collectionView ();
    public boolean     contains (Object item);
    public boolean     equals (Object other);
    public int         getCount (Object item);
    public int         hashCode ();
    public boolean     isEmpty ();
    public Iterator    iterator ();
    public int         remove (Object item);
    public int         size ();
    public String      toString ();  

}
