import ds.util.Heaps;
import ds.util.Greater;
import ds.util.Less;

public class Program22_1
{
	public static void main(String[] args)
	{
		// integer array used to create heaps arrA and arrB
		Integer[] intArr = {15, 29, 52, 17, 21, 39, 8},
					heapArrA = new Integer[intArr.length],
					heapArrB = new Integer[intArr.length];
		int i;

		// comparators to specify maximum or minimum heap
		Greater<Integer> greater = new Greater<Integer>();
		Less<Integer> less = new Less<Integer>();

		// load elements from intArr into heapArrA to form
		// a maximum heap and into heapArrB to form a minimum heap
		for (i = 0; i < intArr.length; i++)
		{
			Heaps.pushHeap(heapArrA, i, intArr[i], greater);
			Heaps.pushHeap(heapArrB, i, intArr[i], less);
		}

		// display the heapArrA
		System.out.println("Display maximum heap:");
		System.out.println(Heaps.displayHeap(heapArrA, 
		                    heapArrA.length, 2));

		// graphically display heapArrB before and after popHeap()
		Heaps.drawHeaps(heapArrB, heapArrB.length, 2);
		
		Integer minObj =
			Heaps.popHeap(heapArrB, heapArrB.length, less);
		System.out.println("\nMinimum value is " + minObj);
		
		// the index range is 0 to heapArrB.length-1
		Heaps.drawHeap(heapArrB, heapArrB.length-1, 2);
	}
}

/*
Run:

Display maximum heap:
          52
    21          39
 15    17    29     8

Minimum value is 8
*/
