import java.util.Random;
import java.text.DecimalFormat;
import ds.util.Arrays;
import ds.time.Timing;

public class Program4_1
{
   public static void main(String[] args)
   {
      final int ARRAY_SIZE = 100000, TARGET_SIZE = 50000;

      // arrays for the search
      int[] listSeq = new int[ARRAY_SIZE],
            listBin = new int[ARRAY_SIZE],
            targetList = new int[TARGET_SIZE];
      int i;

      // use Timing object t to compute times for each process
      Timing t = new Timing();
      double seqTime, binTime;

      // random number object
      Random rnd = new Random();

      // format real numbers with three decimal places
      DecimalFormat fmt = new DecimalFormat("#.000");

      // initialize the arrays with random numbers in the
      // range 0 to 999,999
      for (i = 0; i < ARRAY_SIZE; i++)
         listSeq[i] = listBin[i] = rnd.nextInt(1000000);

      // initialize targetList with random numbers in the
      // same range 0 to 999,999
      for (i=0;i < TARGET_SIZE; i++)
         targetList[i] = rnd.nextInt(1000000);

      // time the sequential search with elements from listSeq
      t.start();
      for (i = 0; i < TARGET_SIZE; i++)
         Arrays.seqSearch(listSeq,0,ARRAY_SIZE,targetList[i]);
      seqTime = t.stop();
      System.out.println("Sequential Search takes " +
      				fmt.format(seqTime) + " seconds.");

      // sort listBin
      Arrays.selectionSort(listBin);

      // time the binary search with elements from listBin
      t.start();
      for (i = 0; i < TARGET_SIZE; i++)
         Arrays.binSearch(listBin,0,ARRAY_SIZE,targetList[i]);
      binTime = t.stop();                  
      System.out.println("Binary Search takes " +
      				fmt.format(binTime) + " seconds.");

      System.out.println(
      		"Ratio of sequential to binary search time is " +
				fmt.format(seqTime/binTime));
   }
}

/*
Run:

Sequential Search takes 16.234 seconds.
Binary Search takes .016 seconds.
Ratio of sequential to binary search time is 1014.625
*/
