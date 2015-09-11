// GraphDemoModel
// (c) 2000 Ken Lambert and Martin Osborne


import lamborne.*;
import java.util.*;

public class GraphDemoModel extends Object{

   private Graph graph;
   private String graphStatus;

   public GraphDemoModel(){
      graph = null;
      graphStatus = "No graph";
   }
   
   public boolean graphValid(){
      return graphStatus.equals ("Graph valid");
   }

   public String graphStatus(){
      return graphStatus;
   }
   
   public Object[][] getSingleShortestPaths(String startLabel){
      // Complete this as an exercise
      return null;
   }

   public java.util.List getMinimumSpanTree(String startLabel){
      // Complete this as an exercise
      return null;
   }
    
   public String toString(){
      if (graphStatus.equals ("Graph valid"))
         return graph.toString();
      else
         return graphStatus;
   }

   public void createGraph(String rep, 
                           boolean directed){
      if (directed)
         graph = new LinkedDirectedGraph();
      else
         graph = new LinkedUndirectedGraph();
      if (rep.equals("")){
         graphStatus = "No graph";
         return;
      }
      StringTokenizer lines = new StringTokenizer(rep, "\n");
      while (lines.hasMoreTokens()){
         String line = lines.nextToken().trim();
         if (line.equals("")) continue;
         StringTokenizer words = new StringTokenizer(line, ">:");
         if (words.countTokens() == 1){
            // One token in line, assume it is a vertex
            String label = words.nextToken();
            if (! graph.containsVertex(label))
               graph.addVertex(label);
            continue;
         }else if (words.countTokens() != 3){
            // Wrong number of tokens in line. Out of here
            graphStatus = "Wrong number of tokens in line: '" 
                          + line + "'";
            return;
         }
         // Three tokens in line, assume it is vertex>vertex:weight
         String label1 = words.nextToken();
         String label2 = words.nextToken();
         String weightStr = words.nextToken();
         double weight = 0;
         try{
            weight = Double.valueOf(weightStr).doubleValue();
         }
         catch(NumberFormatException e){
            graphStatus = "Third token in line must be a number: '" 
                          + line + "'";
            return;
         }
         if (! graph.containsVertex(label1))
            graph.addVertex(label1);
         if (! graph.containsVertex(label2))
            graph.addVertex(label2);
         if (! graph.containsEdge(label1, label2))
            graph.addEdge(label1, label2, new Weight(weight));
      }
      if (graph.isEmpty())
         graphStatus = "No graph";
      else
         graphStatus = "Graph valid";
   }

   
   public List getSortedVertices(){
      return GraphDemoModel.sort(graph);
   }

   public static List sort (Graph g){
      List list = new LinkedList();
      g.clearVertexMarks();
      Iterator vertices = g.vertices();
      while (vertices.hasNext()){
         Vertex v = (Vertex)vertices.next();
         if (! v.isMarked())
            dfs (g, v, list);
      }
      return list;
   }

   private static void dfs (Graph g, Vertex v, List list) {
      v.setMark();
      Iterator neighbors = g.neighboringVertices(v);
      while (neighbors.hasNext()){
         Vertex w = (Vertex)neighbors.next();
         if (! w.isMarked())
            dfs (g, w, list);
      }
      list.add (0, v);    // add at beginning of list
   }

}

