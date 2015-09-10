import ds.util.Bag;

public class Program8_2
{
	public static void main(String[] args)
	{
		final int PRIMELIMIT = 500;
		Bag<Integer> bag;

		// call sieve() and return the bag of primes
		bag = sieve(PRIMELIMIT);

		// list elements in the bag as an array
		// output primes in 6 spaces, 10 per line
		writePrimes(bag.toArray());
		System.out.println();
	}

	// output elements in the array in 6 spaces, 10 per line
	public static void writePrimes(Object[] arr)
	{
		String intStr;
		int count = 1, i;
		// initialize sb with 6 blanks
		StringBuffer sb = new StringBuffer("      ");

		for (i = 0; i < arr.length; i++)
		{
			// convert integer to a string
			intStr = arr[i].toString();

			// use replace() to place intStr in the string buffer.
			sb.replace(0, intStr.length(), intStr);

			// output string buffer as a string
			System.out.print(sb.toString());

			// every 10 elements output a newline
			if(count % 10 == 0)
				System.out.println();
			count++;
		}
	}

	public static Bag<Integer> sieve(int n)
	{
		int m, i;
		Bag<Integer> primeBag = new Bag<Integer>(n);

		// load the set with integers 2, 3, ..., n
		for (m = 2; m <= n; m++)
			primeBag.add(new Integer(m));

		// find the primes using the Sieve of Eratosthenes.
		// look at numbers from m = 2 to m * m > n (m <= sqrt(n))
		for (m = 2; m * m <= n; m++)
			// check is m is still in the set; if so remove all
			// multiples of m starting with 2*m.
			if(primeBag.contains(new Integer(m)))
			{
				// i sequences through successive multiples of m,
				// 2*m, 3*m, ...
				i = 2 * m;
				while (i <= n)
				{
					primeBag.remove(new Integer(i));
					// update i to the next multiple of m
					i += m;
				}
			}
		return primeBag;
	}
}

/*
Run:

2     3     5     7     11    13    17    19    23    29    
31    37    41    43    47    53    59    61    67    71    
73    79    83    89    97    101   103   107   109   113   
127   131   137   139   149   151   157   163   167   173   
179   181   191   193   197   199   211   223   227   229   
233   239   241   251   257   263   269   271   277   281   
283   293   307   311   313   317   331   337   347   349   
353   359   367   373   379   383   389   397   401   409   
419   421   431   433   439   443   449   457   461   463   
467   479   487   491   499
*/
