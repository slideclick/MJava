public class DemoKeyboardReader {

   public static void main(String[] args){
      KeyboardReader reader = new KeyboardReader();
      
      String name = reader.readLine("Enter your name: ");
      int age     = reader.readInt("Enter your age: ");
      double wage = reader.readDouble("Enter your wage: ");
      char gender = reader.readChar("Enter your gender[M/F]: ");

      System.out.println("Name:   " + name + "\n" +
                         "Age:    " + age + "\n" +
                         "Wage:   " + wage + "\n" +
                         "Gender: " + gender);
   }
}