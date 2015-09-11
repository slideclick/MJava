import java.util.*;                                //Contains exception classes
import java.io.Serializable;                       //The Serializable interface

public class LinkedListPT implements ListPT, Serializable {

   private TwoWayNode head;                                     //Sentinel node
   private int size;                              //Number of items in the list
   private int modCount;           //Number of times the list has been modified

   
   // Constructor -- sets up circular linked structure with a dummy header node
   public LinkedListPT()
   {
      head = new TwoWayNode(null, null, null);
      head.next = head;
      head.previous = head; 
      size = 0;
      modCount = 0;
   }
   
   //Adds item to list at index
   public void add (int index, Object item)
   {
      if (item == null)
         throw new IllegalArgumentException ("Cannot insert null");
      if (index < 0 || index > size)
         throw new IndexOutOfBoundsException 
         ("Index " + index + " out of range");

      //Locate node before insertion point   
      TwoWayNode nodeBefore = getNode (index - 1);

      //Create new node and link it into the list
      TwoWayNode newNode = new TwoWayNode (item, nodeBefore, 
		                                     nodeBefore.next);
      nodeBefore.next.previous = newNode;
      nodeBefore.next = newNode;

      size++;
      modCount++;
   }
   
   public boolean contains (Object item){
      // Traverse the links looking for item
      // and return true if it is found
      TwoWayNode probe = head.next;
      while (probe != head)
         if (item.equals (probe.value)) 
            return true;
         else
            probe = probe.next;

        // Item not found so return false
        return false;
    }

   public boolean isEmpty(){
      return size() == 0;
   }
   
   public boolean isFull(){
      return false;
   }
   
   public Object get(int index){
      if (index < 0 || index >= size)
         throw new IllegalArgumentException ("Index " + index + 
			                                    " out of range");
      return getNode(index).value;
   }
   
   // Helper method that returns the ith node
   private TwoWayNode getNode (int i){
      TwoWayNode ithNode = head;
      for (int k = -1; k < i; k++)
         ithNode = ithNode.next;
      return ithNode;
   }         

   public ListIteratorPT iterator()   //Returns an iterator for this collection
   {
      return new ListIter();
   }

   public Object peek(){
      if (isEmpty())
         throw new IllegalStateException ("Peeking at an empty list");
      return head.next.value;
   }
 
   //Removes the node and item at the indicated index
   public Object remove(int index){
      if (index < 0 || index >= size)
         throw new IndexOutOfBoundsException 
         ("Index " + index + " out of range");

      //Locate the node before the one that is being deleted
      TwoWayNode nodeBefore = getNode(index - 1); 

      //Remember the node being removed
      TwoWayNode nodeRemoved = nodeBefore.next;

      //Link around the removed node
      nodeRemoved.next.previous = nodeBefore;      
      nodeBefore.next = nodeRemoved.next;   

      size--;     
      modCount++;                                   
      return nodeRemoved.value;                       
   }
   
   public Object set(int index, Object newItem){
      if (index < 0 || index >= size)
         throw new IllegalArgumentException ("Index " + index + 
			                                    " out of range");
      if (newItem == null)
         throw new IllegalArgumentException("Cannot insert null");
      TwoWayNode ithNode = getNode(index);
      Object oldItem = ithNode.value;
      ithNode.value = newItem;
      return oldItem;
   }
   
   public int size(){
      return size;
   }
   
   public String toString(){
      String str = "";

      // Traverse the array concatenating the string
      // representations of the items
      for (TwoWayNode probe = head.next; probe != head; probe = probe.next)
         str += probe.value.toString() + " ";

      return str;
   }

//================= inner class TwoWayNode ========================

   private class TwoWayNode extends Object{

      private Object     value;    //Value stored in this node
      private TwoWayNode next;     //Reference to next node
      private TwoWayNode previous; //Reference to previous node
    
      private TwoWayNode(){
         value = null;
         previous = null;
         next = null;
      }

      private TwoWayNode(Object value){
         this.value = value;
         previous = null;
         next = null;
      }

      private TwoWayNode(Object value, 
                         TwoWayNode previous, 
                         TwoWayNode next){
         this.value = value;
         this.previous = previous;
         this.next = next;
      }
   }

//================= inner class ListIter ========================

   private class ListIter implements ListIteratorPT, Serializable{

      private TwoWayNode curPos;        
        //Current position indicator with respect to the backing store
        //Points at the node which would be returned by next
        //The current position is considered to be immediately before this node
        //If curPos == head then at end of list
        //If curPos.previous == head then at beginning of list
                                                  
      private TwoWayNode lastItemPos; 
        //Points at the last item returned by next or previous
        //Equals null initially and after add and remove

      private int expectedModCount;     
        //This iterator's notion of modCount

      private ListIter()                                          //Constructor
      {
         curPos = head.next;             //Points at first item if there is one
         lastItemPos = null;
         expectedModCount = modCount;
      }                                  
        
      // Methods for navigation

      public boolean hasNext()          //Returns true if there are items after
      {                                                  //the current position
         return curPos != head;                      
      }
        
      public boolean hasPrevious()     //Returns true if there are items before
      {                                                  //the current position
         return curPos.previous != head;    
      }

      public Object next(){                         //Returns the next item.
         if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
         if (!hasNext())
            throw new NoSuchElementException("There are no more elements");   
                     
         lastItemPos = curPos;   //Remember the index of the last item returned
         curPos = curPos.next;                   //Advance the current position
         return lastItemPos.value;
      }
      
      public Object previous(){                     //Returns the previous item
         if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
         if (!hasNext())
            throw new NoSuchElementException("There are no more elements");   
                     
         lastItemPos = curPos.previous;   //Remember the index of the last item
                                                                     //returned
         curPos = curPos.previous;         //Move the current position backward
         return lastItemPos.value;
      }
      
      // Methods for modifications

      public void add(Object o)         //Adds object o at the current position
      {                           
         if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
         if (o == null)
            throw new IllegalArgumentException("Cannot insert null");
         
         //Create new node for object o
         TwoWayNode newNode = new TwoWayNode(o, curPos.previous, curPos);

         //Link the new node into the list
         curPos.previous.next = newNode;
         curPos.previous = newNode;   

         //curPos does not change
 
         size++;
         modCount++;
         expectedModCount++;
         lastItemPos = null;    //Block remove and set until after a successful
                                                             //next or previous
      }
   
      public void remove()           //Removes the most recently retrieved item
      {                         
         if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
         if (lastItemPos == null)
            throw new IllegalStateException 
            ("There is no established item to remove.");   

         if (lastItemPos == curPos)      //If item being removed is the same as 
            curPos = curPos.next;                 //curPos, then advance curPos

         //Link around the item being removed
         lastItemPos.previous.next = lastItemPos.next;
         lastItemPos.next.previous = lastItemPos.previous;

         size--;
         modCount++;
         expectedModCount++;
         lastItemPos = null;    //Block remove and set until after a successful
                                                             //next or previous
      }

      
      public void set(Object o)    // Replaces the most recently retrieved item
      {
         //This is left as an exercise 
         //Throw an exception if expectedModCount != modCount
         //Throw an exception if lastItemPos == null
         //Throw an exception if object o is null
         //Replace the value of the node at lastItemPos 
         //Do not change modCount, expectedModCount, lastItemPos, curPos
      }
   }
}  
