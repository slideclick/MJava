import java.util.Scanner;

public class Program6_2
{
	public static void main(String[] args)
	{
		// number of disks and the needle names
	   int n;
	   String beginNeedle  = "A",
			    middleNeedle = "B",
			    endNeedle    = "C";

		// the keyboard input stream
		Scanner keyIn = new Scanner(System.in);

	   // prompt for n and solve the puzzle for n disks
	   System.out.print("Enter the number of disks: ");
	   n = keyIn.nextInt();

	   System.out.println("The solution for n = " + n);
	   hanoi(n, beginNeedle, endNeedle, middleNeedle);
	}
	
	// move n disks from initNeedle to endNeedle, using tempNeedle
	// for intermediate storage of the disks 
	public static void hanoi(int n, String initNeedle,
								  	 String endNeedle, String tempNeedle)
	{
	   // stopping condition: move one disk
	   if (n == 1)
	      System.out.println("move " + initNeedle + " to " +
	           				  endNeedle);
	   else
	   {
	      // move n-1 disks from initNeedle to
			// tempNeedle using endNeedle for temporary storage
	      hanoi(n-1,initNeedle,tempNeedle,endNeedle);
	
	      // move largest disk to endNeedle
	      System.out.println("move " + initNeedle + " to " +
									 endNeedle);
	
	      // move n-1 disks from tempNeedle to
			// endNeedle using initNeedle for temporary storage
	      hanoi(n-1,tempNeedle,endNeedle,initNeedle);
	   }
	}
}

/*
Run:

Enter the number of disks: 3
The solution for n = 3
Move A to C
Move A to B
Move C to B
Move A to C
Move B to A
Move B to C
Move A to C
*/
