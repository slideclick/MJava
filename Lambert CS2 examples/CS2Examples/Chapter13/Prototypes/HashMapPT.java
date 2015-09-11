// HashMapPT
// (c) 2000 Ken Lambert and Martin Osborne
 

public class HashMapPT {

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
    

    public HashMapPT()
    {
        capacity = DEFAULT_CAPACITY;
        clear();
    }

    public void clear()
    {
        size = 0;
        table = new Entry[capacity];
    }

    public boolean containsKey (Object key)
    {
        index = Math.abs(key.hashCode()) % capacity;
        priorEntry = null;
        foundEntry = table[index];
        while (foundEntry != null){
            if (foundEntry.key.equals (key)) 
                return true;
            else{
                priorEntry = foundEntry;
                foundEntry = foundEntry.next;
            }
        }
        return false;
    }
    
    public boolean containsValue (Object value)
    {
        for (int i = 0; i < table.length; i++){
            for (Entry entry = table[i]; entry != null; entry = entry.next) 
                if (entry.value.equals (value))
                    return true;
        }
        return false; 
    }      

    public Object get(Object key)
    {
        if (containsKey (key)) 
            return foundEntry.value;
        else
            return null;
    } 
    
    public boolean isEmpty()
    {
        return size == 0;
    }

    public Object put(Object key, Object value)
    {
        if (!containsKey (key)){ 
            Entry newEntry = new Entry (key, value, table[index]);
            table[index] = newEntry;
            size++;
            return null;
        }else{
            Object returnValue = foundEntry.value;
            foundEntry.value = value;
            return returnValue;
        }
    }
    
    public Object remove(Object key)
    {
        if (!containsKey (key))
            return null;
        else{
            if (priorEntry == null)
                table[index] = foundEntry.next;
            else
                priorEntry.next = foundEntry.next;
            size--; 
            return foundEntry.value;
        }
    }
    
    public int size()
    {
        return size;
    }
    
    public String toString()
    {
        String rowStr;
        String str = "HashMapPT: capacity = " +  capacity  
                   + " load factor = " + ((double)size() / capacity); 
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

        private Object key;      //Key for this entry
        private Object value;    //Value for this entry
        private Entry  next;     //Reference to next entry
    
        private Entry(){
            key = null;
            value = null;
            next = null;
        }

        private Entry(Object key, Object value, Entry next){
            this.key = key;
            this.value = value;
            this.next =next;
        }
        
        public String toString()
        {
            return "(" + key + ", " + value + ")";
        }
    }

}
