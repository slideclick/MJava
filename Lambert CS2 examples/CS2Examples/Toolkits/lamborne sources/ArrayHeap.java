// ArrayHeap
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

/**
 * This class ...
 */

import java.util.*;

public class ArrayHeap extends AbstractHeap 
                       implements Heap, Cloneable {

    private static final int 
           DEFAULTCAPACITY = 10;     // Default capacity of a new heap
    private int initCapacity;        // Initial capacity of a new heap
    private Object heap[];           // The array that holds the items
    
    // NOTE: the top of the heap is at location 0
    //       the bottom is at location size - 1
    
//******************************** Constructors ******************************

    public ArrayHeap()
    {   
        super();
        initCapacity = DEFAULTCAPACITY;
        heap = new Object[initCapacity];
    }
    
    public ArrayHeap (int initialCapacity)
    {  
        super();
        
        if (initialCapacity < 1)
            throw new IllegalArgumentException 
            ("Trying to instantiate a heap with initial capacity < 1");
                         
        initCapacity = initialCapacity;
        heap = new Object[initCapacity];
    }
    

    public ArrayHeap (Iterator iter)
    {  
        super();
        buildHeap (iter, DEFAULTCAPACITY);
    }

    public ArrayHeap (Iterator iter, int initialCapacity)
    {  
        super();
        buildHeap (iter, initialCapacity);
    }

    public ArrayHeap (Collection col)
    {
        super();
        
        if (col == null)
            throw new IllegalArgumentException 
            ("Trying to instantiate a heap with a null collection");
            
        buildHeap (col.iterator(), col.size());
    }
    
    private void buildHeap (Iterator iter, int initialCapacity)
    {  
        if (initialCapacity < 1)
            throw new IllegalArgumentException 
            ("Trying to instantiate a heap with initial capacity < 1");
        if (iter == null)
            throw new IllegalArgumentException 
            ("Trying to instantiate a heap with a null iterator");
            
        initCapacity = initialCapacity;
        heap = new Object [initCapacity];
        Object next;
        while (iter.hasNext()){
            next = iter.next();
            
            if (!(next instanceof Comparable))
                throw new IllegalArgumentException 
                ("All objects in the itererator must be comparable");
                
            add (next);
        }
        modCount = 0;
    }
    

//******************************** Methods ******************************

    public boolean add (Object item)
    {
        if (!(item instanceof Comparable))
            throw new IllegalArgumentException 
            ("Trying to add a non-comparable item to heap");
        int curPos, parent;
        
        heap = Utilities.expandArrayIfNeeded (heap, size);
        heap[size] = item;
        size++;
        modCount++;
        curPos = size - 1;
        while (curPos > 0){
            parent = (curPos - 1) / 2;
            Comparable parentItem = (Comparable)(heap[parent]);
            if (parentItem.compareTo ((Comparable)item) >= 0)
                return true;
            else{
                heap[curPos] = heap[parent];
                heap[parent] = item;
                curPos = parent;
            }
        }
        return true;
    } 
    
    public Object clone()
    {
        ArrayHeap clone = new ArrayHeap (size);
        for (int i = 0; i < size; i++)
            clone.heap[i] = heap[i];
        clone.size = size;
        return clone;
    }
      

    public void clear()
    {
        super.clear();
        heap = new Object [initCapacity];
    }
    

    public String debugString()
    {
        String str = "ArrayHeap array size: " + heap.length + "\n";
        str += "Items in level order follow: \n";
        for (int i = 0; i < size; i++)
            str += heap[i].toString() + " ";
        return str;
    }
    
    public Iterator iterator()
    // The iterator returns the items in descending order..
    {
        return new InnerIter();
    }
    
    public Object peek(){
        if (size == 0)
            throw new NoSuchElementException ("Trying to peek at an empty heap");
        return heap[0];
    }
        
    public Object pop()
    {
        if (size == 0)
            throw new NoSuchElementException 
            ("Trying to remove from an empty heap");
        
        int curPos, leftChild, rightChild, maxChild, lastIndex;
        Object topItem = heap[0];
        Comparable bottomItem = (Comparable)(heap[size - 1]);
        
        heap = Utilities.shrinkArrayIfNeeded (heap, size, initCapacity);
        
        heap[0] = bottomItem;
        size--;
        modCount++;
        lastIndex = size - 1;
        curPos = 0;
        while (true){
            leftChild = 2 * curPos + 1 ;
            rightChild = 2 * curPos + 2;
            if (leftChild > lastIndex) break;
            if (rightChild > lastIndex) 
                maxChild = leftChild;
            else{
                Comparable leftItem  = (Comparable)(heap[leftChild]);
                Comparable rightItem = (Comparable)(heap[rightChild]);
                if (leftItem.compareTo (rightItem) > 0)
                    maxChild = leftChild;
                else
                    maxChild = rightChild;
            }
            Comparable maxItem = (Comparable)(heap[maxChild]);
            if (bottomItem.compareTo (maxItem) >= 0)
                break;
            else{
                heap[curPos] = heap[maxChild];
                heap[maxChild]= bottomItem;
                curPos = maxChild;
            }
        }    
        return topItem;
    }        

//************************** Inner Classes ********************************

    private class InnerIter implements Iterator{
    
        private int    curPos;
        private Object sortedItems[];
        private int    expectedModCount = modCount;
        
        protected InnerIter()
        {
            curPos = size - 1;
            if (size > 0)
                sortedItems = AbstractHeap.sort (heap, size);
        }
        
        public boolean hasNext()
        {
            return curPos >= 0;
        }
        
        public Object next()
        {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException("There are no more elements");            
            
            Object item = sortedItems[curPos];
            curPos--;
            return item;
        }

        public void remove()
        {
            throw new UnsupportedOperationException("Remove not allowed");
        }
    }

}
