public class TestListPT{
    
   public static void main (String[] args){

     // Uncomment one of these at a time and test
      //ListPT list = new ArrayListPT();
      ListPT list = new SortedArrayListPT();


      System.out.println ("#1<<<<<<<<<<<<<<<<<<<<");

      System.out.println ("Expect false     : " + list.contains("a"));
      System.out.println ("Expect 0         : " + list.size());
      System.out.println ("Expect emptylist : " + list.toString());

      System.out.println ("#2<<<<<<<<<<<<<<<<<<<<<<<");

      list.add(0, "h");
      list.add(1, "p");
      list.add(2, "x");
      list.add(0, "g");
      list.add(0, "a");
      list.add(3, "k");
      System.out.println ("Expect 6      : " + list.size());
      System.out.println ("Expect true   : " + list.contains("a"));
      System.out.println ("Expect true   : " + list.contains("h"));
      System.out.println ("Expect true   : " + list.contains("x"));
      System.out.println ("Expect false  : " + list.contains("y"));
      System.out.println ("Expect agx    : " + list.get(0)+list.get(1)+list.get(5));
      System.out.println ("Expect aghkpx : " + list.toString());

      System.out.println ("#3<<<<<<<<<<<<<<<<<<<<<<<");

      System.out.println ("Expect agxpkh    : " + list.remove(0)
                                                + list.remove(0)
                                                + list.remove(3)
                                                + list.remove(2)
                                                + list.remove(1)
                                                + list.remove(0));
      System.out.println ("Expect emptylist : " + list.toString());
      list.add(0, "m");
      list.add(0, "h");
      list.add(1, "k");
      list.add(3, "z");
      System.out.println ("Expect hkmz      : " + list.toString());
      System.out.println ("Expect hmz       : " + list.set(0, "b")
                                                + list.set(2, "p")
                                                + list.set(3, "w"));
      System.out.println ("Expect bkpw      : " + list.toString());
      System.out.println ("#3<<<<<<<<<<<<<<<<<<<<<<<"); 
   }
}

