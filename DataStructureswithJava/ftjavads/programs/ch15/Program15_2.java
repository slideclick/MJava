import java.util.Random;
import ds.util.Arrays;

public class Program15_2
{
	public static void main(String[] args)
	{
		// array to hold the data that is sorted
		int[] arr = new int[50];
		Random rnd = new Random();
		int i;

		// initialize array with 50 random numbers in
		// range 0 - 99999
		for (i = 0; i < 50; i++)
			arr[i] = rnd.nextInt(100000);

		// apply the radix sort and output the sorted array
		Arrays.radixSort(arr, 5);
		displayArray(arr);
	}

	private static void displayArray(int[] arr)
	{
		int i, j, strnLength;
		String s, strn;

		for (i=0; i < arr.length; i++)
		{
			// represent value of arr[i] as a string
			strn = String.valueOf(arr[i]);
			// capture the length of strn
			strnLength = strn.length();

			s = "";
			// justify strn in a field of 8 print positions
			for (j=0;j < 8-strnLength;j++)
				s += " ";
			s += strn;

			// output the justified integer value
			System.out.print(s);
			if ((i+1) % 6 == 0) 	// newline every 6 numbers
				System.out.println();
		}

		System.out.println();
	}
}

/*
RUN

    2554    3097    5231    6876    8539   12446
   16483   20040   23202   24353   24758   25996
   28922   29730   30672   32032   32198   32261
   36705   36867   47340   47688   51547   53617
   54797   55577   56055   59553   61588   65289
   65465   68416   68935   71586   73017   77119
   80185   80659   81371   83443   87678   88138
   90076   90717   93637   94948   95470   96984
   97332   98616
 */
