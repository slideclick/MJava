// AbstractPriorityQueue
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;

abstract public class AbstractPriorityQueue extends AbstractContainer {

    public AbstractPriorityQueue()
    {
        super();
    }
    
    public boolean equals (Object other)
    // Equal if iterators deliver up equal items in same order, which doesn't 
    // imply that the priorities of these items are equal.
    {
        AbstractPriorityQueue otherColl;

        if (other == this) return true;
        if (!(other instanceof AbstractPriorityQueue)) return false;

        otherColl = (AbstractPriorityQueue)other;
        if (size() != otherColl.size()) return false;

        Iterator thisIter = iterator();
        Iterator otherIter = otherColl.iterator();
        while (thisIter.hasNext()){
            if (!thisIter.next().equals (otherIter.next()))
                return false;
        }

        return true;
    }

    public void enqueue(Object item)
    {
        enqueue (item, 1);
    }
    
    public abstract void enqueue(Object item, int priority);
    
}
