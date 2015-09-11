import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DemoFlowLayout extends JFrame{

   ////////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new DemoFlowLayout();
      theFrame.setSize (200, 125);
      theFrame.setVisible (true);
   }

   ////////////////////////////////////////////////////// Model

   private Thermometer thermo = new Thermometer();
   
   ////////////////////////////////////////////////////// View

   // Create controls 
   private JLabel     lbFahrenheit = new JLabel ("Fahrenheit");
   private JTextField tfFahrenheit = new JTextField ("212", 6);  
   private JLabel     lbCelsius    = new JLabel ("Celsius");    
   private JTextField tfCelsius    = new JTextField ("100", 6);  
   private JButton    btFtoC       = new JButton ("F to C");
   private JButton    btCtoF       = new JButton ("C to F");

   public DemoFlowLayout(){

      // Set title
      setTitle("FlowLayout Demo");

      // Create container and layout
      Container contentPane = getContentPane();
      FlowLayout layout = new FlowLayout();
      contentPane.setLayout (layout);

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
         String outStr 
                = Format.justify ('l', thermo.getCelsius(), 0, 2);
         tfCelsius.setText (outStr);      
      }
   }

   // Celsius button listener
   private class CtoFListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         String inStr = tfCelsius.getText().trim();
         double c = Double.parseDouble(inStr);
         thermo.setCelsius(c); 
         String outStr 
                = Format.justify('l', thermo.getFahrenheit(), 0, 2);
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