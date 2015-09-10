import java.util.Scanner;
import ds.util.ALStack;

public class Program14_1
{
	public static void main(String[] args)
	{
		int num, b;	// decimal number and base
		int i;			// loop index

		// create scanner for keyboard input
		Scanner keyIn = new Scanner(System.in);

		for (i = 1; i <= 4; i++)
		{
			// prompt for number and base
			System.out.print("Enter a decimal number: ");
			num = keyIn.nextInt();
			System.out.print("Enter a base (2 to 16): ");
			b = keyIn.nextInt();

			System.out.println(" " + num + " base " + b +
							 	 " is " + baseString(num, b));
		}
	}

	public static String baseString(int num, int b)
	{
		// digitChar.charAt(digit) is the character that represents
		// the digit, 0 <= digit <= 15
		String digitChar = "0123456789ABCDEF", numStr = "";

		// stack holds the base-b digits of num
		ALStack<Character> stk = new ALStack<Character>();

		// extract base b digits right to left and push on stack
		do
		{
			// push right-most digit on the stack
			stk.push(digitChar.charAt(num % b));
			num /= b;				// remove right-most digit from num
		} while (num != 0);		// continue until all digits found

		while (!stk.isEmpty())		// flush the stack
		{
			// pop stack and add digit on top of stack to numStr
			numStr += stk.pop().charValue();
		}

		return numStr;
	}
}

/*
Run:

Enter a decimal number: 27
Enter a base (2 to 16): 2
 27 base 2 is 11011
Enter a decimal number: 300
Enter a base (2 to 16): 16
 300 base 16 is 12C
Enter a decimal number: 75
Enter a base (2 to 16): 8
 75 base 8 is 113
Enter a decimal number: 10
Enter a base (2 to 16): 3
 10 base 3 is 101
*/
