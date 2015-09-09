import java.util.Scanner;

public class Program14_3
{
	public static void main(String[] args)
	{			
		// object used to evaluate postfix expressions
		PostfixEval exp = new PostfixEval();
		// postfix expression input
		String rpnExp;
		// for reading an expression
		Scanner keyIn = new Scanner(System.in);
		
		System.out.print("Enter the postfix expression: ");
		rpnExp = keyIn.nextLine();
		
		// assign the expression to exp
		exp.setPostfixExp(rpnExp);
		
		// call evaluate() in a try block in case an error occurs
		try
		{
			System.out.println("The value of the expression = " +
									  exp.evaluate() + "\n");
		}
		// catch block outputs the error
		catch (ArithmeticException ae)
		{
			System.out.println(ae.getMessage() + "\n");
		}
	}
}

/*
Run 1: (2 + 5)*3 - 8/3
	Enter the postfix expression: 2 5 + 3 * 8 3 / -
	The value of the expression = 19

Run 2: 2^3 + 1
	Enter the postfix expression: 2 3 ^ 1 +
	The value of the expression = 9

Run 4:
	Enter the postfix expression: 1 9 * /
	PostfixEval: Too many operators

Run 5:
	Enter the postfix expression: 2 3 5 +
	PostfixEval: Too many operands
*/
