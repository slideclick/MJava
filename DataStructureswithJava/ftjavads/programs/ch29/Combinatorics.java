import ds.util.Arrays;

public class Combinatorics
{
	public static int comm(int n, int k)
	{
		if (n == k || k == 0)					// stopping condition
			return 1;
		else if (k == 1)
			return n;								// stopping condition
		else
			// recursive step
			return comm(n-1,k) + comm(n-1,k-1);
	}

	// computation of C(n,k) using top down dynamic programming
	// to avoid redundant recursive method calls
	public static int commDyn(int n, int k, int[][] commMat)
	{
		int returnValue;

		// check if value is already computed
		if (commMat[n][k] >= 0)
			return commMat[n][k];

		if (n == k || k == 0)
			returnValue = 1;
		else if (k == 1)
			returnValue = n;
		else
			// carry out the recursive step
			returnValue =
				commDyn(n-1,k,commMat) + commDyn(n-1,k-1,commMat);

		// before returning, assign value to the matrix
		commMat[n][k] = returnValue;

		return returnValue;
	}

	// computation of C(n,k) using bottom-up dynamic programming
	public static int commDynB(int n, int k)
	{
		// create an n+1 by k+1 matrix
		int[][] commMat = new int[n+1][k+1];
		int i, j;

		// rows range from 0 through n
		for (i=0; i <= n; i++)
			// only generate columns 0 through k
			for (j = 0; j <= Math.min(i,k); j++)
				// commMat[i][j] = 1 when j == 0 or j == i
				if (j == 0 || i == j)
					commMat[i][j] = 1;
				else
					commMat[i][j] =
						commMat[i-1][j-1] + commMat[i-1][j];

		// return the entry commMat(n,k)
		return commMat[n][k];
	}

	public static void permutation(Object[] permList)
	{
		permute(permList, 0);
	}

	private static void permute(Object[] permList, int index)
	{
		int i, j, arrSize = permList.length;
		Object temp;

		if (index == arrSize-1)
			// display the permutation
			System.out.println(Arrays.toString(permList));
		else
		{
			Object[] newPermList = new Object[arrSize];
			for (i = 0; i < arrSize; i++)
				newPermList[i] = permList[i];

			// find all permutations over the range [index, arrSize)
			permute(newPermList, index+1);

			// exchange permList[index] with permList[i]
			// for i=index+1 to the end of the array and
			// find all permutations
			for (i=index+1; i < arrSize; i++)
			{
				temp = permList[i];
				permList[i] = permList[index];
				permList[index] = temp;

				newPermList = new Object[arrSize];
				for (j = 0; j < arrSize; j++)
					newPermList[j] = permList[j];

				permute(newPermList, index+1);
			}
		}
	}

	// recursive computation of Fibonacci number n
	public static int fib(int n)
	{
		if (n <= 1)								// stopping conditions
			return n;
		else
			return fib(n-1) + fib(n-2);	// recursive step
	}

	// computation of the nth Fibonacci number using top down
	// dynamic programming to avoid redundant recursive
	// method calls
	public static int fibDyn(int n, int[] fibList)
	{
		int fibValue;

		// check for a previously computed result and return
		if (fibList[n] >= 0)
			return fibList[n];

		// otherwise execute the recursive algorithm to obtain the
		// result

		// stopping conditions
		if (n <= 1)
			fibValue = n;
		else
			// recursive step
			fibValue = fibDyn(n-1, fibList) + fibDyn(n-2, fibList);

		// store the result and return its value
		fibList[n] = fibValue;

		return fibValue;
	}

}
