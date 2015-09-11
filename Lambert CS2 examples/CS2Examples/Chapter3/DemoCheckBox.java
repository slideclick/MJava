import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class DemoCheckBox extends JFrame{

   //////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new DemoCheckBox();
      theFrame.setSize(300, 250);
      theFrame.setVisible(true);
   }

   //////////////////////////////////////////////////// Model

   // none
   
   //////////////////////////////////////////////////// View

   // Create controls 

   // Fields to display states of boxes and buttons
   private JLabel       lbCheck  = new JLabel("Check Box State");
   private JLabel       lbRadio  = new 
                                   JLabel("Radio Button State");
   private JTextField   tfCheck  = new JTextField("");
   private JTextField   tfRadio  = new JTextField("");

   // Check boxes
   private JCheckBox    cbDriver    = new JCheckBox("Driver");
   private JCheckBox    cbPassenger = new JCheckBox("Passenger");

   // Radio buttons
   private JRadioButton rbMarried  = new JRadioButton("Married");
   private JRadioButton rbSingle   = new JRadioButton("Single");
   private JRadioButton rbDivorced = new JRadioButton("Divorced");

   // Command button
   private JButton      btGetStates = new JButton("Get States");

   public DemoCheckBox(){

      // Set title
      setTitle("Check Box and Radio Button Demo");

      // Mark the default check box and radio button
      cbDriver.setSelected(true);
      rbSingle.setSelected(true);

      // Add the radio buttons to a button group
      ButtonGroup bgMaritalStatus = new ButtonGroup();
      bgMaritalStatus.add(rbMarried);
      bgMaritalStatus.add(rbSingle);
      bgMaritalStatus.add(rbDivorced);

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout(layout);
      
      // Set constraints
      layout.setConstraints(lbCheck     ,1,1,1,1);
      layout.setConstraints(lbRadio     ,2,1,1,1);
      layout.setConstraints(tfCheck     ,1,2,1,1);
      layout.setConstraints(tfRadio     ,2,2,1,1);
      layout.setConstraints(cbDriver    ,3,1,1,1);
      layout.setConstraints(cbPassenger ,4,1,1,1);
      layout.setConstraints(rbMarried   ,3,2,1,1);
      layout.setConstraints(rbSingle    ,4,2,1,1);
      layout.setConstraints(rbDivorced  ,5,2,1,1);
      layout.setConstraints(btGetStates ,6,1,2,1);

      // Add controls to container
      contentPane.add(lbCheck);
      contentPane.add(lbRadio);
      contentPane.add(tfCheck);
      contentPane.add(tfRadio);
      contentPane.add(cbDriver);
      contentPane.add(cbPassenger);
      contentPane.add(rbMarried);
      contentPane.add(rbSingle);
      contentPane.add(rbDivorced);
      contentPane.add(btGetStates);

      // Specify listeners
      btGetStates.addActionListener(new GetStatesListener());
      addWindowListener(new MyWindowAdapter());   
   }

   //////////////////////////////////////////////////// Controller

   //Get States button listener
   private class GetStatesListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         String cbStr = "", rbStr = "";
         if (cbDriver.isSelected())
            cbStr = "Driver ";
         if (cbPassenger.isSelected())
            cbStr = cbStr + "Passenger";
         tfCheck.setText(cbStr);
         if (rbMarried.isSelected())
            rbStr = "Married";
         else if (rbDivorced.isSelected())
            rbStr = "Divorced";
         else if (rbSingle.isSelected())
            rbStr = "Single";
         tfRadio.setText(rbStr);
      }
   }

   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }
}