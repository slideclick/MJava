import ds.util.OrderedList;
import ds.util.ListIterator;
import java.util.Scanner;			// for file input
import java.io.*;

public class Program12_1
{
   public static void main(String[] args) throws IOException
   {
      // words read from file and inserted into wordList
      OrderedList<WordFreq> wordList =
      		new OrderedList<WordFreq>();

      //  scanner to parse words in the file
      Scanner fileIn;

      // strings for input line and parse of words on the line
      String word = null;

      // WordFreg object for elements in wordList
      WordFreq wf = null;

      // use to search for the current word in the ordered list
      ListIterator<WordFreq> iter;

      // scanner name is a command line argument
		fileIn = new Scanner(new FileReader(args[0]));
		
      // read input line to end-of-file
      while (fileIn.hasNext())
      {
      	word = fileIn.next();
      	
         // create a wordFreq object with frequency 1
         wf = new WordFreq(word);

         // search to see if object is in the list
         iter = search(wordList, wf);
         if (iter != null)
            // yes. increment the word frequency
            iter.next().increment();
         else
            // word is new. insert obj into the list
            wordList.add(wf);
		}
      displayWords(wordList);
   }

	public static <T extends Comparable<? super T>>
	ListIterator<T> search(OrderedList<T> ordList, T target)
	{
		// initialize a list iterator
		ListIterator<T> iter = ordList.listIterator();
		T curr;

		// move through the ordered list
		while (iter.hasNext())
		{
			// get the current list value
			curr = iter.next();
			// see if current value matches target
			if (curr.equals(target))
			{
				// match. move iterator back to the match
				// and return its value
				iter.previous();
				return iter;
			}

			// if the target is less than current value, we won't
			// find the target in the ordered list
			else if (target.compareTo(curr) < 0)
				return null;
		}

		// the target is larger than any value in the list
		return null;
	}
	// output the word and frequency in 15 character positions;
	// limit output to 4 elements per line
	public static void displayWords(OrderedList aList)
	{
		ListIterator iter = aList.listIterator();
		int count = 0, i;
		String blanks;

		while (iter.hasNext())
		{
			String str = iter.next().toString();
			System.out.print(str);
			blanks = "";
			for (i=0;i < 15-str.length();i++)
				blanks += " ";
			System.out.print(blanks);
			count++;
			if (count % 4 == 0)
				System.out.println();
		}
		System.out.println();
	}
}

/*
File "wf.dat"
peter piper picked a peck of pickled peppers
a peck of pickled peppers peter piper picked
if peter piper picked a peck of pickled peppers
where is the peck that peter piper picked

RUN

a (3)          if (1)         is (1)         of (3)         
peck (4)       peppers (3)    peter (4)      picked (4)     
pickled (3)    piper (4)      that (1)       the (1)        
where (1)
*/