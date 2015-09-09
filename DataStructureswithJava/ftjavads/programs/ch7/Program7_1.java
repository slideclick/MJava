import java.util.Random;

import ds.util.Arrays;
import ds.time.Timing;

public class Program7_1
{
	// types of sorts we will test
	enum Sorts {mergesort, quicksort, insertionsort};
	
	public static void main(String[] args)
	{
		final int SIZE = 75000;
	   Integer[] arr1 = new Integer[SIZE],
	   			 arr2 = new Integer[SIZE],
	   			 arr3 = new Integer[SIZE];
		int rndNum, i;
		Random rnd = new Random();

	   // load each array with the same sequence of SIZE
	   // random numbers
		// in the range 0 to 999999
	   for(i=0; i < SIZE; i++)
		{
			rndNum = rnd.nextInt(1000000);
			arr1[i] = arr2[i] = arr3[i] = rndNum;
		}

		// call timeSort() with each sort type
		timeSort(arr1, Sorts.mergesort, "Merge sort");
		timeSort(arr2, Sorts.quicksort, "Quick sort");
		timeSort(arr3, Sorts.insertionsort, "Insertion sort");
	}

	// output the first and last 3 elements in a sorted array
	public static void outputFirst_Last(Object[] arr)
	{
		// capture array size in n
		int i, n = arr.length;

		// output first 3 elements and last 3 elements
		for(i=0;i < 3;i++)
			System.out.print(arr[i] + "  ");
		System.out.print(". . .  ");
		for(i= n-3; i < n; i++)
			System.out.print(arr[i] + "  ");
		System.out.println();
	}

	// post the time with a description of the sort type
	public static <T extends Comparable<? super T>> void 
	timeSort(T[] arr, Sorts sortType, String sortName)
	{
		//create Timing object t and set before starting sort
		Timing t = new Timing();
		double timeRequired;

		t.start();

		// execute the kind of sort specified by sortType
	   switch(sortType)
		{
				case mergesort:		Arrays.sort(arr);
											break;
	      	case quicksort:		Arrays.quicksort(arr);
											break;
	      	case insertionsort:	Arrays.insertionSort(arr);
											break;
		}

		// stop timing and capture the elapsed time for the sort
		timeRequired = t.stop();

		// display output with the sort type and time
		outputFirst_Last(arr);
		System.out.print("   " + sortName + " time is " +
							  timeRequired + "\n\n");
	}
}

/*
Run:

26  38  47  . . .  999980  999984  999984  
   Merge sort time is 0.109

26  38  47  . . .  999980  999984  999984  
   Quick sort time is 0.078

26  38  47  . . .  999980  999984  999984  
   Insertion sort time is 100.611
*/