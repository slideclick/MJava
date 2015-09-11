// Heap interface
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;

public interface Heap {

   public boolean add (Object item);

   public void clear();
   /* 
    * Clears the heap
    * Pre:  none
    * Post: the heap is empty
    * Ret:  void
    */

   public Object clone();
    
   public Collection collectionView();
   /*
    * Returns a collection view of the heap.
    */
   
   public boolean equals (Object other);
   /*
    * Returns true if the items in the two heaps are equal or false
    * otherwise. Basically, two heaps are equal if their iterators are
    * equal.
    *
    * Pre:  none
    * Post: no change
    * Ret:  true if the heaps are equal or false otherwise.
    */

   public int hashCode();

   public boolean isEmpty();
   /*  
    * Tests if the heap contains any items
    *
    * Pre:  none
    * Post: no change
    * Ret:  true if the heap was empty and false otherwise
    */
    
   public Iterator iterator();
   /*
    * Returns an immutable iterarot over items in the heap. 
    *
    * Pre:  none
    * Post: no change
    * Ret:  an iterator over the items in the heap
    */

   public Object peek();
   /*
    * Accesses the top item of the heap without modifying the heap
    *
    * Pre:  the heap is not empty
    * Post: no change
    * Ret:  top item in the heap
    */

   public Object pop();

   public int size();
   /* 
    * Returns the number of items in the heap
    *
    * Pre:  none
    * Post: no change
    * Ret:  number of items in the heap 
    */
    
   /* 
    * Static sort methods
    *
    * Static methods cannot be declared in the interface, so a comment
    * is placed here as a reminder
    *
    * public static Object[] sort (Object a[]);
    * public static Object[] sort (Iterator iter);
    */

   public Object[] toArray();
   /*
    * Returns an array of items in the heap. 
    *
    * Pre:  none
    * Post: no change
    * Ret:  an array containing the items in the heap
    */

   public String toString();
   /*
    * Returns information that describes the state of the heap. 
    *
    * Pre:  none
    * Post: no change
    * Ret:  a comma delimited list of the items in the heap, from
    *       top to bottom
    */

}
