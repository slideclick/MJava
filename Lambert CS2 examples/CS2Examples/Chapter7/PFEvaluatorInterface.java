import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class PFEvaluatorInterface extends JFrame{

   //////////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new PFEvaluatorInterface();
      theFrame.setSize (400, 300);
      theFrame.setVisible (true);
   }

   //////////////////////////////////////////////////////// Model

   PFEvaluatorModel evalModel = new PFEvaluatorModel ();
   
   //////////////////////////////////////////////////////// View

   // Create controls 
   private JLabel    lbSource   = new JLabel("Expression");
   private JLabel    lbResults  = new JLabel("Results");
   private JTextArea taSource   = new JTextArea("");
   private JTextArea taResults  = new JTextArea("");
   private JButton   btEvaluate = new JButton("Evaluate");
   
   public PFEvaluatorInterface(){

      // Set title and initialize
      setTitle ("Postfix Expression Evaluator");
      taResults.setEditable(false);
      taResults.setFont(new Font("Courier", Font.PLAIN, 12));


      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints
      JScrollPane spSource = new JScrollPane(taSource);
      JScrollPane spResults = new JScrollPane(taResults);
      layout.setConstraints(lbSource   ,1,1,1,1); 
      layout.setConstraints(spSource   ,2,1,1,1); 
      layout.setConstraints(lbResults  ,3,1,1,1); 
      layout.setConstraints(spResults  ,4,1,1,1); 
      layout.setConstraints(btEvaluate ,5,1,1,1); 

      // Add controls to container
      contentPane.add (lbSource);
      contentPane.add (lbResults);
      contentPane.add (spSource);
      contentPane.add (spResults);
      contentPane.add (btEvaluate);

      // Specify listeners
      btEvaluate.addActionListener(new EvaluateListener());
      addWindowListener(new MyWindowAdapter());   
   }

   //////////////////////////////////////////////////////// Controller

   // Button action listener
   private class EvaluateListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         String expressionString = taSource.getText();
         taResults.append ("Expression: " + 
                           evalModel.format (expressionString) + "\n");
         try{
            int value = evalModel.evaluate(expressionString);
            taResults.append ("Value     : " + value + "\n\n");
         }catch(Exception e){
            taResults.append ("Error     : " + e.getMessage() + "\n");
            taResults.append (evalModel.evaluationStatus() + "\n\n");
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