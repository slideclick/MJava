public class SortedArrayListPT extends ArrayListPT implements SortedListPT{

   public SortedArrayListPT(){
      super();
   }

   // Other methods and inner classes go here

   public void add(int i, Object o){
      throw new UnsupportedOperationException(
          "cannot add to a position in a sorted list");
   }

   public Object set(int i, Object o){
      throw new UnsupportedOperationException(
          "cannot replace an item at a position in a sorted list");
   }

   public int indexOf(Object o){
   // Check preconditions

      int left = 0;
      int right = size() - 1;

      while (left <= right){
         int mid = (left + right) / 2;
         int comparison = ((Comparable)o).compareTo(items[mid]);
         if (comparison == 0)
            return mid;
         else if (comparison == 1)
            left = mid + 1;
         else
            right = mid - 1;
      }
 
      return -1;
   }

   public boolean contains(Object o){
      return indexOf(o) != -1;
   }

   public void add(Object o){
      if (! (o instanceof Comparable))
         throw new IllegalArgumentException("item must be comparable");

      // Search for the right spot
      int probe = 0;
      for (probe = 0; probe < size(); probe++){
         int comparison = ((Comparable)o).compareTo(items[probe]);
         if (comparison <= 0)
            break;
      }

      // Use superclass method to open a hole and insert new item
      super.add(probe, o);
   }

}

