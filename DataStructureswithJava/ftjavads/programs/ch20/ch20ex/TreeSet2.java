/*
 * @(#)TreeSet2.java
 *
 */

import java.util.NoSuchElementException;
import java.lang.IllegalStateException;
import java.util.ConcurrentModificationException;
import java.io.*;

import ds.util.Collection;
import ds.util.OrderedSet;
import ds.util.Iterator;
import ds.util.Iterable;

/**
 * An implementation of the <tt>OrderedSet</tt> using <tt>STNode</tt> elements
 * and binary search tree (<tt>STree</tt> algorithms.
 *
 * @see	    HashSet
 * @param <T> the type of elements held in this collection
 */

public class TreeSet2<T> implements OrderedSet<T>, Iterable<T>, Cloneable, java.io.Serializable
{
	// set implemented using a TreeMap
	private TreeMap<T, Object> map;
	// value for each key in the map
	private static final Object PRESENT = new Object();

   public TreeSet2()
   {
   }

	public boolean add(T item)
	{
	  return true;
	}

	public void clear()
   {
   }

   public boolean contains(Object item)
   {
      return true;
   }

   public boolean isEmpty()
   {
      return true;
   }

   // returns an iterator for the elements in the set
   public Iterator<T> iterator()
   {
      return null;
   }

	// if item is in the set, remove it
	// and return true; otherwise, return
	// false
	public boolean remove(Object item)
	{
		return true;
	}

   public int size()
   {
      return 0;
   }

	// returns the first (least) element in this sorted set
	public T first()
	{
		return null;
	}

	// returns the last (greatest) element in this sorted set
	public T last()
	{
		return null;
	}

   // returns an array containing all of the elements in this collection
   public Object[] toArray()
   {
      return null;
   }

   // returns objects in the tree as an ordered list in the
   // format [obj1, obj2,  ...]
   public String toString()
   {
      return null;
   }
}