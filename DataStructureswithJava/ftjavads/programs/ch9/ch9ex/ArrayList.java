import java.lang.IndexOutOfBoundsException;
import java.util.NoSuchElementException;
import java.lang.IllegalStateException;
import java.util.ConcurrentModificationException;
import ds.util.Collection;
import ds.util.List;
import ds.util.Iterator;
import ds.util.ListIterator;
import ds.util.Arrays;

public class ArrayList<T> implements List<T>, Cloneable
{
   // number of elements in the list
   private int listSize;
   // the array holding list elements. the capacity
   // is listArr.length
   private T[] listArr;

   // increases whenever the list changes. the class creates
   // iterators whose variable expectedModCount equals the current
   // value of modCount. for an iterator operation to be valid,
   // modCount must equal expectedModCount
   private int modCount = 0;

	// verify that index is in the range 0 <= index <= upperBound. if
	// not throw the IndexOutOfBoundsException exception
	private void rangeCheck(int index, String msg, int upperBound)
	{
		if (index < 0 || index >= upperBound+1)
			throw new IndexOutOfBoundsException("\n" + msg + ": index " + index +
				" out of bounds. Should be in the range 0 to " +
				upperBound);
	}

	// inserts item at location index in this list.
	// shifts all subsequent elements to the right by one
	// position. if index == size(), add() appends
	// item at the back of the list. throws
	// IndexOutOfBoundsException if the index is out of range
	// (index < 0 || index > size())
	public void add(int index, T item)
	{
		// index == listSize is valid. append to the list
		rangeCheck(index, "ArrayList add()", listSize);

		// see if we need to reallocate more memory
		if (listSize == listArr.length)
			ensureCapacity(2*listArr.length);

		// insert item at location index by shifting
		// the elements at locations index+1 through
		// listSize-1 to the right. note that if
		// index == listSize the for loop does
		// nothing. we append to the list
		for (int j= listSize-1;j >= index;j--)
			listArr[j+1] = listArr[j];

		// insert item at location index and increment the
		// list size
		listArr[index] = item;
		listSize++;
	}

	// removes the element at location index in this list.
	// shifts any subsequent elements to the left and
	// returns the element that was removed from the list.
	// throws IndexOutOfBoundsException if the index is out
	// of range (index < 0 || index >= size())
	public T remove(int index)
	{
		// verify that index is in the proper range
		rangeCheck(index, "ArrayList remove()", listSize-1);

		// save the return value
		T returnElement = listArr[index];

		// shift elements at indices index+1 to listSize-1
		// left
		for (int j=index;j < listSize-1;j++)
			listArr[j] = listArr[j+1];

		// make former last entry a null reference and decrement
		// list size
		listArr[listSize-1] = null;
		listSize--;

		// return the value that was removed
		return returnElement;
	}

   // constructs an empty list with initial capacity 10
public ArrayList()
{
   listArr = (T[])new Object[10];
   listSize = 0;
}

	// appends item to the end of this list and returns true
	public boolean add(T item)
	{
		// call method add() at an index to insert item at the
		// end of the list
		add(listSize, item);

		return true;
	}

   // removes all of the elements from this list.
   // this list will be empty after this method returns
   public void clear()
   {
      // replace each element of listArr by null so garbage
      // collection will kick in
      for (int i=0;i < listSize;i++)
         listArr[i] = null;

      // change list size to 0
      listSize = 0;
   }

   // returns true if this list contains item
   // and false otherwise. we assume that item
   // has the method "public boolean equals(Object item)"
   // and use it to determine equality
   public boolean contains(Object item)
   {
      return indexOf(item) >= 0;
   }

	// increases the capacity of this ArrayList instance,
	public void ensureCapacity (int minCapacity)
	{
		// get the current capacity
		int currentCapacity = listArr.length;

		// only take action if the requested capacity
		// is larger than the existing capacity
		if (minCapacity > currentCapacity)
		{
			// capture a reference to the old array
			T[] oldListArr = listArr;

			// create the new array with the new capacity
			listArr = (T[]) new Object[minCapacity];

			// copy the old data to the new array
			for (int i=0; i < listSize; i++)
				listArr[i] = oldListArr[i];

			// nullify reference to the old array. garbage
			// collection will recover the space
			oldListArr = null;
		}
	}

   // returns the element at the specified position in
   // this list. if the index is out of range
   // (index < 0 || index >= size()), throws
   // IndexOutOfBoundsException
   public T get(int index)
   {
      // verify that index is in the proper range
      rangeCheck(index, "ArrayList get()", listSize-1);

      return listArr[index];
   }

   // searches for the first occurrence of the given argument.
   // assumes that item has the method
   // "public boolean equals(Object item)" and
   // uses it to determine equality. returns the index of the
   // first occurrence of the argument in this list if item
   // is found and false otherwise
   public int indexOf(Object item)
   {
      int i;

      // elem is not null. search for it using equals()
      for (i=0;i < listSize;i++)
      {
         if (item.equals(listArr[i]))
            // success
            return i;
      }

      // elem is not in the list. return -1
      return -1;
   }

   // returns true if this list contains no elements
   // and false if the list has at least 1 element
   public boolean isEmpty()
   {
      return listSize == 0;
   }

   // returns an iterator over the elements in this list
   // in order first to last
   public Iterator<T> iterator()
   {
      return new IteratorImpl();
   }

   // returns a list iterator over the elements in this array
   // list in order first to last
   public ListIterator<T> listIterator()
   {
      return new ListIteratorImpl(0);
   }

   // returns a list iterator over the elements in this array
   // list from index to the end of the list
   public ListIterator<T> listIterator(int index)
   {
      return new ListIteratorImpl(index);
   }

	// if item is present in the list, removes the first
	// instance of it from this list. returns true if
	// an element was removed and false otherwise
	public boolean remove(Object item)
	{
		int i = 0, j;
		boolean retValue = true;

		// use indexOf() to search for item
		if ((i = indexOf(item)) != -1)
			remove(i);
		else
			retValue = false;

		return retValue;
	}

	// replaces the value at the specified position in this list
	// with item and returns the previous value. if the index is
	// out of range (index < 0 || index >= size()), throws
	// IndexOutOfBoundsException
	public T set(int index, T item)
	{
		// verify that index is in the proper range
		rangeCheck(index, "ArrayList set()", listSize-1);

		// save the element at listArr[index]
		T previousValue = listArr[index];

		// assign the new element at position index
		listArr[index] = item;

		// return the previous element
		return previousValue;
	}

   // returns the number of elements in this list
   public int size()
   {
      return listSize;
   }

   // returns a string representation of this list. the
   // representation lists the elements in the order from
   // first to last, enclosed in square brackets ("[]").
   // adjacent elements are separated by a comma and a space
	public String toString()
	{
		Object[] arr = toArray();

		return Arrays.toString(arr);
	}

   // trims the capacity of this ArrayList instance to be the
   // list's current size. an application can use this operation
   // to minimize the storage of an ArrayList instance.
   public void trimToSize()
   {
      int currentCapacity = listArr.length;

      if (listSize < currentCapacity)
      {
         T[] oldListArr = listArr;

         listArr = (T[])new Object[listSize];

         // copy the old data to the new array
         for (int i=0; i < listSize; i++)
            listArr[i] = oldListArr[i];

         // nullify reference to the old array. garbage
         // collection will recover the space
         oldListArr = null;
      }
   }

   // returns an array containing all of the elements in this collection
   public Object[] toArray()
   {
		Object[] returnArray = new Object[listSize];

		for (int i=0;i < listSize;i++)
			returnArray[i] = listArr[i];

		return returnArray;
	}

	public Object clone()
	{
		ArrayList<T> copy = null;

		try
		{
			copy = (ArrayList<T>)super.clone();
		}
		catch (CloneNotSupportedException cnse)
		{ throw new InternalError(); }

		// replace listArr in copy by a new reference to an array
		copy.listArr = (T[])new Object[listSize];
		
		// copy the elements from listArr to copy.listArr
		for (int i=0;i < listSize;i++)
			copy.listArr[i] = listArr[i];

		// return the cloned object
		return copy;
	}

   private class IteratorImpl implements Iterator<T>
   {
      // set expectedModCount to the number of list changes
      // at the time of iterator creation
      protected int expectedModCount = modCount;

      // index of the subsequent element that will be returned by next()
      protected int nextIndex = 0;
      // index of the last value returned by next() or -1 if that
      // value was deleted by the iterator method remove()
      protected int prevIndex = -1;

      // constructor. not strictly necessary
      IteratorImpl()
      {}

      // returns true if the collection being traversed
      // has more elements
      public boolean hasNext()
      {
         // elements remain if nextIndex has not reached
         // index listSize
         return nextIndex != listSize;
      }

      // returns the next element in the interation.
      // throws NoSuchElementException if the iteration
      // has no more elements
      public T next()
      {
         T retValue = null;

         // check that the iterator is in a consistent state
         checkIteratorState();

         // call get() to obtain retValue. the call may throw
         // an IndexOutOfBoundsException exception, so put
         // the code in a try block
         try
         {
            retValue = get(nextIndex);
            // we're ok. prevIndex is nextIndex and move
            // nextIndex forward
            prevIndex = nextIndex;
            nextIndex++;
         }
         catch(IndexOutOfBoundsException iobe)
         {
            // OOPS! nextIndex was invalid. throw
            // a NoSuchElementException exception as
            // required by the API
            throw new NoSuchElementException(
                  "Iteration has no more elements");
         }

         return retValue;
      }

      // removes the last element returned by next() from the
      // underlying collection. this method can be called only
      // once per call to next(). the behavior of an iterator
      // is unspecified if the underlying collection is modified
      // while the iteration is in progress in any way other than
      // by calling this method. throws IllegalStateException
      // if next() has not yet been called,or remove() has already
      // been called after the last call to next()
      public void remove()
      {
         // check for a missing call to next() or previous()
         if (prevIndex == -1)
            throw new IllegalStateException(
               "Iterator call to next() or previous() " +
               "required before calling remove()");

         // make sure our state is good
         checkIteratorState();

         ArrayList.this.remove(prevIndex);

         // list has been modified
         modCount++;
         expectedModCount = modCount;

         // since we shifted elements left. nextIndex is now prevIndex
         nextIndex = prevIndex;
         // we did a deletion. indicate this by setting index prevIndex
         // to -1
         prevIndex = -1;
      }

      // protected so ListIteratorImpl class can use it also
      protected void checkIteratorState()
      {
         if (expectedModCount != modCount)
            throw new ConcurrentModificationException(
               "Inconsistent iterator");
      }
   }

   private class ListIteratorImpl
      extends IteratorImpl implements ListIterator<T>
   {
      // int nextIndex = 0 OBTAINED FROM THE SUPERCLASS
      // index of the subsequent element that will be returned by next()

      // int prevIndex = -1 OBTAINED FROM THE SUPERCLASS
      // index of element returned by most recent call to next() or
      // previous(). reset to -1 if this element is deleted by a call
      // to remove() or is a new element inserted by add()

      // constructor
      ListIteratorImpl(int index)
      {
			if (index < 0 || index > listSize)
				throw new IndexOutOfBoundsException(
							"Index: "+ index+ ", Size: "+ listSize);

			nextIndex = index;
		}

      // returns true if the list being traversed has more
      // elements when moving in the forward direction
      // public boolean hasNext() IMPLEMENTED IN THE SUPERCLASS

      // returns the next element in the list. throws
      // NoSuchElementException if the iteration has no more
      // elements
      // public Object next() IMPLEMENTED IN THE SUPERCLASS

      // removes the last element returned by next() or previous()
      // from the list. this method can be called only once per
      // call to next() or previous(). it can be made only if
      // add() has not been called after the last call to next()
      // or previous()
      // public void remove()  IMPLEMENTED IN THE SUPERCLASS

      // inserts item into the list. it is inserted
      // immediately before the next value that would be
      // returned by next, if any, and after the next value
      // that would be returned by previous, if any. if the
      // list is empty, item becomes the sole value in
      // the list. a subsequent call to next is unaffected,
      // and a subsequent call to previous returns the new
      // element
      public void add(T item)
      {
         checkIteratorState();

         // insert item at index nextIndex using the outer class
         // method add(). this call increments modCount
         ArrayList.this.add(nextIndex, item);
         expectedModCount = modCount;

         // increment nexIndex so next() will return the original
         // value. set preIndex = -1 to invalidate a call to set()
         // without an intervening call to next() or previous()
         nextIndex++;
         prevIndex = -1;
      }

      // returns true if the list being traversed has more
      // elements when moving in the backward direction
      public boolean hasPrevious()
      {
         return nextIndex > 0;
      }

      // returns the index of the element that would be returned
      // by a subsequent call to next(). returns list size if the
      // iterator is at the end of the list
      public int nextIndex()
      {
         return nextIndex;
      }

      // returns the previous element in the list. throws
      // NoSuchElementException if the iteration has no
      // previous element
      public T previous()
      {
         T retValue = null;

         checkIteratorState();

         // move backward one position
         nextIndex--;

         // call get() to obtain retValue. the call may throw
         // an IndexOutOfBoundsException exception if we
         // fall off the front of the list, so put
         // the code in a try block
         try
         {
            retValue = get(nextIndex);
            // we're ok. prevIndex is nextIndex
            prevIndex = nextIndex;
         }
         catch(IndexOutOfBoundsException iobe)
         {
            // OOPS! nextIndex was invalid. throw
            // a NoSuchElementException exception as
            // required by the API
            throw new NoSuchElementException(
                  "Iteration has no more elements");
         }

         return retValue;
      }

      // returns the index of the element that would be returned
      // by a subsequent call to previous(). returns -1 if the
      // iterator is at the beginning of the list
      public int previousIndex()
      {
         return nextIndex != 0 ? nextIndex-1 : -1;
      }

		// replaces the last value returned by next() or previous()
      // with item. throws IllegalStateException if neither
      // next() nor previous() have been called, or remove() or
      // add() have been called after the last call to next() or
      // previous()
      public void set(T item)
      {
         if (prevIndex == -1)
            throw new IllegalStateException(
               "Iterator call to next() or previous() " +
               "required before calling set()");

         checkIteratorState();

         // use outer class set() to place the new element at
         // position prevIndex
         ArrayList.this.set(prevIndex, item);
      }
   }
   
   public ArrayList(T[] arr)
	{}

	public int lastIndexOf(Object item)
	{ return 0; }
	
	// remove n items from the ArrayList beginning at
	// position index. if less than n items remain beginning
	// at position index, remove the tail of the list
	public void remove(int index, int n)
	{}

	public void insert(int index, T[] arr)
	{ } 
}
