import ds.time.Timing;

public class Program6_3
{
	public static void main(String[] args)
	{
		int fib_45;
		Timing timer = new Timing();
		double fibTime;

		// evaluate fibIter(45) using iterative method
		System.out.println("Value of fibIter(45) by iteration is " +
								 fibIter(45));

		// evaluate fib(45) using recursive method
		System.out.print("Value of fib(45) by recursion is ");

		// start/stop timing the recursive method
		timer.start();
		fib_45 = fib(45);
		fibTime = timer.stop();

		// output the value for fib(45) and time of computation
		System.out.println(fib_45);
		System.out.println(
			"   Time required by the recursive version is " +
			fibTime +  " sec");
	}

	// compute Fibonacci number n using recursion
	public static int fib(int n)
	{
		if (n <= 1)							// stopping conditions
			return n;
		else
			return fib(n-1) + fib(n-2);	// recursive step
	}

	// compute Fibonacci number n iteratively
	public static int fibIter(int n)
	{
		// integers to store previous two Fibonacci value 
		int oneback = 1, twoback = 0, current = 0;
		int i;
	
		// return is immediate for first two numbers
		if (n <= 1)
			current = n;
		else
			// compute successive terms beginning at 3
			for (i = 2; i <= n; i++)
			{
				current = oneback + twoback;
				twoback = oneback;	// update for next calculation
				oneback = current;
			}
	
		return current;			
	}
}

/*
Run:

Value of fib(45) by iteration is 1134903170
Value of fib(45) by recursion is 1134903170
   Time required by recursive version is 34.719 sec
*/
