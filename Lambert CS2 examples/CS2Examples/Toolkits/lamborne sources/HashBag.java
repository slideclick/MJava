// HashBag
// (c) 1999 Ken Lambert and Martin Osborne
 
/**
 * This class etc...
 */

package lamborne;

import java.util.*;
import java.io.*;

 
public class HashBag extends AbstractBag 
                     implements Bag, Cloneable{

    private static final int DEFAULT_CAPACITY = 97; 
           
    // Temporary variables
    private transient Entry foundEntry;  // entry just located
                                         // undefined if not found
    private transient Entry priorEntry;  // entry prior to one just located
                                         // undefined if not found
    private transient int   index;       // index of chain in which entry 
                                         // located undefined if not found
                                    
    // Instance variables
    private           int   capacity;    // capacity of table[]
    private transient Entry table[];     // the table of collision lists
    

    public HashBag()
    {
        super();
        capacity = DEFAULT_CAPACITY;
        table = new Entry[capacity];
    }

    public HashBag(int capacity)
    {
        super();
        this.capacity = capacity;
        table = new Entry[capacity];
    }

    public HashBag (Collection col, int capacity)
    {
        super();
        
        if (col == null)
            throw new IllegalArgumentException("Collection is null");
               
        buildBag (col.iterator(), capacity);
    }

    public HashBag (Iterator iter, int capacity)
    {
        super();
      
        if (iter == null)
           throw new IllegalArgumentException("Iterator is null");
               
        buildBag (iter, capacity);
    }

    private void buildBag (Iterator iter, int capacity)
    {
        this.capacity = capacity;
        table = new Entry[capacity];
        while (iter.hasNext())
            add (iter.next());
    }
   
    public int add(Object item)
    {
        if (item == null)
            throw new IllegalArgumentException 
            ("Trying to add a null item to a bag");
            
        size++;
        modCount++;
        if (!contains (item)){ 
            Entry newEntry = new Entry (item, table[index]);
            table[index] = newEntry;
            return 1;
        }else{
            foundEntry.count++ ;
            return foundEntry.count;
        }
    }
    
    public void clear()
    {
        super.clear();
        table = new Entry[capacity];
    }

    public Object clone()
    {
        Iterator iter = iterator();
        HashBag clone = new HashBag (capacity);
        while (iter.hasNext()){
            Object nextItem = iter.next();
            for (int i = 1; i <= getCount (nextItem); i++)
                clone.add (nextItem);
        }     
        return clone;
    }
    
    public boolean contains (Object item)
    {
        if (item == null)
            throw new IllegalArgumentException 
            ("Trying to see if a bag contains a null item");
            
        index = Math.abs(item.hashCode()) % capacity;
        priorEntry = null;
        foundEntry = table[index];
        while (foundEntry != null){
            if (foundEntry.item.equals (item)) 
                return true;
            else{
                priorEntry = foundEntry;
                foundEntry = foundEntry.next;
            }
        }
        return false;
    }
    
    public String debugString()
    {
        String rowStr;
        String str = "HashBag: capacity = " +  capacity  
                   + " size = " + size(); 
        for (int i = 0; i < table.length; i++){
            rowStr = "";
            for (Entry entry = table[i]; entry != null; entry = entry.next) 
                rowStr += entry + " "; 
            if (rowStr != "")
                str += "\nRow " + i + ": " + rowStr;
        }
        return str;   
    }
        
    public int getCount(Object item)
    {
        if (item == null)
            throw new IllegalArgumentException 
            ("Trying to get a count for a null item");
            
        if (contains (item)) 
            return foundEntry.count;
        else
            return 0;
    } 
    
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    public Iterator iterator()
    {
        return new InnerIter();
    }
    
    public int remove(Object item)
    {
        if (item == null)
            throw new IllegalArgumentException 
            ("Trying to remove a null item from a bag");
            
        if (!contains (item))
            return -1;
        else if (foundEntry.count == 1){
            if (priorEntry == null)
                table[index] = foundEntry.next;
            else
                priorEntry.next = foundEntry.next;
        }
        modCount++;
        size--; 
        foundEntry.count--;
        return foundEntry.count;
    }
    
    public int size()
    {
        return size;
    }
    
    public String toString()
    {
        String str = "[";
        Iterator iter = iterator();
        for (int i = 0; iter.hasNext(); i++) {
            Object next = (iter.next());
            str += "(" + next + "," + getCount (next) + ")";
            if (iter.hasNext()) 
                str += " ";      
        }
        str += "]";
        return str;
    }
    
    private void writeObject(ObjectOutputStream s) throws IOException
    {
        s.defaultWriteObject();
        for (int i = 0; i < capacity; i++) {
            Entry entry = table[i];
            if (entry == null)
                s.writeObject (null);
            else{
                while (entry != null) {
                    s.writeObject(entry.item);
                    s.writeInt(entry.count);
                    entry = entry.next;
                }
            }
        }
    }
    
    private void readObject(ObjectInputStream s)
                 throws IOException, ClassNotFoundException
    {
        s.defaultReadObject();
        size = 0;
        table = new Entry[capacity];
        for (int i=0; i < capacity; i++) {
            Object item = s.readObject();
            if (item != null){
                int count = s.readInt();
                for (int j = 0; j < count; j++)
                    add (item);
            }
        }
        modCount = 0;
    }
    
//************************** Inner Classes ********************************

    private class Entry  {

        private Object item;      //Item for this entry
        private int    count ;    //Count for this entry
        private Entry  next;      //Reference to next entry
    
        private Entry()
        {
            item = null;
            count = 0;
            next = null;
        }

        private Entry (Object item, Entry next)
        {
            this.item = item;
            count = 1;
            this.next =next;
        }

        private Entry (Object item, int count, Entry next)
        {
            this.item = item;
            this.count = count;
            this.next =next;
        }
            
        public String toString()
        {
            return "(" + item + "," + count + ")";
        }
    }

    private class InnerIter implements Iterator{
    
       private int        expectedModCount = modCount;
       private int        index;      // Current index into table[]
       private Entry      nextEntry;  // Next node in the set
       private int        left;       // Number of items left
                                   
       protected InnerIter () 
       {
           left = size();
           getFirst();
       }
    
       public boolean hasNext() 
       {
           return left > 0; 
       }
    
       public Object next() 
       {
           if (modCount != expectedModCount)
              throw new ConcurrentModificationException();
           if (!hasNext())
              throw new NoSuchElementException("There are no more elements");   
                     
           Object returnValue = nextEntry.item;
           left -= nextEntry.count;
           getNext();
           return returnValue;
       }
    
       private void getFirst()
       {
           if (left <= 0) return;
        
           for (index = 0; index < table.length; index++){
               nextEntry = table[index];
               if (nextEntry != null)
                   return;
           }
       }
    
       private void getNext()
       {
           if (left <= 0) return;
           nextEntry = nextEntry.next;
           if (nextEntry != null) return;
           for (index = index + 1; index < table.length; index++){
               nextEntry = table[index];
               if (nextEntry != null) 
                   return;
           }
       }

      public void remove()
      {
           throw new UnsupportedOperationException("Remove not allowed");
      }
    }       
      
}
