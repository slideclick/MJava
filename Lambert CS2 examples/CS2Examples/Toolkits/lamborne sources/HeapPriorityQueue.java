// HeapPriorityQueue
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;
import java.io.Serializable;

/**
 * This class ...
 */

public class HeapPriorityQueue extends AbstractPriorityQueue
                               implements PriorityQueue, Cloneable {

    
    private ArrayHeap heap;     // A heap of items

    public HeapPriorityQueue()
    /*
     * Instantiates a new priority queue
     *
     * Pre:  none
     * Post: heap = a new Heap  
     * Post: count = 0
     */
    {
        super();
        heap = new ArrayHeap();
    }

    public void clear()
    {
        super.clear();
        heap = new ArrayHeap();
    }

    public Object clone()
    {
        HeapPriorityQueue clone = new HeapPriorityQueue();
        clone.heap = (ArrayHeap)(heap.clone());
        clone.size = size;
        return clone;
    }

    public String debugString()
    {
        return heap.debugString();
    }
    
    public Object dequeue()
    {
        if (size == 0)
            throw new NoSuchElementException 
            ("Trying to dequeue an empty priority queue");
        
        size--;
        modCount++;
        return ((PriorityNode)(heap.pop())).value;
    }

    public void enqueue(Object item, int priority)
    {
        if (item == null)
            throw new IllegalArgumentException 
            ("Trying to enqueue a null item");
        if (priority < 1)
            throw new IllegalArgumentException 
            ("Priority must be >= 1 ");

        size++;
        modCount++;
        heap.add (new PriorityNode (item, priority));
    }    

    public Iterator iterator()
    // The iterator returns the items in their queued order
    {
        return new InnerIter();
    }
    
    public Object peek()
    {
        if (size == 0)
            throw new NoSuchElementException 
            ("Trying to peek at an empty priority queue");
        
        return ((PriorityNode)(heap.peek())).value;
    }

    public String toString()
    {
        if (size == 0) return "[]";
         
        Iterator iter = heap.iterator();
        String str = "";
        PriorityNode next;
        int currentPriority = -1;
        while (iter.hasNext()){
            next = (PriorityNode)(iter.next());
            if (currentPriority == next.priority)
                str += ", " + next.value;
            else{
                if (currentPriority != -1)
                    str += "]\n"; 
                currentPriority = next.priority;
                str += "Priority " + currentPriority + ": [" + next.value;
            }
        }
        return str + "]"; 
    }                

// =============================== Inner Classes =======================

    private class InnerIter extends Object implements Iterator{
        
        private Iterator heapIter;
        private int      expectedModCount = modCount;
        
        private InnerIter()
        {
            heapIter = heap.iterator();
        }
        
        public boolean hasNext()
        {
            return heapIter.hasNext();
        }
        
        public Object next()
        {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException
                ("There are no more elements");            
            
            return ((PriorityNode)((heapIter.next()))).value;
        }

        public void remove()
        {
            throw new UnsupportedOperationException("Remove not allowed");
        }
    }

    private static int subpriorityCounter = 0;
    private class PriorityNode implements Comparable, Serializable {

        private Object     value;        // Value stored in this item
        private int        priority;     // Priority of item
        private int        subpriority;  // subpriority of item, assigned
                                         // by constructor
    
        private PriorityNode()
        {
            throw new IllegalArgumentException 
                ("Trying to create a null priority item");
        }

        private PriorityNode(Object value, int priority)
        {
            this.value = value;
            this.priority = priority;
            subpriority = subpriorityCounter;
            subpriorityCounter++;        // Warning: Jumps from +2G to -2G 
        }
    
        public int compareTo (Object item)
        {
            int prior    = ((PriorityNode)item).priority; 
            int subprior = ((PriorityNode)item).subpriority; 
            if (priority != prior) 
                return priority - prior;
            else
                return subprior - subpriority;
        }
    
        public String toString()
        {
            return "(" + value + "," + 
                    priority + "," + subpriority + ")";
        }
    }

}
