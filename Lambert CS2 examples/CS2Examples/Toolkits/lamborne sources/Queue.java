// Queue interface
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;

public interface Queue {

   public void clear();
   /* 
    * Clears the queue
    * Pre:  none
    * Post: the queue is empty
    * Ret:  void
    */

   public Object clone();
    
   public Collection collectionView();
   /*
    * Returns a collection view of the queue.
    */
   
   public Object dequeue();
   /*
    * Removes the first item from the queue and returns it
    *
    * Pre:  the queue is not empty
    * Post: first item is removed from queue
    * Ret:  first item in the queue
    */

   public void enqueue(Object item);
   /*
    * Pre:  item is the object to be added to the queue 
    *       and is not null
    * Post: the item has been added to the queue
    * Ret:  void
    */

   public boolean equals (Object other);
   /*
    * Returns true if the items in the two queues are equal or false
    * otherwise. Basically, two queues are equal if their iterators are
    * equal.
    *
    * Pre:  none
    * Post: no change
    * Ret:  true if the iterators are equal or false otherwise.
    */

   public int hashCode();

   public boolean isEmpty();
   /*   
    * Tests if the queue contains any items
    *
    * Pre:  none
    * Post: no change
    * Ret:  true if the queue was empty and false otherwise
    */
    
   public Iterator iterator();
   /*
    * Returns an immutable iterarot over items in the queue. 
    *
    * Pre:  none
    * Post: no change
    * Ret:  an iterator over the items in the queue
    */

   public Object peek();
   /*
    * Accesses the first item of the queue without modifying the queue
    *
    * Pre:  the queue is not empty
    * Post: no change
    * Ret:  first item in the queue
    */

   public int size();
   /* 
    * Returns the number of items in the queue
    *
    * Pre:  none
    * Post: no change
    * Ret:  number of items in the queue 
    */
    
   public Object[] toArray();
   /*
    * Returns an array of items in the queue. 
    *
    * Pre:  none
    * Post: no change
    * Ret:  an array containing the items in the queue
    */

   public String toString();
   /*
    * Returns information that describes the state of the queue. 
    *
    * Pre:  none
    * Post: no change
    * Ret:  a comma delimited list of the items in the stack from head
    *       to tail
    */

}
