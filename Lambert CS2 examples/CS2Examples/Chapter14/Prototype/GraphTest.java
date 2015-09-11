import lamborne.*;
import java.util*;

public class GraphTest{

   public static void main(String[] args){

      // Create a directed graph using an adjacency list
      Graph g = new LinkedDirectedGraph();

      // Add vertices labeled A, B, and C to the graph and print it      
      g.addVertex ("A");
      g.addVertex ("B");
      g.addVertex ("C");
      System.out.println(g);

      // Insert edges with weight 2.5 and print the graph
      g.addEdge ("A", "B", new Weight(2.5));
      g.addEdge ("B", "C", new Weight(2.5));
      g.addEdge ("C", "B", new Weight(2.5));
      System.out.println(g);

      // Mark all the vertices
      Iterator vertices = g.vertices();
      while (vertices.hasNext())
         ((GraphVertex)vertices.next()).setMark(); 

      // Print the vertices adjacent to vertex B
      GraphVertex v = g.getVertex("B");
      Iterator BNeighbors = g.neighboringVertices(v);
      while (BNeighbors.hasNext())
         System.out.println(BNeighbors.next());

      // Print the edges out of vertex B
      Iterator BEdges = g.incidentEdges(v);
      while (BEdges.hasNext())
         System.out.println(BEdges.next());
   }
}
