// TreeIterator interface
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;

public interface TreeIterator {

    public boolean addRoot (Object item);
    public boolean addFirstChild (Object item);
    public boolean addRightSibling (Object item);
    public Object  getCurrent ();
    public Object  getFirstChild ();
    public Object  getParent ();
    public Object  getRightSibling ();
    public Object  getRoot ();
    public boolean hasChild ();
    public boolean hasCurrentPosition ();
    public boolean hasParent ();
    public boolean hasRightSibling ();
    public boolean moveTo (int index[]);
    public boolean moveTo (Object item);
    public Object  removeCurrent ();
    public Object  setCurrent (Object item);
}
