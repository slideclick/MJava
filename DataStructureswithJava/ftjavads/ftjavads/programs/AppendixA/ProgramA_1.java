// main application class
public class ProgramA_1
{
	public static void main(String[] args)
	{
		int[] intArr = {35, 20, 50, 5, 40, 20, 15, 45};
		int i;

		// scan first n-1 positions in the array where n = intArr.length
		// call maxFirst() to place largest element from the unsorted tail 
		// of the list into position i
		for (i = 0; i < intArr.length-1; i++)
			maxFirst(intArr, i);

		// display the sorted array;
		for (i = 0; i < intArr.length; i++)
			System.out.print(intArr[i] + "  ");
		System.out.println();
	}

	public static void maxFirst(int[] arr, int start)
	{
		// maxValue and maxIndex are the value and location of the
		// largest element that is identified during a scan of the array. 
		int maxValue = arr[start], maxIndex = start, temp;
	
		// scan the tail of the list beginning at index start+1 and update both
		// maxValue and maxIndex so that we know the value and location
		// of the largest element
		for (int i = start+1; i < arr.length; i++)
			if (arr[i] > maxValue)
			{
				maxValue = arr[i];
				maxIndex = i;
			}
		
		// exchange arr[start] and arr[maxIndex]
		temp = arr[start];
		arr[start] = arr[maxIndex];
		arr[maxIndex] = temp;
	}
}

/*
Run:

50  45  40  35  20  20  15  5
*/
