import java.util.*;
import lamborne.*;

// NEED TO ADD TESTS FOR PRECONDITIONS

public class TestMap{
   public static void main (String[] args){
      HashMapPT t = new HashMapPT();
      
      System.out.println ("Expect \n null null null null xxx yyy\n "  
            + t.put("a","aaa") + " " 
            + t.put("b","xxx") + " " 
            + t.put("c","ccc") + " " 
            + t.put("d","ddd") + " " 
            + t.put("b","yyy") + " " 
            + t.put("b","bbb"));
            
      System.out.println ("Expect the Table abcd: \n" + t);
      String str =        "Expect \n aaa null true false true false bbb null aaa null 3 0 true\n "
                          + t.get ("a")              + " "         
                          + t.get ("x")              + " "
                          + t.containsValue("bbb")   + " "
                          + t.containsValue("xxx")   + " "   
                          + t.containsKey ("b")      + " "            
                          + t.containsKey ("x")      + " "
                          + t.get("b")               + " "     
                          + t.get("x")               + " "
                          + t.remove("a")            + " "     
                          + t.remove("x")            + " "
                          + t.size()                 + " ";      
      t.clear();
      str += t.size() + " " + t.isEmpty();
      
      System.out.println (str);
  }
}

