// AbstractHeap
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;

abstract public class AbstractHeap extends AbstractContainer {

    public AbstractHeap()
    {
        super();
    }
    
    public boolean equals (Object other)
    {
        AbstractHeap otherColl;

        if (other == this) return true;
        if (!(other instanceof AbstractHeap)) return false;

        otherColl = (AbstractHeap)other;
        if (size() != otherColl.size()) return false;

        Iterator thisIter = iterator();
        Iterator otherIter = otherColl.iterator();
        while (thisIter.hasNext()){
            if (!thisIter.next().equals (otherIter.next()))
                return false;
        }

        return true;
    }
    

    // Sort in ascending order
    public static Object[] sort (Object in[])
    {
        if (in == null)
            throw new IllegalArgumentException 
            ("Trying to sort when the array is null");
        if (in.length == 0)
            throw new IllegalArgumentException 
            ("Trying to sort when array has length 0");
        return AbstractHeap.sort (in, in.length);
    }

    // Sort in ascending order
    public static Object[] sort (Object in[], int size)
    {
        if (in == null)
            throw new IllegalArgumentException 
            ("Trying to sort when the array is null");
        if (in.length == 0)
            throw new IllegalArgumentException 
            ("Trying to sort when array has length 0");
        if (in.length < size)
            throw new IllegalArgumentException
            ("Trying to sort when size is larger thatn in.lenght");
            
        int i;
        Heap heap = new ArrayHeap (size);
        for (i = 0; i < size; i++){
            if (!(in[i] instanceof Comparable))
                throw new IllegalArgumentException 
                ("Trying to sort when the array contains a non-comparable item");
            heap.add (in[i]);
        }
        Object out[] = new Object[size];
        for (i = out.length - 1 ; i >= 0; i--)
            out[i] = heap.pop(); 
        return out;
    }   
                                                         
    // Sort in ascending order
    public static Object[] sort (Collection col)
    {
        if (col == null)
            throw new IllegalArgumentException 
            ("Trying to sort when the collection is null");
        if (col.size() == 0)
            throw new IllegalArgumentException 
            ("Trying to sort when the collection is empty");
        
        Iterator iter = col.iterator();
        int i;
        Heap heap = new ArrayHeap ();
        Object next;
        while (iter.hasNext()){
            next = iter.next();
            if (!(next instanceof Comparable))
                throw new IllegalArgumentException 
                ("Trying to sort when the iterator contains a non-comparable item");
            heap.add (next);
        }
        Object out[] = new Object[heap.size()];
        for (i = out.length - 1 ; i >= 0; i--)
            out[i] = heap.pop(); 
        return out;
    }  
    
}
