// TestParser
// (c) 2000 Ken Lambert and Martin Osborne

import java.util.*;
import BreezyGUI.*;

public class TestParser{

  public static void main (String[] args){
      String str;
      ExpressionTree tree;
      Parser parser;
      
      System.out.println ("#1<<<<<<<<<<<<<<<<<<<<<<<");
      
      parser = new Parser();
      System.out.println ("Expect false           : " + parser.successful());
      System.out.println ("Expect no parse yet    : " + parser);

      try{parser.parse ("");}
      catch(Exception e){
          System.out.println ("Expect false           : " + parser.successful());
          System.out.println ("Expect null or empty   : " + parser);
      }
      
      tree = parser.parse ("3");
      System.out.println ("Input '3'              : " + tree.infix());
      tree = parser.parse ("3;");
      System.out.println ("Input '3;'             : " + tree.infix());
      tree = parser.parse ("  3  +  4  ");
      System.out.println ("Input '  3  +  4  '    : " + tree.infix());
      tree = parser.parse ("3+4");
      System.out.println ("Input '3+4'            : " + tree.infix());
      tree = parser.parse ("3+45;xyz");
      System.out.println ("Input '3+45;xyz'       : " + tree.infix());
      tree = parser.parse ("(3+4)*5-6");
      System.out.println ("Input '(3+4)*5-6'      : " + tree.infix());
      System.out.println ("Expect true            : " + parser.successful());
      System.out.println ("Expect successful      : " + parser);
      
      GBFrame.pause();

      try{
          System.out.println ("Expect exception for '3 6+x4;'");
          tree = parser.parse ("3 6+x4;");
      }catch (Exception e){
          System.out.println ("Expect false              : " + parser.successful());
          System.out.println ("Expect some error message \n" + parser);
          GBFrame.pause();
      }
         
      try{
          System.out.println ("Expect exception for '3+x4;'");
          tree = parser.parse ("3+x4;");
      }catch (Exception e){
          System.out.println("Error: " + e.toString());
          GBFrame.pause();
      }

      try{
          System.out.println ("Expect exception for '3+4x'");
          tree = parser.parse ("3+4x");
      }catch (Exception e){
          System.out.println("Error: " + e.toString());
          GBFrame.pause();
      }
         
      try{
          System.out.println ("Expect exception for '3+*4;'");
          tree = parser.parse ("3+*4;");
      }catch (Exception e){
          System.out.println("Error: " + e.toString());
          GBFrame.pause();
      }
         
      try{
          System.out.println ("Expect exception for '*3+4;'");
          tree = parser.parse ("*3+4;");
      }catch (Exception e){
          System.out.println("Error: " + e.toString());
          GBFrame.pause();
      }
         
      try{
          System.out.println ("Expect exception for ';'");
          tree = parser.parse (";");
      }catch (Exception e){
          System.out.println("Error: " + e.toString());
          GBFrame.pause();
      }
         
      try{
          System.out.println ("Expect exception for ''");
          tree = parser.parse ("");
      }catch (Exception e){
          System.out.println("Error: " + e.toString());
          GBFrame.pause();
      }
   
  }
}                                                                                           
