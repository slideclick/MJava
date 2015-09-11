import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class DemoCombineListeners extends JFrame{

   //////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new DemoCombineListeners();
      theFrame.setSize (200, 125);
      theFrame.setVisible (true);
   }

   //////////////////////////////////////////////////// Model

   private Thermometer thermo = new Thermometer();
   
   //////////////////////////////////////////////////// View

   // Create controls 
   private JLabel      lbFahrenheit = new JLabel ("Fahrenheit");
   private DoubleField dfFahrenheit = new DoubleField (212);  
   private JLabel      lbCelsius    = new JLabel ("Celsius");    
   private DoubleField dfCelsius    = new DoubleField (100);  
   private JButton     btFtoC       = new JButton ("F to C");
   private JButton     btCtoF       = new JButton ("C to F");
   
   public DemoCombineListeners(){

      // Set title
      setTitle("DoubleField Demo");

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints
      layout.setConstraints(lbFahrenheit ,1,1,1,1); 
      layout.setConstraints(dfFahrenheit ,1,2,1,1); 
      layout.setConstraints(lbCelsius    ,2,1,1,1); 
      layout.setConstraints(dfCelsius    ,2,2,1,1); 
      layout.setConstraints(btFtoC       ,3,1,1,1); 
      layout.setConstraints(btCtoF       ,3,2,1,1); 

      // Add controls to container
      contentPane.add (lbFahrenheit);
      contentPane.add (dfFahrenheit);
      contentPane.add (lbCelsius);
      contentPane.add (dfCelsius);
      contentPane.add (btFtoC);
      contentPane.add (btCtoF);

      // Specify listeners
      btFtoC.addActionListener(new CombinedListener());
      btCtoF.addActionListener(new CombinedListener());
      addWindowListener(new MyWindowAdapter());   
   }

   //////////////////////////////////////////////////// Controller

   // Combined button listener
   private class CombinedListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         if (event.getSource() == btFtoC){
            double f = dfFahrenheit.getNumber();
            thermo.setFahrenheit(f); 
            dfCelsius.setPrecision(2);
            dfCelsius.setNumber(thermo.getCelsius()); 
         }else{
            double c = dfCelsius.getNumber();
            thermo.setCelsius(c); 
            dfFahrenheit.setPrecision(2);
            dfFahrenheit.setNumber(thermo.getFahrenheit());      
         }
      }
   }

   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }
}