import java.util.*;                 //java.util contains the Iterator interface
                                                // and the Collection interface
import java.io.Serializable;

public class ArrayTiny implements Tiny, Serializable {
   
   public static int DEFAULT_CAPACITY = 100;           // Maximum size of array   
   private Object items[];                                  //An array of items
   private int size;                             //Number of items in the array
   private int modCount;          //Number of times the array has been modified

   public ArrayTiny()
   {
      items = new Object[DEFAULT_CAPACITY];   //Array's maximum capacity is 100
      size = modCount = 0;
   }
   
   public boolean add (Object item)       //Add a new item at the array's first
   {                                              //unused location if possible
      if (size == items.length)
         return false;
      else{
         items[size] = item;
         size++;
         modCount++;
         return true;
      }
   }

   public Object removeLast()                  //Remove the most recently added
   {                                                         //item if possible
      if (size == 0)
         return null;
      else{
         size--;
         modCount++;
         return items[size];
      }
   }

   public int size()             //This method helps the user keep track of the
   {                                             //number of items in the array
      return size;
   }

   public Collection collectionView()                         //See Section 6.6 
   {
      return null;
   }

   public Iterator iterator()         //Returns an iterator for this collection
   {
      return new TinyIterator();
   }

//================= inner class TinyIterator ============================

   //We implement iterators as inner classes. Iterators must implement the
   //Iterator interface defined in java.util.
   private class TinyIterator implements Iterator{
    
      private int curPos;               //Current position of the iterator with
                                                //respect to the backing array.
      private int expectedModCount;       //This iterator's notion of modCount
      private boolean removeIsOK;               //True whenever it is OK to use  
                                                           //the remove method.
      private TinyIterator()
      {
         curPos = 0;
         expectedModCount = modCount;
         removeIsOK = false;         //The remove method cannot be called until
      }                                                 //next has been called.
        
      public boolean hasNext()              //Returns true if some items in the 
      {                                       //backing collection have not yet
         return curPos < size;                              //been encountered.
      }
        
      public Object next(){                            //Returns the next item.
         if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
         if (!hasNext())
            throw new NoSuchElementException("There are no more elements");   
                     
         removeIsOK = true;          //It is now OK to use the remove method.
         curPos++;
         return items[curPos - 1];
      }
      
      public void remove()          //Removes the most recently retrieved item.
      {
         if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
         if (!removeIsOK)
            throw new IllegalStateException 
            ("Remove must be preceded by next");
         removeIsOK = false;         //The remove method cannot be called again
         modCount++;                              //until next is called again.
         expectedModCount++;
         //The rest of this method's code is left as an exercise
      }
   }
}   
