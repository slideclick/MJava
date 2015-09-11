public class TestTiny{

   public static void main(String[] args){

      Tiny cllctn = new ArrayTiny(); 
      int[] hoursArray1 = {8, 8, 10, 8, 8};
      int[] hoursArray2 = {8, 6, 8, 8, 10};

      //Add objects of different types to the collection
      cllctn.add ("Shall I compare thee to a summer's day?");
      cllctn.add (new Employee ("Ken", 8.5, hoursArray1));
      cllctn.add (new Employee ("Sue", 8.5, hoursArray2));
      cllctn.add ("My heart leaps up when I behold a rainbow in the sky.");

      //Remove the objects from the collection and process them differently
      //depending on their types. 
      //Display a string's length and an employee's name.
      while (cllctn.size() != 0){
         String message;   
         Object obj = cllctn.removeLast();
         //System.out.println(obj);
         if (obj instanceof String){
            String str = (String)obj;
            message = "The length of the string is: " + str.length();
         }else if (obj instanceof Employee){
            Employee emp = (Employee)obj;
            message = "The employee is called: " + emp.getName(); 
         }else{
            message = "The type of the object is unknown";
         }
         System.out.println (message);
      }
   }
}
