// AbstractTree
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;

abstract public class AbstractTree extends AbstractContainer {

    public AbstractTree()
    {
        super();
    }
    
    public boolean addFirstChild (int index[], Object item)
    {
        TreeIterator treeIter = treeIterator();
        if (treeIter.moveTo (index))
            return treeIter.addFirstChild (item);
        else
            return false;
    }

    public boolean addRoot (Object item)
    {
        return treeIterator().addRoot (item);
    }

    public boolean addRightSibling (int index[], Object item)
    {
        TreeIterator treeIter = treeIterator();
        if (treeIter.moveTo (index))
            return treeIter.addRightSibling (item);
        else
            return false;
    }
    
    public boolean contains (Object item)
    {
        return (get(item) != null);
    }
    
    public boolean equals (Object other)
    {
        throw new UnsupportedOperationException
        ("This is left as an exercise");
    }
    
    public Object get (Object item)
    {
        throw new UnsupportedOperationException
        ("This is left as an exercise");
    }
    
    public Object get (int index[])
    {
        TreeIterator treeIter = treeIterator();
        treeIter.moveTo (index);
        return treeIter.getCurrent();
    }
    
    
    public int[] indexOf (Object item)              
    {
        throw new UnsupportedOperationException
        ("This is left as an exercise");
    }
        
    public Object remove (int index[])
    {
        TreeIterator treeIter = treeIterator();
        if (treeIter.moveTo (index))
            return treeIter.removeCurrent();
        else
            return null;
    }
    
    public boolean remove (Object item)
    {
        throw new UnsupportedOperationException
        ("This is left as an exercise");
    }
    
    public Object set (int index[], Object item)
    {
        TreeIterator treeIter = treeIterator();
        if (treeIter.moveTo (index))
            return treeIter.setCurrent (item);
        else
            return null;
    }
    
    public abstract TreeIterator treeIterator();
    
}
