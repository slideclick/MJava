import java.io.*;
import java.util.Scanner;
import ds.util.Sets;
import ds.util.TreeSet;
import ds.util.Set;

public class Program19_2
{
	public static void main(String[] args)
	{
		// declare sets for current and new computer accounts 
		Set<String> oldAcct = new TreeSet<String>(),
		    currAcct = new TreeSet<String>(), processAcct, 
		    newAcct, carryOverAcct, obsoleteAcct;
		
		// input names from file into the set
		try
		{
			readAccounts("oldAcct.dat", oldAcct);
			readAccounts("currAcct.dat", currAcct);
		}
		catch(IOException ioe)
		{  
			System.err.println("Cannot open account file"); 
			System.exit(1);
		}
		
		// use set union to determine all accounts to update
		processAcct =
			Sets.union(currAcct, oldAcct);
	
		// use set intersection to determine carryover accounts
		carryOverAcct =
			Sets.intersection(currAcct, oldAcct);
	
		// use set differnce to determine new and obsolete accounts
		newAcct =
			Sets.difference(currAcct, oldAcct);
		obsoleteAcct =
			Sets.difference(oldAcct, currAcct);
	
		// output statements provide set description and elements
		System.out.println("Old Accounts:       " + oldAcct);
		System.out.println("Current Accounts:   " + currAcct);
		System.out.println("Process Accounts:   " + processAcct);
		System.out.println("New Accounts:       " + newAcct);
		System.out.println("Carryover Accounts: " + carryOverAcct);
		System.out.println("Obsolete Accounts:  " + obsoleteAcct);
	}
	
	public static void readAccounts(String filename,Set<String> t)
		throws IOException
	{
		Scanner sc = new Scanner(new FileReader(filename));
		String acctName;
	
		// input the set of current accounts
		while(sc.hasNext())
		{
			acctName = sc.next();
			t.add(acctName);
		}
	}
}

/*
Run:

Old Accounts:       [fbrue, gharris, lhung, tmiller]
Current Accounts:   [ascott, fbrue, wtubbs]
Process Accounts:   [ascott, fbrue, gharris, lhung, tmiller, wtubbs]
New Accounts:       [ascott, wtubbs]
Carryover Accounts: [fbrue]
Obsolete Accounts:  [gharris, lhung, tmiller]
*/
