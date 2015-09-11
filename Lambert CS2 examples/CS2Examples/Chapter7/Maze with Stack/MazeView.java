import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class MazeView extends JFrame{
    
   //////////////////////////////////////////////////////// main

   public static void main (String[] args){
      Frame frm = new MazeView();
      frm.setSize(300, 300);
      frm.setVisible(true);
   }


   //////////////////////////////////////////////////////// Model

   private MazeModel model;

   //////////////////////////////////////////////////////// View

   // Create controls 

   private JTextArea outputArea  = new JTextArea("");
   private JButton   solveButton = new JButton("Solve");
   private JButton   setButton   = new JButton("Set maze");
   
   public MazeView(){

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);

      // Set constraints
      JScrollPane spOutputArea = new JScrollPane(outputArea);
      layout.setConstraints(spOutputArea, 1,1,3,7);
      layout.setConstraints(solveButton , 8,1,1,1);
      layout.setConstraints(setButton   , 8,2,1,1);

      // Add controls to container
      contentPane.add(spOutputArea);   
      contentPane.add(solveButton); 
      contentPane.add(setButton);  

      // Specify listeners
      solveButton.addActionListener(new SolveActionListener());
      setButton.addActionListener(new SetActionListener()); 

      // Initialize
      setTitle("Maze Solver");
      model = new MazeModel(this);
      outputArea.setText(model.getMaze());
   }
   
   //////////////////////////////////////////////////////// Controller

   private class SolveActionListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         String message = "";
         if (model.solveMaze())
            message = "Solved";
         else
            message = "No path out";
         new MessageBox(MazeView.this, message, 200, 100);
      }
   }

   private class SetActionListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         model.setMaze(outputArea.getText());
      }
   }
   
   public void printMaze(){
      outputArea.setText(model.getMaze());
   }
   
   public void println(String s){
      outputArea.append(s + "/n");
   }

}