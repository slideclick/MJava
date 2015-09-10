import java.util.Scanner;

public class Program29_1
{
	public static void main(String[] args)
	{
		// array of Item object for the knapsack
		Item itemList[] = {new Item(2,1), new Item(3,4), new Item(4,3),
						  new Item(5,6), new Item(6,8)};
		// use for keyboard input
		Scanner keyIn = new Scanner(System.in);

		int capacity;

		System.out.print("Enter the capacity: ");
		capacity = keyIn.nextInt();

		// create a knapsack object
		Knapsack ks = new Knapsack(itemList,capacity);

		// display the solution
		ks.displayKnapsack();
		System.out.println();
	}
}

/*
Run 1:

Enter the capacity: 12
Capacity: 12  Value: 14
Contents:
   item5(6,8)
   item4(5,6)
   Unused capacity: 1

Run 2:

Enter the capacity: 19
Capacity: 19  Value: 21
Contents:
   item5(6,8)
   item4(5,6)
   item3(4,3)
   item2(3,4)
   Unused capacity: 1
*/
