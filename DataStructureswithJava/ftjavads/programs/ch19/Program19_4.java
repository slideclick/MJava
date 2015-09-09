import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ds.util.TreeMap;
import ds.util.TreeSet;

public class Program19_4
{
	public static void main(String[] args)
	{
		// softwareMap holds entries that are (string, set) pairs
		TreeMap<String, TreeSet<String>> softwareMap =
			new TreeMap<String, TreeSet<String>>();
		Scanner fin = null;
		TreeSet<String> prodSet;
		String company, product;

		try
		{
			fin = new Scanner(new FileReader("product.dat"));
			fin.useDelimiter("[\t\n\r]+");
		}
		catch (FileNotFoundException fnfe)
		{
			System.err.println("Cannot open \"product.dat\"");
			System.exit(1);
		}

		while(fin.hasNext())
		{
			// get company and product names
			company = fin.next();
			product = fin.next();

			// return value (set) corresponding to company name
			prodSet = softwareMap.get(company);

			// if no entry exists, create an empty set
			if (prodSet == null)
				prodSet = new TreeSet<String>();

			// add product name to the set; then add entry with
			// company as key and set as value
			prodSet.add(product);
			softwareMap.put(company, prodSet);
		}
		// display contents of the softwareMap
		System.out.println(softwareMap);
	}
}

/*
File <product.dat> with tab-separated data

Microsoft	Visual C++
Borland	C++ Builder
Microsoft	Word
Ramsoft	EZJava
Borland	J Builder
Adobe	Photoshop
Microsoft	Excel
Adobe	Illustrator

Run:

{Adobe=[Illustrator, Photoshop], Borland=[C++ Builder, J Builder],
 Microsoft=[Excel, Visual C++, Word], Ramsoft=[EZJava]}
*/
