import java.io.*;
import java.util.regex.*;
import java.util.StringTokenizer;
import java.util.Scanner;
import ds.util.*;

public class Program19_5
{
	private static Pattern identifierPattern =
							Pattern.compile("[a-zA-Z][a-zA-Z0-9]*");

	public static void main(String[] args) throws IOException
	{
		String filename;
		Scanner keyIn = new Scanner(System.in);

		// get the file name
		System.out.print("Enter the file name: ");
		filename = keyIn.nextLine();
		System.out.println();

		// create the concordance
		concordance(filename);
	}

	// builds concordance and calls writeConcordance() for output
	public static void concordance(String filename)
			throws IOException
	{
		// concordance map and set for line numbers
		TreeMap<String, TreeSet<Integer>> concordanceMap =
								new TreeMap<String, TreeSet<Integer>>();
		TreeSet<Integer> lineNumbers;
		String inputLine, identifier;
		int lineNumber = 0;


		// create scanner to input from document file
		Scanner fin = new Scanner(new FileReader(filename));		
		Matcher matcher = null;

		// read the file a line at a time
		while(fin.hasNext())
		{
			// get next line
			inputLine = fin.nextLine();
			lineNumber++;

			// create matcher to find identifiers in inputLine
			matcher = identifierPattern.matcher(inputLine);
			
			// extract identifiers until end of line
			while (matcher.find())
			{
				identifier = inputLine.substring(matcher.start(),
																 matcher.end());
				
				// get value (TreeSet) from entry with identifier as
				// key. if it does not exist (null), create TreeSet
				// object
				lineNumbers = concordanceMap.get(identifier);
				if ( lineNumbers == null )
					lineNumbers = new TreeSet<Integer>();

				// add a new line number to set of line numbers
				lineNumbers.add(lineNumber);
				concordanceMap.put(identifier, lineNumbers);
			}
		}

		// output the concordance
		writeConcordance(concordanceMap);
	}

	public static void
	     writeConcordance(TreeMap<String,TreeSet<Integer>> map)
	{
		Set<Map.Entry<String,TreeSet<Integer>>> entries =
				map.entrySet();
		TreeSet<Integer> lineNumberSet;
		Iterator<Map.Entry<String,TreeSet<Integer>>> iter =
				entries.iterator();
		Iterator<Integer> setIter;
		int i;

		while (iter.hasNext())
		{
			Map.Entry<String,TreeSet<Integer>> e = iter.next();
			System.out.print(e.getKey());					// output key

			// pad output to 12 characters using blanks
			if (e.getKey().length() < 12)
				for (i=0;i < 12 -(e.getKey().length());i++)
					System.out.print(' ');

			// extract the value component as a TreeSet
			lineNumberSet = e.getValue();

			// display number of lines containing the identifier and
			// the actual lines
			System.out.print(formatInt(4, lineNumberSet.size()) +
  								 ":    ");
			setIter = lineNumberSet.iterator();
			while (setIter.hasNext())
				System.out.print(setIter.next() + "   ");
			System.out.println();
		}
		System.out.println();
	}

	// private method formatInt() with integer arguments w and n.
	// returns formatted string with integer n right-justified in
	// a field of w spaces. used to line up output in concordance
	private static String formatInt(int w, int n)
	{
		// get the string representation of n and a return string
		String strn = Integer.toString(n);
		String rtnStr = "";

		// capture the length of strn
		int strnLength = strn.length();

		// if length of strn is at least w, just return strn
		if (strnLength >= w)
			return strn;

		// build rtnStr by first appending w - strnLength blanks;
		// then append strn with the value of n and return
		for (int i=0;i < w - strnLength;i++)
			rtnStr += " ";
		rtnStr += strn;

		return rtnStr;
	}
}

/*
File "concord.txt"
	int m = 12, n = 14;
	double a = 3, b = 2, hypotenuse
	
	if (n <= 5)
		n = 2*m;
	else
		n = m * m;
	hypotenuse = sqrt(a*a + b*b);

Run:
	Enter the file name: concord.txt
	
	a              2:    2   8   
	b              2:    2   8   
	double         1:    2   
	else           1:    6   
	hypotenuse     2:    2   8   
	if             1:    4   
	int            1:    1   
	m              3:    1   5   7   
	n              4:    1   4   5   7   
	sqrt           1:    8   
*/
