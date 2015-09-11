// AbstractContainer
// (c) 1999 Ken Lambert and Martin Osborne

package lamborne;

import java.util.*;
import java.io.Serializable;

public abstract class AbstractContainer implements Serializable {

    protected int size;
    protected transient int modCount;  
    
    public AbstractContainer()
    {
        size = 0;
        modCount = 0;
    }

    public void clear()
    {
        size = 0;
        modCount++;
    }
    
    private transient Collection col = null;
    public Collection collectionView()
    /*
     * Returns a collection that is based on the iterator for the 
     * underlying container. 
     */
    {
        if (col != null) return col;

        col = new AbstractCollection() 
              {
                public Iterator iterator(){
                    return AbstractContainer.this.iterator();
                }
                
                public int size() {
                    return AbstractContainer.this.size();
                }
              };
        return col;
    }
  
    public int hashCode()
    {
        Object obj;
        int hashCode = 0;
        Iterator iter = iterator();
        
        while (iter.hasNext())
            hashCode += iter.next().hashCode();
        return hashCode;
    }
        

    public boolean isEmpty()
    {
        return size() == 0;
    }

   
    public abstract Iterator iterator();
    /*
     * Returns an iterator on the container. 
     * 
     * Any changes made through the iterator are reflected in the 
     * underlying container.
     *
     * While the iterator is active, changes to the container's structure
     * should be made only via the iterator.
     *
     * Changing the container's structure independently of the iterator
     * can adversely affect the iterator, possibly giving rise to 
     * non-deterministic behavior in the future. The iterator protects itself
     * from such an event by throwing a ConcurrentModificationException. 
     * This is called "fail-fast" behavior.
     */

    public int size()
    {
        return size;
    }
   
    public Object[] toArray() 
    {
        Object[] array = new Object[size()];
        Iterator iter = iterator();
        for (int i=0; iter.hasNext(); i++)
            array[i] = iter.next();
        return array;
    }

    public String toString()
    {
        String str = "[";
        Iterator iter = iterator();
        int imax = size() - 1;
        for (int i = 0; iter.hasNext(); i++) {
            str += iter.next();
            if (i < imax) 
                str += ", ";      
        }
        str += "]";
        return str;
    }
    
}

