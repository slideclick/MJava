import ioutil.*;

public class VaryingPrecision {

   public static void main(String[] args){

      FileReader reader = new FileReader("in.dat");
      double number = 1.23456;
      for (int i = 0; i < 10; i++){
         String str = "The number with precision " + i + ":"
                    + Format.justify('r', number, 10, i);
         System.out.println(str);
      }
   }
}