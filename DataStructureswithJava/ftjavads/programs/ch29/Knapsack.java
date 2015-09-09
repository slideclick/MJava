public class Knapsack
{
	private int capacity;
	private int numItems;
	private Item[] itemList;
	private int[][] maxValueMat;

	public Knapsack(Item[] list, int cap)
	{
		int i;

		capacity = cap;
		numItems = list.length;

		// initialize dimensions for itemList and maxValueMat
		itemList = new Item[numItems+1];
		maxValueMat = new int[numItems+1][capacity+1];

		// initialize itemList from list so that item1 = list[0]
		// is in itemList[1], item2 = list[1] is in itemList[2],
		// and so forth
		for (i = 1; i <= numItems; i++)
			itemList[i] = list[i-1];

		// build the matrix
		buildMaxValueMat();
	}

	// builds maxValueMat for specified capacity
	private void buildMaxValueMat()
	{
		int i, cap, testMax;

		// compute entries in the matrix
		for (i = 1; i <= numItems; i++)
			for (cap = 1; cap <= capacity; cap++)
			{
				// keep the same max value by default
				maxValueMat[i][cap] = maxValueMat[i-1][cap];

				// test if itemList[i] fits into the knapsack
				if (cap-itemList[i].size >= 0)
				{
					// test if maximum value increases
					testMax = maxValueMat[i-1][cap-itemList[i].size] +
									itemList[i].value;
					// if yes, assign new max
					if (testMax > maxValueMat[i-1][cap])
						maxValueMat[i][cap] = testMax;
				}
			}
	}

	// displays capacity, items in knapsack, max value, and unused capacity
	public void displayKnapsack()
	{
		int i = numItems, cap = capacity;

		// create header with capcity and maximum value
		System.out.println("Capacity: " + capacity + "  Value: " +
			  	maxValueMat[numItems][capacity]);

		// list items in the knapsack by reading from maxValueMat
		System.out.println("Contents: ");
		while (i > 0)
		{
			// if values in successive rows are not equal,
			// itemList[i] is part of the solution
			if (maxValueMat[i][cap] != maxValueMat[i-1][cap])
			{
				System.out.println("   item" + i + '(' + itemList[i].size +
					  ',' + itemList[i].value + ')');
				// look for maximum value remaining space
				cap -= itemList[i].size;
			}
			i--;
		}
		System.out.println("   Unused capacity: " + cap);
	}
}