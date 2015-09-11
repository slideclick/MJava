import ioutil.*;
public class AddThreeIntegers {

   public static void main(String[] args){
      String name;
      int sum, num1, num2, num3;
      KeyboardReader reader = new KeyboardReader();

      name = reader.readLine("Enter your name: ");
      num1 = reader.readInt("Enter three integers: ");
      num2 = reader.readInt();
      num3 = reader.readInt();
      sum = num1 + num2 + num3;
      System.out.println(name + " the sum of the number is " + sum);
   }
}