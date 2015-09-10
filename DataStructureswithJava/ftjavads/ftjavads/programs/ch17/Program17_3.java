import java.util.Scanner;

import ds.util.TNode;
import ds.util.BinaryTree;

public class Program17_3
{
	public static void main(String[] args)
	{
		// prompt for the RPN expression
		Scanner keyIn = new Scanner(System.in);
		String postfixExp;
		// root of the expression tree
		TNode<Character> root;

		System.out.print("Enter a postfix expresssion: ");
		postfixExp = keyIn.nextLine();

		// build the expression tree
		root = BinaryTree.buildExpTree(postfixExp);

		// display the tree
		System.out.println("Expression tree");
		System.out.println(BinaryTree.displayTree(root,1));

		// output the full parenthesized expression
		System.out.print("Fully parenthesized expression: ");
		System.out.println(BinaryTree.fullParen(root));
	}
}

/*
Run:

Enter a postfix expresssion: a d e - * b c / +
Expression tree
           +
   *           /
 a     -     b   c
     d   e

Fully parenthesized expression: ((a*(d-e))+(b/c))
*/
