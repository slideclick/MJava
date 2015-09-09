import ds.util.Bag;
import ds.util.Arrays;
import java.util.Scanner;

public class Program8_1
{
	public static void main(String[] args)
	{
		// keyboard input stream and input string
		Scanner keyIn = new Scanner(System.in); 
		String str = "";

		// Bag objects hold string characters
		Bag<Character> bagA, bagB;

		// Character object for char value in the input string
		Character ch;

		// flag used to remove duplicates from bagA
		boolean foundDuplicate;

		// prompt for input string
		System.out.print("Enter a string: ");
		str = keyIn.next();

		// create the collections with capacity str.length()
		bagA = new Bag<Character>(str.length());
		bagB = new Bag<Character>(str.length());

		// add characters from the string to bagA
		for (int i = 0; i < str.length(); i++)
			bagA.add(str.charAt(i));

		// use grab() to fetch a character from bagA; add add it to
		// bagB and then remove all occurrences of the character
		// from bagA; continue this process until bagA is empty
		while (!bagA.isEmpty())
		{
			// remove a random character from bagA and add to bagB
			ch = bagA.grab();
			bagB.add(ch);

			// remove all occurrence of target = chObj from bagA
			do
				foundDuplicate = bagA.remove(ch);
			while (foundDuplicate);
		}

		// create array of Object references corresponding to
		// elements in bagB. sort array and output its values using
		// static methods sort() and toString(arr)
		Object[] objArr = bagB.toArray();
		Arrays.sort(objArr);
		System.out.println("Sorted letters: " +
		                   Arrays.toString(objArr));
	}
}

/*
Run:

Enter a string: mississippi
Sorted letters: [i, m, p, s]
*/
