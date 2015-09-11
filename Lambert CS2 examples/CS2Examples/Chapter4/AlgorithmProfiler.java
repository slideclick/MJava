import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class AlgorithmProfiler extends JFrame{

   //////////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new AlgorithmProfiler();
      theFrame.setSize(300, 500);
      theFrame.setVisible(true);
   }

   //////////////////////////////////////////////////////// Model

   private int[] array;
   private int targetItem, targetPosition;
   
   //////////////////////////////////////////////////////// View

   // Create controls 
   private JLabel       lbArraySize    = new JLabel("Array size");
   private IntegerField ifArraySize    = new IntegerField(100);
   private JLabel       lbTargetItem   = new JLabel("Target Item");
   private IntegerField ifTargetItem   = new IntegerField(0);
   private JCheckBox    cbDuplicates   = new JCheckBox("Allow Duplicates");
   private JButton      btRandomize    = new JButton("Randomize");
   private JButton      btDisplayArray = new JButton("Display Array");
   private JLabel       lbComparisons  = new JLabel("Comparisons");
   private JTextField   tfComparisons  = new IntegerField(0);
   private JLabel       lbExchanges    = new JLabel("Exchanges");
   private JTextField   tfExchanges    = new IntegerField(0);
   private JLabel       lbTime         = new JLabel("Time in msec");
   private JTextField   tfTime         = new IntegerField(0);
   private JLabel       lbPosition     = new JLabel("Position of target");
   private JTextField   tfPosition     = new IntegerField(0);
   private JTextArea    taDisplayArray = new JTextArea("");

   private JMenu     muAlgorithm       = new JMenu("Algorithm");      
   private JMenuItem miSelectionSort   = new JMenuItem("Selection Sort");
   private JMenuItem miBubbleSort      = new JMenuItem("Bubble Sort");
   private JMenuItem miInsertionSort   = new JMenuItem("Insertion Sort");
   private JMenuItem miLinearSearch    = new JMenuItem("Linear Search");
   private JMenuItem miBinarySearch    = new JMenuItem("Binary Seasrch");

   public AlgorithmProfiler(){

      // Initialize
      tfComparisons.setEditable(false);
      tfExchanges.setEditable(false);
      tfPosition.setEditable(false);
      tfTime.setEditable(false);
      cbDuplicates.setSelected(true);           // Allow duplicates by default
      setTitle("Algorithm Profiler");
      targetItem = 0;
      targetPosition = -1;
      array = Algorithms.getRandomArray(100, true);  // Default size of 100

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints
      JScrollPane spDisplayArea = new JScrollPane(taDisplayArray);
      layout.setConstraints(lbArraySize    ,1,1,1,1);
      layout.setConstraints(ifArraySize    ,1,2,1,1);
      layout.setConstraints(lbTargetItem   ,2,1,1,1);
      layout.setConstraints(ifTargetItem   ,2,2,1,1);
      layout.setConstraints(cbDuplicates   ,3,1,1,1);
      layout.setConstraints(btRandomize    ,3,2,1,1);
      layout.setConstraints(btDisplayArray ,4,2,1,1);
      layout.setConstraints(lbComparisons  ,5,1,1,1);
      layout.setConstraints(tfComparisons  ,5,2,1,1);
      layout.setConstraints(lbExchanges    ,6,1,1,1);
      layout.setConstraints(tfExchanges    ,6,2,1,1);
      layout.setConstraints(lbTime         ,7,1,1,1);
      layout.setConstraints(tfTime         ,7,2,1,1);
      layout.setConstraints(lbPosition     ,8,1,1,1);
      layout.setConstraints(tfPosition     ,8,2,1,1);
      layout.setConstraints(spDisplayArea  ,9,1,2,1);

      // Add controls to container
      contentPane.add(lbArraySize);   
      contentPane.add(ifArraySize); 
      contentPane.add(lbTargetItem);  
      contentPane.add(ifTargetItem);  
      contentPane.add(cbDuplicates);  
      contentPane.add(btRandomize);   
      contentPane.add(btDisplayArray);
      contentPane.add(lbComparisons); 
      contentPane.add(tfComparisons); 
      contentPane.add(lbExchanges);  
      contentPane.add(tfExchanges);  
      contentPane.add(lbTime);      
      contentPane.add(tfTime);       
      contentPane.add(lbPosition);    
      contentPane.add(tfPosition);   
      contentPane.add(spDisplayArea);

      // Create the menu bar and add menus and menu items
      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);
      menuBar.add(muAlgorithm);
      muAlgorithm.add(miSelectionSort);
      muAlgorithm.add(miBubbleSort);
      muAlgorithm.add(miInsertionSort);
      muAlgorithm.add(miLinearSearch);
      muAlgorithm.add(miBinarySearch);

      // Specify listeners
      miSelectionSort.addActionListener(new MenuActionListener());
      miBubbleSort.addActionListener(new MenuActionListener()); 
      miInsertionSort.addActionListener(new MenuActionListener()); 
      miLinearSearch.addActionListener(new MenuActionListener()); 
      miBinarySearch.addActionListener(new MenuActionListener()); 
      btRandomize.addActionListener(new ButtonActionListener());
      btDisplayArray.addActionListener(new ButtonActionListener()); 
      addWindowListener(new MyWindowAdapter());  
   }

   //////////////////////////////////////////////////////// Controller

   private void updateDisplay(String type){
      if (type == "sort"){
         tfComparisons.setText("" + Algorithms.comparisons);
         tfExchanges.setText("" + Algorithms.exchanges);
         tfTime.setText("" + Algorithms.runningTime);
         tfPosition.setText("Not applicable");
      }else{ // "search"
         tfComparisons.setText("" + Algorithms.comparisons);
         tfExchanges.setText("Not applicable");
         tfTime.setText("Not applicable");
         tfPosition.setText("" + targetPosition);
      }
   }

   // Menu action listener
   private class MenuActionListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         String type = "sort";
         Object menuItem= e.getSource();
         targetItem = ifTargetItem.getNumber();
         if (menuItem== miSelectionSort)
            Algorithms.selectionSort(array);
         else if (menuItem== miBubbleSort)
            Algorithms.bubbleSort(array);
         else if (menuItem== miInsertionSort)
            Algorithms.insertionSort(array);
         else if (menuItem== miLinearSearch){
            type = "search";
            targetPosition = Algorithms.linearSearch(targetItem,
                                                     array, array.length);
         }else if (menuItem== miBinarySearch){
            type = "search";
            targetPosition = Algorithms.binarySearch(targetItem,
                                    array, array.length);
         }
         updateDisplay(type);
      }
   }
   
   // Button action listener
   private class ButtonActionListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         Object button= e.getSource();
         if (button== btRandomize){
            array = Algorithms.getRandomArray(ifArraySize.getNumber(),
                                              cbDuplicates.isSelected());
            taDisplayArray.setText("");
         }else 
            taDisplayArray.setText(Algorithms.toString(array));
      }
   }

   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }
}