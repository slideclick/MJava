// LinkedStack
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;
import java.io.Serializable;

public class LinkedStack extends AbstractStack 
                         implements Stack, Cloneable {

   private OneWayNode top;      //top node in the stack
    
   public LinkedStack()
   /*
    * Instantiates a new stack
    *
    * Pre:  none
    * Post: top = null
    */
   {
      super();
      top = null;
   }

   public LinkedStack (Collection col)
   /*
    * Instantiates a new stack with the contents of a collection.
    * The first item in the collection will be at the top of the stack. 
    *
    * Pre:  col is not null
    * Post: top = null if collection empty else top refers to top of stack
    */
   {
      super();
      
      if (col == null)
         throw new IllegalArgumentException("Collection is null");
               
      top = null;
      appendIterator(col.iterator());
   }

   public LinkedStack (Iterator iter)
   /*
    * Instantiates a new stack with the contents of an iterator.
    * The first item in the interator will be at the top of the stack. 
    *
    * Pre:  iter is not null
    * Post: top = null if iterator empty else top refers to top of stack
    */
   {
      super();
      
      if (iter == null)
         throw new IllegalArgumentException("Iterator is null");
               
      top = null;
      appendIterator (iter);
   }

   private void appendIterator (Iterator iter)
   {
      Object next;
      
      OneWayNode last = top;
      if (iter.hasNext() && top == null){
         next = iter.next();
         
         if (next == null)
            throw new IllegalArgumentException 
            ("Trying to place a null item in a stack");
         
         top = new OneWayNode (next);
         last = top;
         size++;
      }
      while (iter.hasNext()){
         next = iter.next();
         
         if (next == null)
            throw new IllegalArgumentException 
            ("Trying to place a null item in a stack");
         
         last.next = new OneWayNode (next);
         last = last.next;
         size++;
      }
   }
   
   public void clear()
   /*
    * Clears the stack
    *
    * Pre:  none
    * Post: top = null
    * Ret:  void
    */
   {
      super.clear();
      top = null;
   }

   public Object clone()
   {
      return new LinkedStack (iterator());
   }
    
   public Iterator iterator()
   // The iterator returns items in their stack order
   {
      return new InnerIter();
   }

   public Object peek(){
      if (size == 0)
         throw new NoSuchElementException ("Trying to peek at an empty stack");
      return top.value;
   }

   public Object pop(){
      if (size == 0)
         throw new NoSuchElementException ("Trying to pop an empty stack");
      size--;
      modCount++;
      Object item = top.value;
      top = top.next;
      return item;
   }

   public void push(Object item){
      if (item == null)
         throw new IllegalArgumentException 
         ("Trying to push null onto the stack");
      size++;
      modCount++;
      OneWayNode n = new OneWayNode (item, top);
      top = n;
   } 
   
//************************** Inner Classes ********************************

   private class InnerIter implements Iterator{
    
      private OneWayNode curPos;
      private int        expectedModCount = modCount;

        
      private InnerIter()
      {
         curPos = top;
      }
        
      public boolean hasNext()
      {
         return curPos != null;
      }
        
      public Object next()
      {
         if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
         if (!hasNext())
            throw new NoSuchElementException("There are no more elements");   
                     
         Object item = curPos.value;
         curPos = curPos.next;
         return item;
      }
      
      public void remove()
      {
         throw new UnsupportedOperationException("Remove not allowed");
      }
   }
   
   private class OneWayNode implements Serializable{
       private Object     value;    //Value stored in this node
       private OneWayNode next;     //Reference to next node
    
       private OneWayNode()
       {
          value = null;
          next = null;
       }

       private OneWayNode(Object value)
       {
          this.value = value;
          next = null;
       }

       private OneWayNode(Object value, OneWayNode next)
       {
          this.value = value;
          this.next = next;
       }
   }

}



