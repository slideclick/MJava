// HashBagPT
// (c) 2000 Ken Lambert and Martin Osborne
 

public class HashBagPT {

    private static final int DEFAULT_CAPACITY = 3; 
                               // Purposely set to a small value inorder
                               // to ensure collisions
           
    // Temporary variables
    private Entry foundEntry;  // entry just located
                               // undefined if not found
    private Entry priorEntry;  // entry prior to one just located
                               // undefined if not found
    private int   index;       // index of chain in which entry located
                               // undefined if not found
                                    
    // Instance variables
    private int   capacity;    // size of table[]
    private Entry table[];     // the table of collision lists
    private int   size;        // number of entries in the map
    

    public HashBagPT()
    {
        capacity = DEFAULT_CAPACITY;
        clear();
    }

    public int add(Object item)
    {
        size++;
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
        size = 0;
        table = new Entry[capacity];
    }

    public boolean contains (Object item)
    {
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
    
    public int getCount(Object item)
    {
        if (contains (item)) 
            return foundEntry.count;
        else
            return 0;
    } 
    
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    public int remove(Object item)
    {
        if (!contains (item))
            return -1;
        else if (foundEntry.count == 1){
            if (priorEntry == null)
                table[index] = foundEntry.next;
            else
                priorEntry.next = foundEntry.next;
        }
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
        String rowStr;
        String str = "HashBagPT: capacity = " +  capacity  
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

    private class Entry {

        private Object item;      //Item for this entry
        private int    count ;    //Count for this entry
        private Entry  next;      //Reference to next entry
    
        private Entry(){
            item = null;
            count = 0;
            next = null;
        }

        private Entry(Object item, Entry next){
            this.item = item;
            count = 1;
            this.next =next;
        }

        public String toString()
        {
            return "(" + item + ", " + count + ")";
        }
    }

}
