import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class ParserInterface extends JFrame{

   //////////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new ParserInterface();
      theFrame.setSize (400, 350);
      theFrame.setVisible (true);
   }

   //////////////////////////////////////////////////////// Model

   private Parser parser = new Parser();
   
   //////////////////////////////////////////////////////// View

   // Create controls 
   private JLabel    lbSource  = new JLabel("Expression");
   private JLabel    lbResults = new JLabel("Results");
   private JTextArea taSource  = new JTextArea("");
   private JTextArea taResults = new JTextArea("");
   private JButton   btParse   = new JButton("Parse");

   public ParserInterface(){

      // Set title
      setTitle ("Parser");

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints 
      JScrollPane spSource = new JScrollPane(taSource);
      JScrollPane spResults = new JScrollPane(taResults);
      layout.setConstraints(lbSource  , 1,1,1,1);
      layout.setConstraints(spSource  , 2,1,1,1);
      layout.setConstraints(lbResults , 3,1,1,1);
      layout.setConstraints(spResults , 4,1,1,1);
      layout.setConstraints(btParse   , 5,1,1,1);

      // Add controls to container
      contentPane.add(lbSource);
      contentPane.add(lbResults);
      contentPane.add(spSource);
      contentPane.add(spResults);
      contentPane.add(btParse);

      // Specify listeners
      btParse.addActionListener(new MyButtonListener());
      addWindowListener(new MyWindowAdapter());   
   }

   //////////////////////////////////////////////////////// Controller

   // Button action listener
   private class MyButtonListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         try{
            parser.parse(taSource.getText());
         }catch (Exception e){}
         taResults.append ("\n" + parser + "\n");
      }
   }

   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }
}
