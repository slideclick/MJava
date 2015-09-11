// LinkedPriorityQueue
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

/**
 * This class ...
 */
 
import java.util.*;

public class LinkedPriorityQueue extends AbstractPriorityQueue
                                 implements PriorityQueue, Cloneable {

    private Queue[] queues;     //array of queues

    public LinkedPriorityQueue()
    {
        throw new IllegalArgumentException 
        ("Must specify maxPriority when creating a LinkedPriorityQueue");
    }
    
    public LinkedPriorityQueue(int maxPriority)
    /*
     * Instantiates a new priority queue with maxPriority priorites
     *
     * Pre:  maxPriority >= 2
     * Post: queues = array of maxPriority queues  
     * Post: size = modCount = 0
     */
    {
        super();
        if (maxPriority < 2)    
            throw new IllegalArgumentException ("maxPriority must be >= 2");
        queues = new LinkedQueue[maxPriority];
        for (int i = 0; i < queues.length; i++)
           queues[i] = new LinkedQueue();
    }
    
    public void clear()
    {
        super.clear();
        for (int i = 0; i < queues.length; i++)
           queues[i].clear();
    }

    public Object clone()
    {
        LinkedPriorityQueue clone = new LinkedPriorityQueue (queues.length);
        for (int i = queues.length - 1; i >= 0; i--){
            Iterator iter = queues[i].iterator();
            clone.queues[i] = new LinkedQueue();
            while (iter.hasNext())
                clone.queues[i].enqueue (iter.next());
        }
        clone.size = size;
        return clone;
    }
        

    public Object dequeue()
    {
        if (size == 0)
            throw new NoSuchElementException 
            ("Trying to dequeue an empty priority queue");
        

        size--;
        modCount++;
        for (int i = queues.length - 1; i >= 0; i--)
            if (! queues[i].isEmpty())
                return queues[i].dequeue();
        return null;                        // Should not be reached
    }

    public void enqueue(Object item, int priority)
    {
        if (item == null)
            throw new IllegalArgumentException 
            ("Trying to enqueue a null item");
        if (priority < 1 || priority > queues.length)
            throw new IllegalArgumentException 
            ("Priority must be between " + 1 + " and " + queues.length);

        size++;
        modCount++;
        queues[priority - 1].enqueue(item);
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
        
        for (int i = queues.length - 1; i >= 0; i--)
            if (! queues[i].isEmpty())
                return queues[i].peek();
        return null;
    }

    public String toString()
    {
        if (size == 0) return "[]";
        
        String str;
        str = "";
        for (int i = queues.length - 1; i >= 0; i--){
            if (queues[i].size() > 0){
                str += "Priority " + (i + 1) + ": ";
                str += queues[i].toString();
            }
            if (i != queues.length - 1)
                str += "\n";
        }
        return str;
    }
    
//************************** Inner Classes ********************************

    private class InnerIter extends Object implements Iterator{
    
        private Iterator    iterOnCurrentQueue;
        private int         currentQueue;
        private Object      nextEl;
        private int         left;
        private int         expectedModCount = modCount;
        
        private InnerIter()
        {
            left = size;
            currentQueue = queues.length;
            iterOnCurrentQueue = null;
            getNext();
        }
        
        public boolean hasNext()
        {
            return left > 0;
        }
        
        public Object next()
        {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException
                ("There are no more elements");            
            
            Object item = nextEl;
            left--;
            getNext();
            return item;
        }

        private void getNext()
        {
            if (!hasNext()) return;
            
            if (iterOnCurrentQueue != null &&
                iterOnCurrentQueue.hasNext())
            {
                nextEl = iterOnCurrentQueue.next();
                return;
            }
            
            for (int i = currentQueue - 1; i >= 0; i--){
                if (! queues[i].isEmpty()){
                    iterOnCurrentQueue = queues[i].iterator();
                    currentQueue = i;
                    nextEl = iterOnCurrentQueue.next();
                    break;
                }
            }
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException("Remove not allowed");
        }
    }
        
}
