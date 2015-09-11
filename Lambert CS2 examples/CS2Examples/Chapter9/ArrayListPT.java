import java.util.*;                                //Contains exception classes
import java.io.Serializable;                       //The Serializable interface

public class ArrayListPT implements ListPT, Serializable {

   private static int DEFAULT_CAPACITY = 100;            //Maximum size of list
   protected Object items[];                               //The array of items
   protected int size;                           // Number of items in the list
   protected int modCount;         //Number of times the list has been modified

   public ArrayListPT()                                   //Default constructor
   {
      items = new Object[DEFAULT_CAPACITY]; 
      size = 0;
      modCount = 0;
   }
   
   public void add (int index, Object item) {          //Adds a new item at the
                                                      //given index if possible
      if (item == null)
         throw new IllegalArgumentException ("Cannot insert null");
      if (isFull())                          //If list full, throw an exception
         throw new IllegalStateException ("List is full"); 
      if (index < 0 || index > size)      //If out of range, throw an exception
         throw new IndexOutOfBoundsException 
         ("Index " + index + " out of range");

      for (int i = size; i > index; i--)                 //Shift items to right
            items[i] = items[i - 1];
      items[index] = item;                                    //Insert new item
      size++;         
      modCount++;
   }

   public boolean contains (Object item){
      // Traverse the array looking for item
      // and return true if it is found
      for (int i = 0; i < size; i++)
         if (item.equals (items[i])) return true;

        // Item not found so return false
        return false;
   }

   public Object get(int index){
      if (index < 0 || index >= size)
         throw new IllegalArgumentException ("Index " + index + " out of range");
      return items [index];
   }

   public boolean isEmpty(){
      return size() == 0;
   }
   
   public boolean isFull(){
      return size() == items.length;
   }
   
   public ListIteratorPT iterator()      //Returns an iterator for this collection
   {
      return new ListIter();
   }

   public Object peek(){
      if (isEmpty())
         throw new IllegalStateException ("Peeking at an empty list");
      return items[0];
   }
 
   public Object remove(int index){
      if (index < 0 || index >= size)
         throw new IllegalArgumentException ("Index " + index + " out of range");
    // Remember the item being removed
      Object theItem = items[index];

      // Close up the array around the item being removed
      for (int k = index; k < size - 1; k++)
         items[k] = items[k + 1];

      size--;
      return theItem;
   }

   public Object set(int index, Object newItem){
      if (index < 0 || index >= size)
         throw new IllegalArgumentException ("Index " + index + " out of range");
      if (newItem == null)
         throw new IllegalArgumentException("Cannot insert null");
      Object oldItem = items[index];
      items[index] = newItem;
      return oldItem;
   }

   public int size(){
      return size;
   }
   
   public String toString(){
      String str = "";

      // Traverse the array concatenating the string
      // representations of the items
      for (int i = 0; i < size; i++)
         str += items[i].toString() + " ";

      return str;
   }

//================= inner class ListIter ========================

   private class ListIter implements ListIteratorPT, Serializable{
    
      private int curPos;     
        //Current position indicator with respect to the backing list
        //Equals i if immediately before the item at index i
        //Equals size() if after the last item

      private int lastItemPos;                    
        //Equals index of last item returned by next or previous 
        //Equals -1 initially and after add and remove 

      private int expectedModCount;        
        //This iterator's notion of modCount

      private ListIter()                                          //Constructor
      {
         curPos = 0;
         lastItemPos = -1;
         expectedModCount = modCount;
      }                                   
        
      // Methods for navigation
      
      public boolean hasNext()          //Returns true if there are items after 
      {                                                  //the current position
         return curPos < size;                
      }
        
      public boolean hasPrevious()     //Returns true if there are items before 
      {                                                  //the current position
         return curPos > 0;                     
      }
        
      public Object next(){                             //Returns the next item
         if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
         if (!hasNext())
            throw new NoSuchElementException("There are no more elements");   
                     
         lastItemPos = curPos;   //Remember the index of the last item returned
         curPos++;                               //Advance the current position
         return items[lastItemPos]; 
      }
      
      public Object previous(){                     //Returns the previous item
         if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
         if (!hasPrevious())
            throw new NoSuchElementException("There are no more elements");   
                     
         lastItemPos = curPos - 1;        //Remember the index of the last item
                                                                     //returned
         curPos--;                         //Move the current position backward
         return items[lastItemPos];
      }
     
      // Methods for modifications

      public void add(Object o)         //Adds object o at the current position
      {                           
         if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
         ArrayListPT.this.add(lastItemPos, o);        //Call the backing list's 
                                    //add method, which will increment modCount
         curPos++; 
         expectedModCount++;               //Increment expectedModCount as well
         lastItemPos = -1;      //Block remove and set until after a successful
                                                             //next or previous
      }

      public void remove()           //Removes the most recently retrieved item
      { 
         if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
         if (lastItemPos == -1)
            throw new IllegalStateException 
            ("There is no established item to remove.");   

         ArrayListPT.this.remove(lastItemPos);        //Call the backing list's 
                                 //remove method, which will increment modCount
         expectedModCount++;               //Increment expectedModCount as well
         if (lastItemPos < curPos)       //If the item removed was obtained via
            curPos--;               //next, then move the current position back
         lastItemPos = -1;      //Block remove and set until after a successful
                                                             //next or previous
      }

      public void set(Object o)     //Replaces the most recently retrieved item
      { 
         if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
         if (lastItemPos == -1)
            throw new IllegalStateException 
            ("There is no established item to set.");   
         ArrayListPT.this.set(lastItemPos, o);        //Call the backing list's
                                                                   //set method 
         //Do not change modCount, expectedModCount, lastItemPos, curPos
      }
   }
}



