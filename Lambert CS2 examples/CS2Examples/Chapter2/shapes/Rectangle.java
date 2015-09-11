public class Rectangle extends Shape{

   private double width;
   private double height;
   
   public Rectangle(){
      super();
      width = 0;
      height = 0;
   }
   
   public Rectangle (double xLoc, double yLoc, double wdth, double hght){
      super (xLoc, yLoc);
      width = wdth;
      height = hght;
   }
   
   public double area(){
      return width * height;
   }
   
   public void flipDimensions(){
      double temp = width;
      width = height;
      height = temp;
   }

   public void stretchBy (double factor){
      width = width * factor;
      height = height * factor;
   }
   
   public String toString(){
      String str = "RECTANGLE\n"
                 + super.toString() 
                 + "Width & Height: " + width + " & " + height +"\n"
                 + "Area: " + area();
      return str;
   }
}