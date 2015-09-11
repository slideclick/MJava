// Utilities
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

/**
 * This class etc...
 */

public class Utilities extends Object {

    public static void assert (boolean condition, String message)
    {
        if (!condition) throw new RuntimeException ("\n" + message);
    } 


    public static Object[] expandArrayIfNeeded 
                    (Object oldArray[], int count){
    // If the array is full, double its capacity
        int size = oldArray.length;
        if (count < size)
            return oldArray;
        else{
            Object newArray[] = new Object[2*size];
            for (int i = 0; i <= count-1; i++) newArray[i] = oldArray[i];
            return newArray;
        }
    }

    public static Object[] expandArrayIfNeeded 
                    (Object oldArray[], int count,
                     int front, int rear){
    // If the array is full, double its capacity
        int i, j;
        int size = oldArray.length;
        if (count < size)
           return oldArray;
        else{
           Object newArray[] = new Object[2*size];
           if (front <= rear){
               j = 0;        
               for (i = front; i <= rear; i++){ 
                 newArray[j] = oldArray[i];
                 j++;
               }
           }else{
               j = 0;
               for (i = front; i < size; i ++){
                 newArray[j] = oldArray[i];
                 j++;
               }
               for (i = 0; i <= rear; i++){
                 newArray[j] = oldArray[i];
                 j++;
               }
           }
           return newArray;
        }
    }

    public static Object[] shrinkArrayIfNeeded 
                     (Object oldArray[], int count, int initCapacity){
    // If only 1/4 of the array will be in use after an item is removed,
    // shrink the array, but not below its initial capacity
        int size = oldArray.length;
        if (size < 2*initCapacity || (count - 1) > size/4)
            return oldArray;
        else{
            Object newArray[] = new Object[size/2];
            for (int i = 0; i <= count - 1; i++) newArray[i] = oldArray[i];
            return newArray;
        }
    }
    

    public static Object[] shrinkArrayIfNeeded 
                     (Object oldArray[], int count, int initCapacity,
                      int front, int rear){
    // If only 1/4 of the array will be in use after an item is removed,
    // shrink the array, but not below its initial capacity
        int i, j;
        int size = oldArray.length;
        if (size < 2*initCapacity || (count - 1) > size/4)
           return oldArray;
        else{
           Object newArray[] = new Object[size/2];
           if (front <= rear){
              j = 0;
              for (i = front; i <= rear; i++){ 
                 newArray[j] = oldArray[i];
                 j++;
              }
           }else{
              j = 0;
              for (i = front; i < size; i ++){
                 newArray[j] = oldArray[i];
                 j++;
              }
              for (i = 0; i <= rear; i++){
                 newArray[j] = oldArray[i];
                 j++;
              }
           }
           return newArray;
        }
    }
    
    public static void openHoleInArray (Object array[], int at, int last){
    // Open a hole in the array at the indicated position by shifting elements
    // down. "last" indicates the last occupied position in the array.
        for (int pos = last + 1; pos > at; pos--)
            array[pos] = array[pos - 1];
    }  
    
    public static void removeSlotFromArray 
                     (Object array[], int at, int last){
    // Remove a slot from the array at the indicated position by shifting
    // elements up. "last" indicates the last occupied position in the array.  
        for (int pos = at; pos < last; pos++)
            array[pos] = array[pos + 1];
    }
    
}
