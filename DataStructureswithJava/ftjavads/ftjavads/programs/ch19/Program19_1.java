import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ds.util.TreeSet;

public class Program19_1
{
	// keyboard input stream used by main() and spellChecker()
	static Scanner keyIn = new Scanner(System.in);

	public static void main(String[] args)
	{
		String fileName;

		// enter the file name for the document
		System.out.print("Enter the document to spell check: ");
		fileName = keyIn.next();

		// check the spelling
		spellChecker(fileName);
	}

	public static void spellChecker(String filename)
	{
		// sets storing the dictionary and the misspelled words
		TreeSet<String> dictionary = new TreeSet<String>(),
				          misspelledWords = new TreeSet<String>();
	
		Scanner dictFile = null, docFile = null;
	
		// create Scanner objects to input dictionary and document
		try
		{		
			// dictionary and document streams
			dictFile = new Scanner(new FileReader("dict.dat"));
			docFile = new Scanner(new FileReader(filename));
		}
		
		catch(FileNotFoundException fnfe)
		{
			System.err.println("Cannot open a file");
			System.exit(1);
		}
	
		// string containing each word from the dictionary and
		// from the document
		String word;
		// user response when a misspelled word is noted
		String response;
	
		// insert each word from file "dict.dat" into a set
		while(dictFile.hasNext())
		{
			// input next word and add to dictionary
			word = dictFile.next();
			dictionary.add(word);
		}
	
		// read the document word by word and check spelling
		while(docFile.hasNext())
		{
			// get the next word from the document
			word = docFile.next();
	
			// lookup word up in the dictionary. if not present
			// assume word is misspelled. prompt user to add word to
			// the dictionary, ignore it, or flag as misspelled
			if (!dictionary.contains(word))
			{
				System.out.println(word);
				System.out.print(
					"   'a'(add)  'i'(ignore)  'm'(misspelled) ");
				response = keyIn.next();
				// if response is 'a' add to dictionary;
				// if not ignored, add to set of misspelled words
				if (response.charAt(0) == 'a')
					dictionary.add(word);
				else if (response.charAt(0) == 'm')
					misspelledWords.add(word);
			}
		}
	
		// display the set of misspelled words
		System.out.println("\nMisspelled words: " +
								 misspelledWords);
	}
}

/*
File "spell.txt"
teh message contians the url for the web-page
and a misspeled url for the email adress

Run:

Enter the document to spell check: spell.txt
teh
   'a'(add)  'i'(ignore)  'm'(misspelled) m
contians
   'a'(add)  'i'(ignore)  'm'(misspelled) m
url
   'a'(add)  'i'(ignore)  'm'(misspelled) a
web-page
   'a'(add)  'i'(ignore)  'm'(misspelled) i
misspeled
   'a'(add)  'i'(ignore)  'm'(misspelled) m
email
   'a'(add)  'i'(ignore)  'm'(misspelled) i
adress
   'a'(add)  'i'(ignore)  'm'(misspelled) m
Misspelled words: [adress, contians, misspeled, teh]
*/
