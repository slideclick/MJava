import lamborne.*;

public class ERModel extends Object{

   private PriorityQueue patients;
   private ERView view;

   public ERModel(ERView v, int numPriorities){
      patients = new LinkedPriorityQueue(numPriorities);
      view = v;
   }

   public void schedule(String name, String priority){
      patients.enqueue(name, getPriority(priority));
      view.println(name + " is added to the " +
                   priority + " list");
   }

   public void treatNext(){
      if (patients.isEmpty())
         view.displayErrorMessage("No patients scheduled");
      else{
         String name = (String)patients.dequeue();
         view.println(name + " is being treated");
      }
   }

   public void treatAll(){
      if (patients.isEmpty())
         view.displayErrorMessage("No patients scheduled");
      while (! patients.isEmpty())
         treatNext();
   }

   private int getPriority(String priority){
      if (priority.equals("fair"))
         return 1;
      else if (priority.equals("critical"))
         return 2;
      else
         return 3;
   }
}