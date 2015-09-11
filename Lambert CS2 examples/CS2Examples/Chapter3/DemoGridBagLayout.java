import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DemoGridBagLayout extends JFrame{

   ////////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new DemoGridBagLayout();
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
   
   public DemoGridBagLayout(){

      // Set title
      setTitle("GridBagLayout Demo");

      // Create container and layout
      Container contentPane = getContentPane();
      GridBagLayout layout = new GridBagLayout();
      contentPane.setLayout (layout);
      
      // Set basic constraints
      GridBagConstraints gbc = new GridBagConstraints();
      
      gbc.insets.bottom = 1;            
      gbc.insets.left = 2;
      gbc.insets.right = 2;
      gbc.insets.top = 1;

      gbc.weightx = 100;
      gbc.weighty = 100;
      gbc.gridwidth = 1;
      gbc.gridheight = 1;
      
      // Set label constraints
      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      gbc.gridx = 0;
      gbc.gridy = 0;
      layout.setConstraints(lbFahrenheit, gbc);
      gbc.gridx = 0;
      gbc.gridy = 1;
      layout.setConstraints(lbCelsius, gbc);

      // Set text field constraints
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.anchor = GridBagConstraints.NORTHWEST;
      gbc.gridx = 1;
      gbc.gridy = 0;
      layout.setConstraints(tfFahrenheit, gbc);
      gbc.gridx = 1;
      gbc.gridy = 1;
      layout.setConstraints(tfCelsius, gbc);

      // Set button constraints      
      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.CENTER;
      gbc.gridx = 0;
      gbc.gridy = 2;
      layout.setConstraints(btFtoC, gbc);
      gbc.gridx = 1;
      gbc.gridy = 2;
      layout.setConstraints(btCtoF, gbc);

      // Add controls to the container
      contentPane.add (lbFahrenheit);
      contentPane.add (lbCelsius);
      contentPane.add(tfFahrenheit);
      contentPane.add (tfCelsius);
      contentPane.add(btFtoC);
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