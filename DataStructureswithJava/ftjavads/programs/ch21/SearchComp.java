import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.io.FileReader;
import java.io.FileNotFoundException;

import ds.util.HashSet;
import ds.util.TreeSet;
import ds.time.Timing;

public class SearchComp
{
	public static void main(String[] args)
	{
		// array and ArrayList that store strings in random order
		ArrayList<String> alist = new ArrayList<String>();
		
		// declare a binary search tree and a hash table
		// with elements from alist
		HashSet<String> hSet = new HashSet<String>();
		TreeSet<String> tSet = new TreeSet<String>();

		// open 25000+ word dictionary;
		Scanner dictionary = null;

		try
		{	
			dictionary = new Scanner(new FileReader("dict.dat"));
		}
		catch (FileNotFoundException fnfe)
		{
			System.err.println("Cannot open \"dict.dat\"");
			System.exit(1);
		}

		// objects used in the search tests
		String word;
		Timing t = new Timing();
		int len, method, i;

		// copy dictionary to alist
		while(true)
		{
			if (!dictionary.hasNext())
				break;
			word = dictionary.next();
			alist.add(word);
		}

		// time the building of the TreeSet and HashSet collection
		len = alist.size();
		System.out.println("Number of words is " + len + "\n");
		t.start();
		for (i = 0; i < len; i++)
			tSet.add(alist.get(i));
		System.out.println("Built TreeSet in " + t.stop() + " seconds");

		t.start();
		for (i = 0; i < len; i++)
			hSet.add(alist.get(i));
		System.out.println("Built HashSet in " + t.stop() + " seconds");

		// shuffle alist
		Collections.shuffle(alist);

		// test the two search methods
		for (method = 0; method < 2; method++)
		{
			// start the timer
			t.start();
			// implement the search
			switch(method)
			{
				 case 0: for (i = 0; i < len; i++)
								 tSet.contains(alist.get(i));
							System.out.print("TreeSet search time is " +
									  t.stop());
							break;

				 // search HashSet
				 case 1: for (i = 0; i < len; i++)
								 hSet.contains(alist.get(i));
							System.out.print("HashSet search time is " +
									  t.stop());
							break;
			}
			System.out.println(" seconds");
		}
	}
}

/*
Run:

Number of words is 25025

Built TreeSet in 0.078 seconds
Built HashSet in 0.047 seconds
TreeSet search time is 0.078 seconds
HashSet search time is 0.016 seconds
*/
