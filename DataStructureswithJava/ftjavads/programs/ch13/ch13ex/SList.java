import java.util.NoSuchElementException;
import java.lang.IllegalStateException;
import java.util.ConcurrentModificationException;

import ds.util.Node;
import ds.util.Nodes;
import ds.util.Iterator;

public class SList<T>
{
	private Node<T> listFront;
	private int listSize;
	private int modCount;

	public SList()
	{
	}

	public void addFirst(T item)
	{
	}

	public void addLast(T item)
	{
	}

 	public T getFirst()
 	{
 		return null;
 	}

	public T getLast()
 	{
 		return null;
 	}

	public boolean isEmpty()
	{ return listSize == 0; }

	public void removeFirst()
	{
 		
 	}

	public void removeLast()
	{
 		
 	}

	public int size()
	{ return listSize; }

	public String toString()
	{ return Nodes.toString(listFront); }

	// returns an iterator over the elements in this list
	// in order first to last
	public Iterator<T> iterator()
	{
		return new IteratorImpl();
	}

	private class IteratorImpl implements Iterator<T>
   {
      // set expectedModCount to the number of list changes
      // at the time of iterator creation
      int expectedModCount = modCount;

      // node whose value is returned a subsequent call to next()
      Node<T> nextNode = listFront;

      // node of the last value returned by next() or null if that
      // value was deleted by the iterator method remove()
      Node<T> lastReturned = null;

      // constructor. not strictly necessary
      IteratorImpl()
      {}

		// returns true if the collection being traversed
		// has more elements
		public boolean hasNext()
		{
			return true;
		}

		// returns the next element in the interation.
		// throws NoSuchElementException if the iteration
		// has no more elements
		public T next()
		{
			return null;
		}

		// remove() throws an UnsupportedOperationException
		public void remove()
		{
			throw new UnsupportedOperationException("SList: remove not defined");
		}

		void checkIteratorState()
		{
			
		}
   }
}