public class TestSortedListPT{
    
   public static void main (String[] args){

   ListPT plainList = new ArrayListPT();

SortedListPT sortedList = new SortedArrayListPT();

for (int i = 0; i < 10; i++){
   int data = (int)(Math.random() * 10);
   plainList.add(i, "" + data);
   sortedList.add("" + data);
}

for (int i = 0; i < plainList.size(); i++){
   String data = plainList.get(i).toString() + " " +
                 sortedList.get(i).toString();
   System.out.println(data);
}


/*
     // Uncomment one of these at a time and test
      //ListPT list = new ArrayListPT();
      SortedListPT list = new SortedArrayListPT();


      System.out.println ("#1<<<<<<<<<<<<<<<<<<<<");

      System.out.println ("Expect false     : " + list.contains("a"));
      System.out.println ("Expect 0         : " + list.size());
      System.out.println ("Expect emptylist : " + list.toString());

      System.out.println ("#2<<<<<<<<<<<<<<<<<<<<<<<");

      list.add("h");
      list.add("p");
      list.add("x");
      list.add("g");
      list.add("a");
      list.add("k");
      System.out.println ("Expect 6      : " + list.size());
      System.out.println ("Expect true   : " + list.contains("a"));
      System.out.println ("Expect true   : " + list.contains("h"));
      System.out.println ("Expect true   : " + list.contains("x"));
      System.out.println ("Expect false  : " + list.contains("y"));
      System.out.println ("Expect agk    : " + list.get(0)+list.get(1)+list.get(2));
      System.out.println ("Expect aghkpx : " + list.toString());

      System.out.println ("#3<<<<<<<<<<<<<<<<<<<<<<<");

      System.out.println ("Expect agxpkh    : " + list.remove(0)
                                                + list.remove(0)
                                                + list.remove(3)
                                                + list.remove(2)
                                                + list.remove(1)
                                                + list.remove(0));
      System.out.println ("Expect emptylist : " + list.toString());
      System.out.println ("#3<<<<<<<<<<<<<<<<<<<<<<<");

      */
   }
}

