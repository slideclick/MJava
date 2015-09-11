import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import lamborne.*;
import java.util.*;
import ioutil.*;

/* Format of input in text area
   Each line of input should consist of one of the three following:
     1) blank line
     2) vertexLabel                            
     3) vertexLabel1>vertexLabel2:weight
   Meaning:
     1) blank lines are ignored
     2) a vertex with the indicated label is added to the graph
        unless one is already present, in which case the line is
        ignored
     3) an edge from vertexLabel1 to vertexLabel2, with the indicated 
        weight,is added; vertices with the indicated labels are added 
        unless they are already present; the line ignored if there is 
        already an edge from vertexLabel1 to vertexLabel2
*/
   
public class GraphDemoView extends JFrame{

   //////////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new GraphDemoView();
      theFrame.setSize (400, 350);
      theFrame.setVisible (true);
   }

   //////////////////////////////////////////////////////// Model

   GraphDemoModel model;
   
   //////////////////////////////////////////////////////// View

   // Create controls 
   private JLabel       lbStructure    = new JLabel("Structure of graph");
   private JLabel       lbResults      = new JLabel("Results");
   private JTextArea    taStructure    = new JTextArea("");
   private JTextArea    taResults      = new JTextArea("");
   private JLabel       lbType         = new JLabel("Type of graph");
   private JRadioButton rbDirected     = new JRadioButton("Directed");
   private JRadioButton rbUndirected   = new JRadioButton("Undirected");
   private JLabel       lbStartVertex  = new JLabel("Starting Vertex");
   private JTextField   tfStartVertex  = new JTextField("");
   private JButton      btShortestPath = new JButton(
	                                          "Single-Source Shortest Path");
   private JButton      btMinSpanTree  = new JButton(
	                                          "Minimum Spanning Tree");
   private JButton      btTopoSort     = new JButton("Topological Sort");  

   public GraphDemoView(){

      // Set title and model. Initialize controls.
      setTitle("Graph Demo");
      model = new GraphDemoModel();
      taResults.setEditable(false);

      // Select the default radio button and add buttons to a group
      rbDirected.setSelected(true);
      ButtonGroup bgGraphType = new ButtonGroup();
      bgGraphType.add(rbDirected);
      bgGraphType.add(rbUndirected);

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints 
      JScrollPane spStructure = new JScrollPane(taStructure);
      JScrollPane spResults = new JScrollPane(taResults);
      layout.setConstraints(lbStructure    ,1,1,1,1);
      layout.setConstraints(lbResults      ,1,2,1,1);
      layout.setConstraints(spStructure    ,2,1,1,1);
      layout.setConstraints(spResults      ,2,2,1,1);
      layout.setConstraints(lbType         ,3,1,1,1);
      layout.setConstraints(rbDirected     ,4,1,1,1);
      layout.setConstraints(rbUndirected   ,5,1,1,1);
      layout.setConstraints(lbStartVertex  ,6,1,1,1);
      layout.setConstraints(tfStartVertex  ,6,2,1,1);
      layout.setConstraints(btShortestPath ,3,2,1,1);
      layout.setConstraints(btMinSpanTree  ,4,2,1,1);
      layout.setConstraints(btTopoSort     ,5,2,1,1);

      // Add controls to container
      contentPane.add(lbStructure);
      contentPane.add(lbResults);
      contentPane.add(spStructure);
      contentPane.add(spResults);
      contentPane.add(lbType);
      contentPane.add(rbDirected);
      contentPane.add(rbUndirected);
      contentPane.add(lbStartVertex);
      contentPane.add(tfStartVertex);
      contentPane.add(btShortestPath);
      contentPane.add(btMinSpanTree);
      contentPane.add(btTopoSort);

      // Specify listeners
      btShortestPath.addActionListener(new MyButtonListener());
      btMinSpanTree.addActionListener(new MyButtonListener());
      btTopoSort.addActionListener(new MyButtonListener());
      addWindowListener(new MyWindowAdapter());   
   }
      
   //////////////////////////////////////////////////////// Controller
   
   private void messageBox(String str){
      new MessageBox(this, str, 350, 150);
   }

   private void getInputData(){
      String graphString = taStructure.getText().trim();
      model.createGraph(graphString, rbDirected.isSelected());
   }
   
   private void findShortestPath(String startLabel){
      Object[][] paths = model.getSingleShortestPaths(startLabel);
      if (paths == null){
         messageBox ("Starting vertex missing or not in graph");
         return;
      }
      taResults.setText("");
      for (int row = 0; row < paths.length; row++){
         Vertex toVert = (Vertex)paths[row][0];
         double distance = ((Double)paths[row][1]).doubleValue();
         Vertex pred = (Vertex)paths[row][2];
         String str = toVert.toString() + ":" + distance;
         if (pred != null)
            str = str + " from pred " + pred.toString();
         str = str + "\n";
         taResults.append(str);
      }
   }
   
   private void findMinSpanTree(String startLabel){
      java.util.List edges = model.getMinimumSpanTree(startLabel);
      if (edges == null){
         messageBox ("Starting vertex missing or not in graph");
         return;
      }
      taResults.setText("Minimum spanning tree:\n");
      for (int i = 0; i < edges.size(); i++){
         GraphEdge e = (GraphEdge)edges.get(i);
         taResults.append(toString(e) + "\n");
      }
   }
   
   private String toString(GraphEdge e){
      Vertex fv = e.getFromVertex();
      Vertex tv = e.getToVertex();
      double weight = e.getWeight();
      return fv.toString() + ">" + 
             tv.toString() + ":" + 
             weight;
   }
   
   private void topologicalSort(){
      if (!rbDirected.isSelected())
         messageBox(
			"Error: topological sorting \nrequires a directed graph");
      else{
         java.util.List vertices = model.getSortedVertices();
         taResults.setText("Topological order of vertices:\n");
         Iterator iter = vertices.iterator();
         while (iter.hasNext()){
            Vertex v = (Vertex)(iter.next());
            taResults.append(v.toString() + "\n");
         }
      }
   }

   // Button action listener
   private class MyButtonListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         getInputData();
         if (! model.graphValid()) 
            messageBox (model.graphStatus());
         else{
            String startLabel = tfStartVertex.getText().trim();
            Object buttonObj = event.getSource();
            if (buttonObj == btShortestPath)
               findShortestPath(startLabel);
            else if (buttonObj == btMinSpanTree)
               findMinSpanTree(startLabel);
            else if (buttonObj == btTopoSort)
               topologicalSort();
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


