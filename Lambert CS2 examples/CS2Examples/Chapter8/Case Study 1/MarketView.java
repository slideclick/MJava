import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class MarketView extends JFrame{

   //////////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new MarketView();
      theFrame.setSize (300, 400);
      theFrame.setVisible (true);
   }

   //////////////////////////////////////////////////////// Model

   private MarketModel model;
   
   //////////////////////////////////////////////////////// View

   // Create controls 
   private JLabel       lbLengthOfSimulation = new JLabel("Total running time");
   private JLabel       lbAverageTimePerCus  = new JLabel("Average time per customer");
   private JLabel       lbProbability        = new JLabel("Probability of new arrival");
   private IntegerField ifLengthOfSimulation = new IntegerField(0);
   private IntegerField ifAverageTimePerCus  = new IntegerField(0);
   private DoubleField  dfProbability        = new DoubleField(0);
   private JButton      btRun                = new JButton("Run");
   private JTextArea    taOutput             = new JTextArea("");

   
   public MarketView(){

      // Set title and initialize
      setTitle("Market Simulator");
      taOutput.setEditable(false);
      taOutput.setFont(new Font("Courier", Font.PLAIN, 12));

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints
      JScrollPane spOutput = new JScrollPane(taOutput);
      layout.setConstraints(lbLengthOfSimulation ,1,1,1,1); 
      layout.setConstraints(lbAverageTimePerCus  ,2,1,1,1); 
      layout.setConstraints(lbProbability        ,3,1,1,1); 
      layout.setConstraints(ifLengthOfSimulation ,1,2,1,1); 
      layout.setConstraints(ifAverageTimePerCus  ,2,2,1,1); 
      layout.setConstraints(dfProbability        ,3,2,1,1); 
      layout.setConstraints(btRun                ,4,1,2,1); 
      layout.setConstraints(spOutput             ,5,1,2,1); 

      // Add controls to container
      contentPane.add (lbLengthOfSimulation);
      contentPane.add (lbAverageTimePerCus);
      contentPane.add (lbProbability);
      contentPane.add (ifLengthOfSimulation);
      contentPane.add (ifAverageTimePerCus);
      contentPane.add (dfProbability);
      contentPane.add (btRun);
      contentPane.add (spOutput);

      // Specify listeners
      btRun.addActionListener(new RunListener());
      addWindowListener(new MyWindowAdapter());   
   }

   //////////////////////////////////////////////////////// Controller

   // Button action listener
   private class RunListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         int lengthOfSimulation         = ifLengthOfSimulation.getNumber();
         int averageTimePerCus          = ifAverageTimePerCus.getNumber();
         double probabilityOfNewArrival = dfProbability.getNumber();
      
         if (lengthOfSimulation < 1 || lengthOfSimulation > 1000)
            new MessageBox (MarketView.this,
                            "Running time must be an integer greater than 0" +
                            "\nand less than or equal to 1000", 350, 150);
         else if (averageTimePerCus <= 0 || 
                    averageTimePerCus >= lengthOfSimulation)
            new MessageBox (MarketView.this,
                            "Average time per customer must be an integer" +
                            "\ngreater than 0 and less than running time", 350, 150);
         else if (probabilityOfNewArrival <= 0 || 
                  probabilityOfNewArrival > 1)
            new MessageBox (MarketView.this,
                            "Probability must be geater than 0" +
                            "\nand less than or equal to 1", 350, 150);
         else{
            model = new MarketModel(lengthOfSimulation, averageTimePerCus, 
                                    probabilityOfNewArrival); 
            String results = model.runSimulation();
            taOutput.append(results + "\n\n");
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