public class ViewTest
{
	public static void main(String[] args)
	{
		// create object and display its current status
		StoreOneTwo st = new StoreOneTwo(5, 35);
		System.out.println("Initial object st: " + st);

		// use the View object to access first field in st
		View v = st.viewOne();
		System.out.println("Value viewed from st = " + v.get());
		
		// use view object v to update field in st
		v.set(25);
		System.out.println("Updated object st:  " + st);
		
		// use setOneTwo() to update the object directly  the
		// display the view of the object using v.get()
		st.setOneTwo(3, 7);
		System.out.println("Value viewed from updated st: " + v.get());
	}
}

/*
Run:

Initial object st: 5=35
Value viewed from st = 5
Updated object st:  25=35
Value viewed from updated st: 3
*/
