public class TestShapes {

   public static void main (String[] args){
      Rectangle rectangle;
      Shape shape1, shape2;
      
      rectangle = new Rectangle (1,1,4,6);
      shape1 = makeOneShapeFromAnother (rectangle, "circle");
      shape2 = makeOneShapeFromAnother (rectangle, "rectangle");

      System.out.println ("\nRectangle Area: " + rectangle.area() +
                          "\nCircle Area:    " + shape1.area() +
                          "\nRectangle Area: " + shape2.area());
   }
   
static private Shape makeOneShapeFromAnother 
               (Shape inShape, String returnType){
   Shape outShape = null;                       
   double area, radius, width, height;
   double x = inShape.getXPos();
   double y = inShape.getYPos();

   area = inShape.area();
   if (returnType.equals ("circle")){
      radius = Math.sqrt (area / Math.PI);
      outShape = new Circle (x, y, radius);                  // assign a circle
   }else if (returnType.equals ("rectangle")){
      width = height = Math.sqrt (area);
      outShape = new Rectangle (x, y, width, height);     // assign a rectangle
   }
   return outShape;
}
}
