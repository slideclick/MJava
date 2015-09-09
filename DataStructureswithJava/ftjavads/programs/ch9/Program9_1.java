import java.util.Scanner;
import java.io.*;

import ds.util.ArrayList;

public class Program9_1
{
	public static void main(String[] args) throws IOException
	{
		Scanner fileIn =
			new Scanner(new FileReader("gradlist.dat"));
		// input strings from the file
		String inputStr, gradName, gradDegree;
		
		// string of 20 blank characters for names in an ArrayList
		String buffer = " ";

		// ArrayLists holds diplomaList and baList
		ArrayList<String> diplomaList = new ArrayList<String>(), 
		          			baList = new ArrayList<String>();

		// the Scanner delimiters are tab, newline, and carriage
		// return
		fileIn.useDelimiter("[\t\n\r]+");

		// read registrar's list to eof and add to array lists
		while(fileIn.hasNext())
		{
			// input the name which is tab separated from the degree
			gradName = fileIn.next();

			// input the degree
			gradDegree = fileIn.next();

			// add name and degree as string in specified list
			if (gradDegree.equals("BS"))
				diplomaList.add(gradName + " " + gradDegree);
			else
				baList.add(gradName + " " + gradDegree);
		}

		// join the BA list at end of diploma list
		join(diplomaList, baList);

		// output a list header and the list of names with degrees
		System.out.println("Diploma List");
		for (int i = 0; i < diplomaList.size(); i++)
			System.out.println("\t" + (String)diplomaList.get(i));
	}

	// attach listB onto the end of listA
	public static <T> void
	join (ArrayList<T> listA, ArrayList<T> listB)
	{
		// capture the size of ArrayLists listA and listB
		int i, sizeA = listA.size(), sizeB = listB.size();

		// insure sufficient capacity for listA
		listA.ensureCapacity(sizeA + sizeB);

		// use index i to access the elements of listB and add()
		// to insert elements from listB at rear of listA
		for (i = 0; i < sizeB; i++)
			listA.add(listB.get(i));
	}
}


/*
<File "gradlist.dat">

Bailey, Julie	BS
Frazer, Thomas	BA
Harkness, Bailey	BA
Johnson, Shannon	BS
Kilmer, William	BA
Miller, Sara	BS
Nelson, Harold	BS
O'Dell, Jack	BA
Wilson, Rebecca	BS

Run:

Diploma List
	Bailey, Julie BS
	Johnson, Shannon BS
	Miller, Sara BS
	Nelson, Harold BS
	Wilson, Rebecca BS
	Frazer, Thomas BA
	Harkness, Bailey BA
	Kilmer, William BA
	O'Dell, Jack BA
*/
