import java.util.Random;
import java.util.Scanner;

import ds.util.DNode;
import ds.util.DNodes;

public class Program11_1 
{
	static Random rnd = new Random();

	public static void main(String[] args)
	{
		Scanner keyIn = new Scanner(System.in);
		
		String word, jumbleword;
		int numWords, i, j;
		
		// prompt for the number of words to enter
		System.out.print("How many words will you enter? ");
		numWords = keyIn.nextInt();
		
		for (i = 0; i < numWords; i++)
		{
			System.out.print("Word: ");
			word = keyIn.next();
			jumbleword = jumbleLetters(word);
			
			// output the word and its jumbled variation
			System.out.println("Word/Jumbled Word: " + word +
									 "   " + jumbleword);;
		}
	}

	public static String jumbleLetters(String word)
	{
		DNode<Character> header = new DNode<Character>();
		String jumbleword = "";
		
		// use rnd.nextInt(2) to determine if char is inserted
		// at the front (value = 0) or back (value = 1) of list
		for (int i = 0;  i < word.length();  i++)
			if (rnd.nextInt(2) == 0)
				// add at the front of the list
				DNodes.addBefore(header.next,word.charAt(i));
			else
				// insert at the back of the list
				DNodes.addBefore(header, word.charAt(i));
		
		// create the jumbled word and clear the list
		while (header.next != header)
		{
			jumbleword += header.next.nodeValue;
			DNodes.remove(header.next);
		}
		return jumbleword;
	}
}

/*
Run:

How many words will you enter? 3
Word: before
Word/Jumbled Word: before   erofeb
Word: java
Word/Jumbled Word: java   vjaa
Word: link
Word/Jumbled Word: link   knli
*/
