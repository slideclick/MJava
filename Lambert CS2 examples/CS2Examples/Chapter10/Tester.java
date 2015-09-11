import ioutil.KeyboardReader;

public class Tester{

   static int count;
   
   public static void main (String[] args){
      KeyboardReader reader = new KeyboardReader();
      int i, fibn, n;
      for (i = 1; i <= 5; i++){
         count = 0;
         n = (int) Math.pow (2, i);
         fibn = fibonacci (n);
         System.out.println ("" + n + ":" + count);
      }
   } 

   static int fibonacci (int n){
      count++;
      if (n <= 2) 
         return 1;
      else
         return fibonacci (n - 1) + fibonacci (n - 2);
   }
}
