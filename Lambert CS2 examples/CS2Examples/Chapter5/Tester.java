public class Tester{
   public static int DEFAULT_CAPACITY = 3;
   public static void main(String[] args){
      Object[] array = {"hi", "there", "Mary"};          // Set up array.
      int logicalSize = 3;

      array = insertItem(array, logicalSize, DEFAULT_CAPACITY, 0, "Jack");  // Insert name.
      logicalSize++;
      array = insertItem(array, logicalSize, DEFAULT_CAPACITY, 1, "says");  // Insert name.
      logicalSize++;

      System.out.println(array.length);                  // Display physical
      for (int i = 0; i < logicalSize; i++)              // size and 
         System.out.print(array[i] + " ");             // contents.
   }

   // Returns a resized array if it needs to be grown or shrunk
   // or the original array if no change is necessary.
   static Object[] resizeIfNeeded(Object[] array, int logicalSize,
                                    int DEFAULT_CAPACITY){
      if (logicalSize == array.length){                        // Increase
         Object[] tempArray = new Object[array.length * 2];   
         for (int i = 0; i < logicalSize; i++)                
            tempArray[i] = array[i]; 
         return tempArray;
      }else if (logicalSize == array.length / 4 && 
                logicalSize > DEFAULT_CAPACITY){               // Decrease
         Object[] tempArray = new Object[array.length / 2];   
         for (int i = 0; i < logicalSize; i++)                
            tempArray[i] = array[i]; 
         return tempArray;
      }else                                                    // No change
         return array;
   }

   // Returns array with new item inserted at target index position.
   // Note: does not modify array's logical size.
   static Object[] insertItem(Object[] array, int logicalSize,
                              int DEFAULT_CAPACITY, int targetIndex,
                              Object newItem){
      array = resizeIfNeeded(array, logicalSize, DEFAULT_CAPACITY);
      for (int i = logicalSize; i > targetIndex; i--)   
         array[i] = array[i - 1];                       
      array[targetIndex] = newItem;
      return array;                     
   }

}
