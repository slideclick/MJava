import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DemoMultiViewApp{

   //////////////////////////////////////////////// main

   public static void main (String[] args){
      DemoMultiViewApp theApp = new DemoMultiViewApp();
   }


   //////////////////////////////////////////////// Model

   private Dog dog;
   
   //////////////////////////////////////////////// Views

   private DemoNameView nameView;
   private DemoAgeView ageView;
   
   //////////////////////////////////////////////// Startup

   public DemoMultiViewApp(){
      dog = new Dog();
      dog.setName("Suzie"); 
      dog.setAge(3);
      nameView = new DemoNameView(this, dog);
      ageView = new DemoAgeView(this, dog);
   }

   //////////////////////////////////////////////// Synchronize

   public void updateAllViews(){
      nameView.updateView();
      ageView.updateView();
   };
}