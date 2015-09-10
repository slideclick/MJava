import ds.util.*;

public class OrderedLists
{
	// insert item into the ordered list
	public static <T extends Comparable<? super T>>
	void insertOrder(LinkedList<T> orderedList, T item)
	{
		// curr starts at first list element
		ListIterator<T> curr = orderedList.listIterator();
	
		// move forward until encountering the end of the list or
		// locating the insertion point inside the list
		while (curr.hasNext())
			// check if item is <= value extracted by next() 
			if(item.compareTo(curr.next()) <= 0)
			{
				// if so, reset curr back one position and exit loop
				curr.previous();
				break;
			}
	
		// add item before curr. if curr is at the end of the list
		// adds item as the last element of the list
		curr.add(item);
	}
	
	// remove duplicate values from the linked list
	public static <T> void removeDuplicates(LinkedList<T> aList)
	{
		// current value and the target
		T currValue, target;
	
		// list iterator that scans the list
		Iterator<T> curr;
	
		// start at the front of the list
		curr = aList.iterator();
	
		// assign target the first list element and move to
		// the second element
		target = curr.next();
	
		// cycle through the list and remove duplicates
		while(curr.hasNext())
		{
			// record the current list value
			currValue = curr.next();
	
			// if currValue equals target, remove it; otherwise
			// reassign the target to the current value
			if (currValue.equals(target))
				curr.remove();
			else
				target = currValue;
		}
	}
}
