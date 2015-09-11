import ioutil.*;

public class PrintSales {

   // Declare variables shared by several methods
   private static String name;
   private static double sales, totalSales = 0, 
                         commission, totalCommissions = 0;
   private static FileReader reader = new FileReader("in.dat");

   public static void main(String[] args){
 
      // Display the headilng
      displayText("NAME", "SALES", "COMMISSIONS");

      // Read sales figures, compute commisions, 
      // increment totals, and display
      readNameAndSales();
      while (!reader.iseof()){
         commission = sales * 0.10;
         totalSales += sales;
         totalCommissions += commission;
         displayNumbers(name, sales, commission);
         readNameAndSales();
      }

      // Display totals
      displayText("", "----------", "----------");
      displayNumbers("Totals", totalSales, totalCommissions);     
   }

   private static void readNameAndSales(){
      name = reader.readWord();
      sales = reader.readDouble();
   }

   private static void displayText(String s1, String s2, String s3){
      String str = Format.justify('l', s1, 12) +
                   Format.justify('r', s2, 15) +
                   Format.justify('r', s3, 15);
      System.out.println(str);
   }

   private static void displayNumbers(String s, double num1, double num2){
      String str = Format.justify('l', s, 12) +
                   Format.justify('r', num1, 15, 2) +
                   Format.justify('r', num2, 15, 2);
      System.out.println(str);
   }
}