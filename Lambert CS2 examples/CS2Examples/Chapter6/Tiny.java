import java.util.*;                 //java.util contains the Iterator interface
                                                // and the Collection interface

public interface Tiny {
   
   public boolean add (Object item);      //Add a new item at the array's first
                                                  //unused location if possible
      

   public Object removeLast();                 //Remove the most recently added
                                                             //item if possible

   public int size();            //This method helps the user keep track of the
                                                 //number of items in the array

   public Collection collectionView();                        //See Section 6.6 
   

   public Iterator iterator();        //Returns an iterator for this collection
   
}   
