public class Circle extends Shape{

   private double radius;
   
   public Circle(){
      super();
      radius = 0;
   }
   
   public Circle (double xLoc, double yLoc, double rds){
      super (xLoc, yLoc);
      radius = rds;
   }

   public double getRadius(){
      return radius;
   }
   
   public double area(){
      return Math.PI * radius * radius;
   }
   
   public void stretchBy (double factor){
      radius = radius * factor;
   }
   
   public String toString(){
      String str = "CIRCLE\n"
                 + super.toString() 
                 + "Radius: " + radius + "\n"
                 + "Area: " + area();
      return str;
   }     
}