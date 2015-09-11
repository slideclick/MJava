public class DemoTextReader {

   public static void main(String[] args){

      FileReader reader = new FileReader("in.dat");
      char word = reader.readChar();
      while (!reader.iseof()){
         System.out.print(word + ":");
         word = reader.readChar();
      }
      System.out.print("<" + word + ">");
/*

      System.out.println("Enter name (one line), age, wage, gender:");
      KeyboardReader reader = new KeyboardReader();
      String name = reader.readLine("Name: ");
      int age     = reader.readInt("Age: ");
      double wage = reader.readDouble("Wage: ");
      char gender = reader.readChar("Gender: ");

      System.out.println("Name:" + name + ":\n" +
                         "Age:" + age + ":\n" +
                         "Wage:" + wage + ":\n" +
                         "Gender:" + gender +
                          ':');
*/
   }
}