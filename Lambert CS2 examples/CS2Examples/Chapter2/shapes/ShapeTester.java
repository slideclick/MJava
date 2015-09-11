public class ShapeTester {

   public static void main (String[] args){
      Circle circ;
      circ = new Circle (0, 0, 1);
      System.out.println ("Creating\n" + circ.toString());
      circ.moveTo (1, 2);
      System.out.println ("Moving\n" + circ.toString());
      circ.stretchBy (2);
      System.out.println ("Stretching\n" + circ.toString());
      
      Rectangle rect;
      rect = new Rectangle (0, 0, 1, 2);
      System.out.println ("Creating\n" + rect.toString());
      rect.moveTo (1, 2);
      System.out.println ("Moving\n" + rect.toString());
      rect.stretchBy (2);
      System.out.println ("Stretching\n" + rect.toString());
      rect.flipDimensions();
      System.out.println ("Flipping dimensions\n" + rect.toString());
   }
}