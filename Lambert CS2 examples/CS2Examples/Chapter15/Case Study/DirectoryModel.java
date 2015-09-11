import java.util.*;

public class DirectoryModel extends Object{
    
   private SortedMap entries;
   private DirectoryInterface view;
   
   public DirectoryModel(DirectoryInterface view){
      this.view = view;
      entries = new TreeMap();
   }
   
   public String findEntry(String name){
      return (String)entries.get(name);
   }
   
   public void addEntry(String name, String number){
      entries.put(name, number);
      Set names = entries.keySet();
      view.displayNames(names.toArray());
   }
   
}