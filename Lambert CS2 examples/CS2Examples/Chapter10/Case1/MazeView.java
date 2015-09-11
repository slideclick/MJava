import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class MazeView extends JFrame{
    
   //////////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new MazeView();
      theFrame.setSize (300, 300);
      theFrame.setVisible (true);
   }

   //////////////////////////////////////////////////////// Model

   private MazeModel model;
   
   //////////////////////////////////////////////////////// View

   // Create controls 
   private JTextArea taMaze  = new JTextArea("");
   private JButton   btSolve = new JButton("Solve");
   private JButton   btSet   = new JButton("Set maze");  

   public MazeView(){
      // Set title and initialize
      setTitle("Maze Solver");
      model = new MazeModel(this);
      taMaze.setText(model.getMaze());
      taMaze.setFont(new Font("Courier", Font.PLAIN, 10));

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints 
      JScrollPane spMaze = new JScrollPane(taMaze);
      layout.setConstraints(spMaze ,1,1,2,1); 
      layout.setConstraints(btSolve,5,1,1,1);
      layout.setConstraints(btSet  ,5,2,1,1);

      // Add controls to container
      contentPane.add(spMaze);
      contentPane.add(btSolve);
      contentPane.add(btSet);
  
      // Specify listeners
      btSolve.addActionListener(new MyButtonListener());
      btSet.addActionListener(new MyButtonListener());
      addWindowListener(new MyWindowAdapter());       
   }
  
   public void printMaze(){
      taMaze.setText(model.getMaze());
   }

   //////////////////////////////////////////////////////// Controller

   // Button action listener
   private class MyButtonListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         Object buttonObj = event.getSource();
         if (buttonObj == btSolve)
            if (model.solveMaze())
               new MessageBox(MazeView.this, "Solved", 150, 100);
            else
               new MessageBox(MazeView.this, "No path out", 150, 100);
         else if (buttonObj == btSet)
            model.setMaze(taMaze.getText());
      }
   }

   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }
}