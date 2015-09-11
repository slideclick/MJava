// Stack interface
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;

public interface Stack {

   public void clear();
   /* 
    * Clears the stack
    * Pre:  none
    * Post: the stack is empty
    * Ret:  void
    */

   public Object clone();
    
   public Collection collectionView();
   /*
    * Returns a collection view of the stack.
    */
   
   public boolean equals (Object other);
   /*
    * Returns true if the items in the two stacks are equal or false
    * otherwise. Basically, two stacks are equal if their iterators are
    * equal.
    *
    * Pre:  none
    * Post: no change
    * Ret:  true if the stacks are equal or false otherwise.
    */

   public int hashCode();

   public boolean isEmpty();
   /*  
    * Tests if the stack contains any items
    *
    * Pre:  none
    * Post: no change
    * Ret:  true if the stack was empty and false otherwise
    */
    
   public Iterator iterator();
   /*
    * Returns an immutable iterarot over items in the stack. 
    *
    * Pre:  none
    * Post: no change
    * Ret:  an iterator over the items in the stack
    */

   public Object peek();
   /*
    * Accesses the top item of the stack without modifying the stack
    *
    * Pre:  the stack is not empty
    * Post: no change
    * Ret:  top item in the stack
    */

   public Object pop();
   /*
    * Removes the top item from the stack and returns it
    *
    * Pre:  the stack is not empty
    * Post: top item is removed from stack
    * Ret:  top item in the stack
    */

   public void push(Object item);
   /*
    * Pre:  item is the object to be pushed onto the stack 
    *       and is not null
    * Post: the item has been pushed onto the stack
    * Ret:  void
    */

   public int size();
   /* 
    * Returns the number of items in the stack
    *
    * Pre:  none
    * Post: no change
    * Ret:  number of items in the stack 
    */
    
   public Object[] toArray();
   /*
    * Returns an array of items in the stack. 
    *
    * Pre:  none
    * Post: no change
    * Ret:  an array containing the items in the stack
    */

   public String toString();
   /*
    * Returns information that describes the state of the stack. 
    *
    * Pre:  none
    * Post: no change
    * Ret:  a comma delimited list of the items in the stack, from
    *       top to bottom
    */

}
