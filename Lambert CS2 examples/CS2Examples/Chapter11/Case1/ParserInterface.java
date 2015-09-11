import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class ParserInterface extends JFrame{

   //////////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new ParserInterface();
      theFrame.setSize (450, 450);
      theFrame.setVisible (true);
   }

   //////////////////////////////////////////////////////// Model

   private Parser parser;
   private ExpressionTree tree;
   
   //////////////////////////////////////////////////////// View

   // Create controls 
   private JLabel    lbSource   = new JLabel    ("Expression");
   private JLabel    lbResults  = new JLabel    ("Results");
   private JTextArea taSource   = new JTextArea ("");
   private JTextArea taResults  = new JTextArea ("");
   private JButton   btParse    = new JButton   ("Parse");
   private JButton   btEvaluate = new JButton   ("Evaluate");
   private JButton   btPrefix   = new JButton   ("Prefix");
   private JButton   btPostfix  = new JButton   ("Postfix");
   private JButton   btInfix    = new JButton   ("Infix");

   public ParserInterface()
   {
      // Set title and initialize
      setTitle ("Parser");
      parser = new Parser ();
      tree = null;

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints 
      JScrollPane spSource = new JScrollPane(taSource);
      JScrollPane spResults = new JScrollPane(taResults);
      layout.setConstraints(lbSource   , 1, 1, 1, 1);
      layout.setConstraints(spSource   , 2, 1, 5, 1);
      layout.setConstraints(lbResults  , 3, 1, 1, 1);
      layout.setConstraints(spResults  , 4, 1, 5, 1);
      layout.setConstraints(btParse    , 5, 1, 1, 1);
      layout.setConstraints(btEvaluate , 5, 2, 1, 1);
      layout.setConstraints(btPrefix   , 5, 3, 1, 1);
      layout.setConstraints(btPostfix  , 5, 4, 1, 1);
      layout.setConstraints(btInfix    , 5, 5, 1, 1);

      // Add controls to container
      contentPane.add(lbSource);
      contentPane.add(lbResults);
      contentPane.add(spSource);
      contentPane.add(spResults);
      contentPane.add(btParse);
      contentPane.add(btEvaluate);
      contentPane.add(btPrefix);
      contentPane.add(btPostfix);
      contentPane.add(btInfix);

      // Specify listeners
      btParse.addActionListener(new MyButtonListener());
      btEvaluate.addActionListener(new MyButtonListener());
      btPrefix.addActionListener(new MyButtonListener());
      btPostfix.addActionListener(new MyButtonListener());
      btInfix.addActionListener(new MyButtonListener());
      addWindowListener(new MyWindowAdapter());   
   }

   //////////////////////////////////////////////////////// Controller

   private void parseExpression(){
      try{
         tree = parser.parse(taSource.getText());
      }catch (Exception e){
         tree = null;
      }
      taResults.append ("\n" + parser + "\n");
   }

   private void evaluateExpression(){
      if (tree == null)
         taResults.append ("Sorry: You must first parse an expression\n");
      else
         taResults.append ("Expression value   = " + tree.evaluate() + "\n");
   }

   private void displayExpressionPrefix(){
      if (tree == null)
         taResults.append ("Sorry: You must first parse an expression\n");
      else
         taResults.append ("Expression prefix  = " + tree.prefix() + "\n");
   }

   private void displayExpressionPostfix(){
      if (tree == null)
         taResults.append ("Sorry: You must first parse an expression\n");
      else
         taResults.append ("Expression postfix = " + tree.postfix() + "\n");
   }

   private void displayExpressionInfix(){
      if (tree == null)
         taResults.append ("Sorry: You must first parse an expression\n");
      else
         taResults.append ("Expression infix   = " + tree.infix() + "\n");
   }

   // Button action listener
   private class MyButtonListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         Object buttonObj = event.getSource();
         if (buttonObj == btParse)
            parseExpression();
         else if (buttonObj == btEvaluate)
            evaluateExpression();
         else if (buttonObj == btPrefix)
            displayExpressionPrefix();
         else if (buttonObj == btPostfix)
            displayExpressionPostfix();
         else 
            displayExpressionInfix();
      }
   }

   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }
}
