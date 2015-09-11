import java.util.*;

public class Algorithms {

   public static long comparisons = 0,
                      exchanges   = 0,
                      runningTime = 0;

   public static void selectionSort(int[] array){
      comparisons = exchanges = 0;
      Date d1 = new Date();
      for (int i = 0; i < array.length - 1; i++){
         int minIndex = i;
         for (int j = i + 1; j < array.length; j++){
            comparisons++;
            if (array[j] < array[minIndex])
               minIndex = j;
         }
         if (minIndex != i)
            swap(array, minIndex, i);
      }
      Date d2 = new Date();
      runningTime = d2.getTime() - d1.getTime();
   }

   public static void bubbleSort(int[] array){
      comparisons = exchanges = 0;
      boolean swapped = false;
      Date d1 = new Date();
      for (int i = 0; i < array.length - 1; i++){
         for (int j = 0; j < array.length - i - 1; j++){
            comparisons++;
            if (array[j] > array[j + 1]){
               swap(array, j, j + 1);
               swapped = true;
            }
         }
         if (!swapped)
            break;
      }
      Date d2 = new Date();
      runningTime = d2.getTime() - d1.getTime();
   }

   public static void insertionSort(int[] array){
      comparisons = exchanges = 0;
      Date d1 = new Date();
      for (int i = 1; i < array.length; i++){
         int itemToInsert = array[i];
         int j = i - 1;
         while (j >= 0){
            comparisons++;
            if (itemToInsert < array[j]){
               exchanges++;
               array[j + 1] = array[j];
               j--;
            }
            else
               break;
         }
         exchanges++;
         array[j + 1] = itemToInsert;
      }
      Date d2 = new Date();
      runningTime = d2.getTime() - d1.getTime();
   }

   public static int linearSearch(int target, int[] array, int size){
      comparisons = exchanges = 0;
      for (int i = 0; i < size; i++){
         comparisons++;
         if (array[i] == target)
            return i;
      }
      return -1;
   }

   public static int binarySearch(int target, int[] array, int size){
      comparisons = exchanges = 0;
      int low = 0, middle = 0, high = size - 1;
      while (low <= high){
         middle = (low + high) / 2;
         comparisons++;
         if (array[middle] == target)
            return middle;
         else if (array[middle] < target)
            low = middle + 1;
         else
            high = middle - 1;
      }
      return - 1;
   }

   private static void swap(int[] array, int i, int j){
      exchanges++;
      int temp = array[i];
      array[i] = array[j];
      array[j] = temp;
   }

   public static int[] getRandomArray(int size, boolean duplicates){
      int[] array = new int[size];
      int i = 0;
      while (i < size){
         int value = 1 + (int)(Math.random() * size);
         if (duplicates || (linearSearch(value, array, i) == -1)){
            array[i] = value;
            i++;
         }
      }
      return array;
   }

   public static String toString(int[] array){
      String str = "";
      for (int i = 0; i < array.length; i++){
         str += array[i] + " ";
         if (i != 0 && i % 10 == 0)
            str += "\n";
      }
      return str;
   }

}
