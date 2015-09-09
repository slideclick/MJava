public class Program1_2 
{
	enum TrashContainer
	{
		// name and size for three types of containers
		garbage(30), recycle(60), garden(90);
		
		// variable initialize when the enum constant is created 
		private int containerSize;
		
		// constructor passed the enum constant integer argument
		private TrashContainer(int size) 
		{ containerSize = size; }
		
		// returns value associated with the enum constant
		public int size() 
		{ return containerSize; }
	}
	
	public static void main(String[] args) 
	{
		// scan values in the TrashContainer enum clsss
		for (TrashContainer c : TrashContainer.values()) 
		{
			// display container name, size, and color; the 
			// latter value results from a call to color() 
			System.out.println(c + ":\t Size " + c.size() +
									 " gal. \tColor " + color(c));
		}
	}
	
	// private enum class with names for the containers
	private enum ContainerColor {brown, yellow, green}
	
	// switch statement selects container type and returns color
	private static ContainerColor color(TrashContainer container) 
	{
		ContainerColor color = null;

		switch(container) 
		{
			case garbage:  
				color = ContainerColor.brown;
				break;
			case recycle:  
				color = ContainerColor.yellow;
				break;
			case garden:   
				color = ContainerColor.green;
				break;
		}
		
		return color;
	}
}

/*
Run:

garbage:	 Size 30 gal. 	Color brown
recycle:	 Size 60 gal. 	Color yellow
garden:	 Size 90 gal. 	Color green
*/
