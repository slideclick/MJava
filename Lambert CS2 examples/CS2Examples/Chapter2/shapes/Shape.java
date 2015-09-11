abstract public class Shape extends Object{

   private double xPos;
   private double yPos;
   
   public Shape (){
      xPos = 0;
      yPos = 0;
   }
   
   public Shape (double x, double y){
      xPos = x;
      yPos = y;
   }
   
   abstract public double area();
   
   abstract public void stretchBy (double factor);
   
   public final double getXPos(){
      return xPos;
   }
   
   public final double getYPos(){
      return yPos;
   }

   public void moveTo (double xLoc, double yLoc){
      xPos = xLoc;
      yPos = yLoc;
   }
   
   public String toString(){
      String str = "(X,Y) Position: (" + xPos + "," + yPos + ")\n";
      return str;
   }

}