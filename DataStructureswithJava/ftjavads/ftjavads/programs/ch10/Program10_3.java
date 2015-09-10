import ds.util.LinkedList;
import java.util.Scanner;

public class Program10_3
{
	public static void main(String[] args)
	{
		String str;
		LinkedList<Character> charList =
				new LinkedList<Character>();
		Scanner keyIn = new Scanner(System.in);
		int i;
		char ch;

		// prompt user to enter a string that may include blanks
		// and punctuation marks
		System.out.print("Enter the string: ");
		str = keyIn.nextLine();

		// copy all of the letters as lowercase characters
		// to the linked list charList
		for (i = 0; i < str.length(); i++)
		{
			ch = str.charAt(i);

			if (Character.isLetter(ch))
				charList.addLast(Character.toLowerCase(ch));
		}

		// call isPalindrome() and use return value to designate
		// whether the string is or is not a palindrome
		if (isPalindrome(charList))
			System.out.println("'" + str + "' is a palindrome");
		else
			System.out.println("'" + str + "' is not a palindrome");
	}

	public static boolean isPalindrome(LinkedList<?> aList)
	{
		// check values at ends of list as long as list size > 1
		while (aList.size() > 1)
		{
			// compare values on opposite ends; if not equal,
			// return false
			if (aList.getFirst().equals(aList.getLast()) == false)
				return false;
	
			// delete the objects
			aList.removeFirst();
			aList.removeLast();
		}
	
		// if still have not returned, list is a palindrome
		return true;
	}
}

/*
Run 1:
Enter the string: A man, a plan, a canal, Panama
'A man, a plan, a canal, Panama' is a palindrome

Run 2:
Enter the string: Go hang a salami, I'm a lasagna hog
'Go hang a salami, I'm a lasagna hog' is a palindrome

Run 3:
Enter the string: palindrome
'palindrome' is not a palindrome
*/
