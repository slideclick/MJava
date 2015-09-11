import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class DemoEasyGridLayout extends JFrame{

   ////////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new DemoEasyGridLayout();
      theFrame.setSize (200, 125);
      theFrame.setVisible (true);
   }

   ////////////////////////////////////////////////////// Model

   private Thermometer thermo = new Thermometer();
   
   ////////////////////////////////////////////////////// View

   // Create controls 
   private JLabel     lbFahrenheit = new JLabel ("Fahrenheit");
   private JTextField tfFahrenheit = new JTextField ("212");  
   private JLabel     lbCelsius    = new JLabel ("Celsius");    
   private JTextField tfCelsius    = new JTextField ("100");  
   private JButton    btFtoC       = new JButton ("F to C");
   private JButton    btCtoF       = new JButton ("C to F");
   
   public DemoEasyGridLayout(){

      // Set title
      setTitle("EasyGridLayout Demo");

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints
      layout.setConstraints(lbFahrenheit ,1,1,1,1); 
      layout.setConstraints(tfFahrenheit ,1,2,1,1); 
      layout.setConstraints(lbCelsius    ,2,1,1,1); 
      layout.setConstraints(tfCelsius    ,2,2,1,1); 
      layout.setConstraints(btFtoC       ,3,1,1,1); 
      layout.setConstraints(btCtoF       ,3,2,1,1); 

      // Add controls to container
      contentPane.add (lbFahrenheit);
      contentPane.add (tfFahrenheit);
      contentPane.add (lbCelsius);
      contentPane.add (tfCelsius);
      contentPane.add (btFtoC);
      contentPane.add (btCtoF);

      // Specify listeners
      btFtoC.addActionListener(new FtoCListener());
      btCtoF.addActionListener(new CtoFListener());
      addWindowListener(new MyWindowAdapter());   
   }

   ////////////////////////////////////////////////////// Controller

   // Fahrenheit button listener
   private class FtoCListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         String inStr = tfFahrenheit.getText().trim();
         double f = Double.parseDouble(inStr);
         thermo.setFahrenheit(f); 
         String outStr = Format.justify ('l', thermo.getCelsius(), 0, 2);
         tfCelsius.setText (outStr);      
      }
   }

   // Celsius button listener
   private class CtoFListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         String inStr = tfCelsius.getText().trim();
         double c = Double.parseDouble(inStr);
         thermo.setCelsius(c); 
         String outStr = Format.justify('l', thermo.getFahrenheit(), 0, 2);
         tfFahrenheit.setText (outStr);      
      }
   }

   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }
}

