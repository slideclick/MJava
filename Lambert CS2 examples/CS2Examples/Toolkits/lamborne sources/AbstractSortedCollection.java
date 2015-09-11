// AbstractSortedCollection
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;

abstract public class AbstractSortedCollection extends AbstractContainer {

    public AbstractSortedCollection()
    {
        super();
    }

    public abstract boolean add (Object item);

    public boolean addUnique (Object item)
    {
        if (!(item instanceof Comparable))
            throw new IllegalArgumentException
            ("Cannot add a non-comparable item");
            
        if (contains (item))
            return false;
        else{
            add (item);
            return true;
        }
    }
            
    public boolean contains (Object item)
    {
        if (!(item instanceof Comparable))
            throw new IllegalArgumentException
            ("Trying to see if collection contains non-comparable item");
        
        return get (item) != null;
    }
    
    public boolean equals (Object other)
    {
        AbstractSortedCollection otherColl;

        if (other == this) return true;
        if (!(other instanceof AbstractSortedCollection)) return false;

        otherColl = (AbstractSortedCollection)other;
        if (size() != otherColl.size()) return false;

        Iterator thisIter = iterator();
        Iterator otherIter = otherColl.iterator();
        while (thisIter.hasNext()){
            if (!thisIter.next().equals (otherIter.next()))
                return false;
        }

        return true;
    }
        
    public int indexOf (Object item)
    {
        // Hint: traverse the iterator.
        throw new UnsupportedOperationException
        ("Implementation of this method is left as an exercise");
    }
        
    public abstract Object get (int i);
     
    public abstract Object get (Object item);
    
    public Object remove (int i)  
    {
        if (i < 0 || i >= size)
            throw new IllegalArgumentException
            ("Trying to get when index is out of range: 1 to " + size);
        
        Object item = get (i);
        remove (item);
        return item;
    }
    
    public abstract boolean remove (Object item);
     
}                                   
