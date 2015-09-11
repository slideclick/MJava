// LinkedQueue
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;        
import java.io.Serializable;

public class LinkedQueue extends AbstractQueue 
                         implements Queue, Cloneable {

   private OneWayNode head;      //head node in the queue
   private OneWayNode tail;      //tail node in the queue
    
   public LinkedQueue()
   /*
    * Instantiates a new queue
    *
    * Pre:  none
    * Post: head = null, tail = null
    */
   {
      super();
      head = null;
      tail = null;
   }

   public LinkedQueue (Collection col)
   /*
    * Instantiates a new queue with the contents of a collection.
    * The first item in the collection will be at the head of the queue. 
    *
    * Pre:  col is not null
    * Post: head = null if collection empty else head refers to head of queue
    *       tail = null if collection empty else tail refers to tail of queue
    */
   {
      super();
      
      if (col == null)
         throw new IllegalArgumentException("Collection is null");
               
      head = null;
      tail = null;
      appendIterator(col.iterator());
   }

   public LinkedQueue (Iterator iter)
   /*
    * Instantiates a new queue with the contents of an iterator.
    * The first item in the interator will be at the head of the queue. 
    *
    * Pre:  iter is not null
    * Post: head = null if collection empty else head refers to head of queue
    *       tail = null if collection empty else tail refers to tail of queue
    */
   {
      super();
      
      if (iter == null)
         throw new IllegalArgumentException("Iterator is null");
               
      head = null;
      tail = null;
      appendIterator (iter);
   }

   private void appendIterator (Iterator iter)
   {
      Object next;
      
      if (iter.hasNext() && tail == null){
         next = iter.next();
         
         if (next == null)
            throw new IllegalArgumentException 
            ("Trying to place a null item in a queue");
         
         head = new OneWayNode (next);
         tail = head;
         size++;
      }
      while (iter.hasNext()){
         next = iter.next();
         
         if (next == null)
            throw new IllegalArgumentException 
            ("Trying to place a null item in a queue");
         
         tail.next = new OneWayNode (next);
         tail = tail.next;
         size++;
      }
   }
   
   public void clear()
   /*
    * Clears the queue
    *
    * Pre:  none
    * Post: head = null, tail = null
    * Ret:  void
    */
   {
      super.clear();
      head = null;
      tail = null;
   }

   public Object clone(){
      return new LinkedQueue (iterator());
   }
    
   public Object dequeue(){
      if (size == 0)
         throw new NoSuchElementException ("Trying to dequeue an empty queue");

      Object item = head.value;
      head = head.next;
      if (head == null)
         tail = null;
      size--;
      modCount++;
      return item;
   }

   public void enqueue(Object item){
      if (item == null)
         throw new IllegalArgumentException 
         ("Trying to push null onto the queue");
         
      OneWayNode node = new OneWayNode (item);
      if (size == 0)
         head = node;
      else
         tail.next = node;
      tail = node;  
      size++;
      modCount++;
   } 
   
   public Iterator iterator()
   // The iterator returns items in their queue order
   {
      return new InnerIter();
   }

   public Object peek(){
      if (size == 0)
         throw new NoSuchElementException ("Trying to peek at an empty queue");
      return head.value;
   }

//************************** Inner Classes ********************************

   private class InnerIter implements Iterator{
    
      private OneWayNode curPos;
      private int expectedModCount = modCount;

        
      private InnerIter()
      {
         curPos = head;
      }
        
      public boolean hasNext()
      {
         return curPos != null;
      }
        
      public Object next(){
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
   
   private class OneWayNode implements Serializable {
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



