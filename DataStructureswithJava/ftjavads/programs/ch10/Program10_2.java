import ds.util.LinkedList;
import java.util.Scanner;

public class Program10_2
{
	public static void main(String[] args)
	{
		// create an empty linked list
		LinkedList<String> draftlist = new LinkedList<String>();

		// variables used to update the draft list
		int fromIndex, toIndex;
		char updateAction;
		String playerName;
		String obj;

		// initial names in the list and the keyboard input file
		String[] playerArr ={"Jones", "Hardy", "Donovan", "Bundy"};
		Scanner keyIn = new Scanner(System.in);
		String inputStr;

		// initialize the list
		for (int i = 0; i < playerArr.length; i++)
			draftlist.add(playerArr[i]);

		// give instructions on updating the list
		System.out.println("Add player:    Input 'a' <name>");
		System.out.println("Shift player:  Input 's' <from> <to>");
		System.out.println(
			"Delete player: Input 'r' <name>" + "\n");

		// initial list
		System.out.println("List: " + draftlist);

		// loop executes the simulation of draft updates
		while (true)
		{
			// input updateAction, exiting on 'q'
			System.out.print("   Update: ");
			updateAction = keyIn.next().charAt(0);

			if (updateAction == 'q')
				break;
			
			// execute the update
			switch(updateAction)
			{
				case 'a': 	
					// input the name and add to end of list
					playerName = keyIn.next();
					draftlist.add(playerName);
					break;

				case 'r': 	
					// input the name and remove from list
					playerName = keyIn.next();
					draftlist.remove(playerName);
					break;

				case 's':	
					// input two indices to shift an element from a 
					// source position to a destination position. 
					// remove element at source and add at destination
					fromIndex = keyIn.nextInt();
					fromIndex--;	// set to list position
					toIndex = keyIn.nextInt();
					toIndex--;		// set to list position
					obj = draftlist.remove(fromIndex);
					draftlist.add(toIndex, obj);
					break;
			}
			// Display status of current draft list
			System.out.println("List: " + draftlist);
		}
	}
}

/*
Run:

Add player:    Input 'a' <name>
Shift player:  Input 's' <from> <to>
Delete player: Input 'r' <name>

List: [Jones, Hardy, Donovan, Bundy]
   Update: a Harrison
List: [Jones, Hardy, Donovan, Bundy, Harrison]
   Update: s 4 2
List: [Jones, Bundy, Hardy, Donovan, Harrison]
   Update: r Donovan
List: [Jones, Bundy, Hardy, Harrison]
   Update: a Garcia
List: [Jones, Bundy, Hardy, Harrison, Garcia]
   Update: s 5 2
List: [Jones, Garcia, Bundy, Hardy, Harrison]
   Update: s 1 4
List: [Garcia, Bundy, Hardy, Jones, Harrison]
   Update: q
*/
