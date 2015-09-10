import java.util.Random;

import ds.util.Collection;
import ds.util.Iterator;
import ds.util.Arrays;

public class Bag<T> implements Collection<T>
{
	protected T[] bagArr;		// storage structure
	protected int bagSize;		// size of collection

	// used by grab()
	private static Random rnd = new Random();

	// remove the element bagArr[i] by shifting the tail of the 
	// array left one position and decrementing bagSize
	private void remove(int i)
	{
		// copy bagArr[i+1] ... bagArr[bagSize-1]
		// left one position
		for (int j=i; j < bagSize-1; j++)
			bagArr[j] = bagArr[j+1];

		// decrement bagSize
		bagSize--;
	}

	// create an object with size 0
	public Bag(int capacity)
	{
		// value of capacity is maximum number of elements
		bagArr = (T[])new Object[capacity];
		bagSize = 0;
	}

	// stores item if space is available and
	// returns true; otherwise, returns false
	public boolean add(T item)
	{
		boolean returnValue;

		if (bagSize >= bagArr.length)
			return false;
		else
		{
			// append item at index bagSize
			bagArr[bagSize] = item;

			// increment bagSize and return true
			bagSize++;

			return true;
		}
	}

	public void clear()
	{
		// the bag has no elements
		bagSize = 0;
	}

	public boolean contains(Object item)
	{
		// search positions from 0 to bagSize-1
		for (int i=0; i < bagSize; i++)
			if (bagArr[i].equals(item))
				return true;

		return false;
	}

	public boolean isEmpty()
	{ return bagSize == 0;	}

	// search for item. if found, remove it and return
	// true; otherwise return false
	public boolean remove(Object item)
	{
		// search positions from 0 to bagSize-1
		for (int i=0;i < bagSize;i++)
			if (bagArr[i].equals(item))
			{
				// call remove to delete bagArr[i]
				remove(i);
				return true;
			}
		return false;
	}

	// return the number of positions of element
	// that we are using
	public int size()
	{ return bagSize; }

	public Object[] toArray()
	{
		Object[] returnArray = new Object[bagSize];
		
		for (int i=0; i < bagSize; i++)
			returnArray[i] = bagArr[i];

		return returnArray;
	}

	public String toString()
	{
		Object[] arr = toArray();

		return Arrays.toString(arr);
	}
	
	public Iterator<T> iterator()
	{ return new IteratorImpl(); }

	// return value of random object in range [0,bagSize)
	public T grab()
	{
		return bagArr[rnd.nextInt(bagSize)];
	}

	// subclass that defines an iterator object
	private class IteratorImpl implements Iterator<T>
	{
		// node whose value is returned a subsequent call to next()
		int nextIndex = 0;
		int lastIndex = -1;

		// constructor. not strictly necessary
		IteratorImpl()
		{}

		// returns true if the collection being traversed
		// has more elements
		public boolean hasNext()
		{
			// elements remain if the next node is not the header
			return nextIndex != bagSize;
		}

		// returns the next element in the interation or throws exception if no more elements
		public T next()
		{
			// check if the iteration has an another element; if not throw exception
			if (nextIndex == bagSize)
				throw new RuntimeException("Iteration has no more elements");

			// save current value of next as lastReturned; nextNode
			lastIndex = nextIndex;
			nextIndex++;

			return bagArr[lastIndex];
		}

		// removes the last element returned by next(). this method can be called only once
		// per call to next(). throws IllegalStateException if next() has not yet been called
		// or remove() has already been called after the last call to next()
		public void remove()
		{
			// check for a missing call to next()
			if (lastIndex == -1)
				throw new RuntimeException("Iterator call to next() " +
							"required before calling remove()");

			Bag.this.remove(lastIndex);

			// we did a deletion.indicate this by setting lastIndex to -1
			// nextIndex is reset to the left one position
			nextIndex--;
			lastIndex = -1;
		}
	}
}

