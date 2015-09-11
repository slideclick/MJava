// PriorityQueue
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;


public interface PriorityQueue extends Queue {

    public void enqueue(Object item, int priority);
    /*
     * Adds the item to the queue behind all items of the same or higher
     * priority
     *
     * Pre:  item is the object to be enqueued onto the queue and is not null
     * Pre:  priority is the integer priority of the item, ranging from 1 to
     *       the number of priorities allowed by the queue, which for some
     *       implementations is unbounded
     * Post: the item has been added to the queue behind all items of the 
     *       same or higher priority
     * Post: size and modCount incremented
     * Ret:  void
     */
}
