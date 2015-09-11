import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class ERView extends JFrame{

   //////////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new ERView();
      theFrame.setSize (300, 400);
      theFrame.setVisible (true);
   }

   //////////////////////////////////////////////////////// Model

   private ERModel erModel;
   
   //////////////////////////////////////////////////////// View

   // Create controls 
   private JLabel       lbName      = new JLabel("Patient name");
   private JTextField   tfName      = new JTextField("");
   private JRadioButton cbFair      = new JRadioButton("Fair condition");
   private JRadioButton cbCritical  = new JRadioButton("Critical condition");
   private JRadioButton cbSerious   = new JRadioButton("Serious condition");
   private JButton      btSchedule  = new JButton("Schedule");
   private JButton      btNext      = new JButton("Treat Next Patient");
   private JButton      btAll       = new JButton("Treat All Patients");
   private JTextArea    taOutput    = new JTextArea ("");

   
   public ERView(){

      // Set title and initialize
      setTitle ("Emergency Room");
      erModel = new ERModel(this, 3);
      ButtonGroup bgCondition = new ButtonGroup();
      bgCondition.add(cbFair);
      bgCondition.add(cbCritical);
      bgCondition.add(cbSerious);
      cbFair.setSelected (true);
 
      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints 
      JScrollPane spOutput = new JScrollPane(taOutput);
      layout.setConstraints(lbName     ,1,1,1,1); 
      layout.setConstraints(tfName     ,1,2,1,1); 
      layout.setConstraints(cbFair     ,2,1,1,1); 
      layout.setConstraints(cbCritical ,3,1,1,1); 
      layout.setConstraints(cbSerious  ,4,1,1,1); 
      layout.setConstraints(btSchedule ,2,2,1,1); 
      layout.setConstraints(btNext     ,3,2,1,1); 
      layout.setConstraints(btAll      ,4,2,1,1); 
      layout.setConstraints(spOutput   ,5,1,2,1); 

      // Add controls to container
      contentPane.add(lbName);
      contentPane.add(tfName);
      contentPane.add(cbFair);
      contentPane.add(cbCritical);
      contentPane.add(cbSerious);
      contentPane.add(btSchedule);
      contentPane.add(btNext);
      contentPane.add(btAll);
      contentPane.add(spOutput);
     

      // Specify listeners
      btSchedule.addActionListener(new ButtonListener());
      btNext.addActionListener(new ButtonListener());
      btAll.addActionListener(new ButtonListener());
      addWindowListener(new MyWindowAdapter());   
   }

   public void println(String s){
      taOutput.append(s + "\n");
   }

   
   public void displayErrorMessage (String msg){
      new MessageBox(this, msg, 250, 100);
   }   
   //////////////////////////////////////////////////////// Controller

   private String getPriority(){
      if (cbFair.isSelected())
         return "fair";
      else if (cbCritical.isSelected())
         return "critical";
      else
         return "serious";
   }

   // Button action listener
   private class ButtonListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         Object button= event.getSource();
         if (button== btSchedule){
            String name = tfName.getText().trim();
            if (name.equals("")){
               displayErrorMessage("You must enter a name first.");
               return;
            }
            erModel.schedule(name, getPriority());
         }else if (button== btNext)
            erModel.treatNext();
         else
            erModel.treatAll();
      }
   }

   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }
}

