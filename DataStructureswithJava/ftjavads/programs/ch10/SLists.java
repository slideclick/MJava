public class SLists
{
	// return a string that displays the list as a comma
	// separated sequence enclosed in brackets
	public static <T> String toString(Node<T> front)
	{
		if (front == null)
			return "null";

		Node<T> curr = front;
		// start with the left bracket and value of first node
		String str = "[" + curr.nodeValue;

		// append all but last node, separating items with a comma
		// polymorphism calls toString() for the nodeValue type
		while(curr.next != null)
		{
			curr = curr.next;
			str +=  ", " + curr.nodeValue;
		}
		str += "]";
		return str;
	}

	// delete the first occurrence of the target in the linked
	// list referenced by front. Return the value of front
	public static <T> Node<T> remove(Node<T> front, T target)
	{
		// curr moves through list, trailed by prev
	   Node<T>  curr = front, prev = null;
		// becomes true if we locate target
		boolean foundItem = false;

	   // scan until locate item or come to end of list
	   while (curr != null && !foundItem)
	   {
			// check for a match; if found, check whether deletion
			// occurs at the front or at an intermediate position
			// in the list; set boolean foundItem true
			if (target.equals(curr.nodeValue))
			{
			 	if (prev == null)				// remove the first Node
					front = front.next;
			 	else
					prev.next = curr.next;	// erase intermediate Node
				foundItem = true;
			}
		  	else
		  	{
			 	// advance curr and prev
			 	prev = curr;
			 	curr = curr.next;
		  	}
	   }
	   // return current value of front which is updated when the
		// deletion occurs at the first element in the list
		return front;
	}
}
