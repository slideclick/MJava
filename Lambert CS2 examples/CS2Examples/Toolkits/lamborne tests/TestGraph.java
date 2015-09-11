// TestGraph
// (c) 1999 Ken Lambert and Martin Osborne

import java.util.*;
import lamborne.*;
import BreezyGUI.*;

// NEED TO ADD TESTS FOR PRECONDITIONS

public class TestGraph{

   public static void main (String[] args){
      String str;
      Iterator iter;
      int count;
      
      Edge edge = null;
      Vertex vertex = null;
      Graph g;
      List list = new LinkedList();
      
//================ Directed begins here ==========================

      System.out.println ("================Directed Graph Tests======");                                      

      // Create a graph from an Iterator
      list.add("a");
      list.add("b");
      list.add("c");
      list.add("d");
      
      g = new LinkedDirectedGraph (list.iterator());
      System.out.println ("Expect 4 : " + g.sizeVertices()); 
      System.out.println ("Expect 4 vertices abcd and no edges: " + g); 
      
      // Change vertex label
      Vertex v = g.getVertex ("a");
      v.setLabel ("a", g);
      v.setLabel ("aaa", g);
      System.out.println ("Expect 4 vertices aaa b c d and no edges: " + g); 
      //v.setLabel ("b", g);  // This statement throws an exception
      v.setLabel ("a", g);
      
      
      g = new LinkedDirectedGraph (list);
      System.out.println ("Expect 4 : " + g.sizeVertices()); 
      System.out.println ("Expect 4 vertices abcd and no edges: " + g); 
      
      // Mark vertices, clear last mark, count marks, clear marks, count marks
      iter = g.vertices();
      while (iter.hasNext()){
        vertex = (Vertex)(iter.next());
        vertex.setMark();
      }
      vertex.clearMark();
      iter = g.vertices();
      count = 0;
      while (iter.hasNext()){
        vertex = (Vertex)(iter.next());
        if (vertex.isMarked())
            count++;
      }
      g.clearVertexMarks();
      while (iter.hasNext()){
        vertex = (Vertex)(iter.next());
        if (vertex.isMarked())
            count++;
      }
      System.out.println ("Expect 3: " + count);
      
      // Insert some edges
      g.addEdge("a","b",new Weight(1));
      g.addEdge("b","a",new Weight(2));
      g.addEdge("c","d",new Weight(3));
      
      // Test edge weights
      Edge ab = g.getEdge("a","b");
      Edge ba = g.getEdge("b","a");
      Edge cd = g.getEdge("c","d");
      System.out.println ("Expect 3: " + cd.getWeight());
      cd.setWeight(4);
      System.out.println ("Expect 4 neg# 0 pos#: " + cd.getWeight() + " "
                                                   + ab.compareTo(ba) + " "
                                                   + ab.compareTo(ab) + " "
                                                   + ba.compareTo(ab));
      
      // Mark edges, clear last mark, count marks, clear marks, count marks
      iter = g.edges();
      while (iter.hasNext()){
        edge = (Edge)(iter.next());
        edge.setMark();
      }
      edge.clearMark();
      iter = g.edges();
      count = 0;
      while (iter.hasNext()){
        edge = (Edge)(iter.next());
        if (edge.isMarked())
            count++;
      }
      g.clearEdgeMarks();
      while (iter.hasNext()){
        edge = (Edge)(iter.next());
        if (edge.isMarked())
            count++;
      }
      System.out.println ("Expect 2: " + count);
      
      // Set edge label
      Edge e = g.getEdge ("a", "b");
      e.setLabel ("abc");
      System.out.println ("Expect graph with edges abc 2 4 : " + g);

      // Clear graph
      g.clear();
        
      // Insert vertices
      g.addVertex("a");
      g.addVertex("b");
      g.addVertex("c");
      g.addVertex("d");
      g.addVertex("e");
      g.addVertex("f");
      g.addVertex("g");
      g.addVertex("h");
      g.addVertex("i");
      g.addVertex("j");
      
      // Test some vertex methods
      iter = g.vertices();
      str = "";
      while (iter.hasNext())
        str += iter.next() + " " ;   
      System.out.println ("Expect 10        : " + g.sizeVertices());
      System.out.println ("Expect a thru j  : " + str);
      System.out.println ("Expect truefalse : " + g.containsVertex("a")
                                                + g.containsVertex("x"));
      System.out.println ("Expect ab true false : " + g.getVertex("a")
                                                + g.getVertex("b") + " "
                                                + g.removeVertex("j") + " "  
                                                + g.containsVertex("j"));                                        
      
      // Test some edge methods
      System.out.println ("Expect falsefalse: " + g.containsEdge ("a", "b")
                                                + g.containsEdge ("a", "a"));
      g.addEdge("a","b", new Weight(1));
      System.out.println ("Expect 1truefalsefalse : " + g.sizeEdges()
                                                      + g.containsEdge ("a", "b")
                                                      + g.containsEdge ("b", "a")
                                                      + g.containsEdge ("a", "a"));
      g.addEdge("a","c", new Weight(2));
      g.addEdge("a","d", new Weight(3));
      g.addEdge("e","c", new Weight(4));
      g.addEdge("e","d", new Weight(5));
      g.addEdge("e","f", new Weight(6));
      g.addEdge("d","a", new Weight(7));
      
      System.out.println("Expect true false true : " +  g.removeEdge("a","c") + " "
                                                     +  g.containsEdge ("a","c") + " "
                                                     +  g.containsEdge ("d","a"));

      // Test incidentEdges
      iter = g.incidentEdges(g.getVertex("a"));
      str = "";
      while (iter.hasNext())
        str += iter.next() + " " ;   
      System.out.println ("Expect edges ab1 ad3 : " + str);

      // Test neighboringVertices
      iter = g.neighboringVertices(g.getVertex("a"));
      str = "";
      while (iter.hasNext())
        str += iter.next() + " " ;   
      System.out.println ("Expect vertices bd : " + str);
      
      GBFrame.pause();

      // Test size methods and toString for graph
      System.out.println ("Expect 9 and 6 : " + g.sizeVertices() + " "
                                              + g.sizeEdges());
      System.out.println ("Expect 9 vertices and 6 edges : \n" + g);
      g.removeVertex("a");
      System.out.println ("Expect 8 and 3 : " + g.sizeVertices() + " "
                                              + g.sizeEdges());

//================ Undirected begins here ==========================

      GBFrame.pause(); 
      System.out.println ("================Undirected Graph Tests======");                                      
                                              
      // Create a graph from an Iterator
      list.clear();
      list.add("a");
      list.add("b");
      list.add("c");
      list.add("d");
      
      g = new LinkedUndirectedGraph (list);
      System.out.println ("Expect 4 : " + g.sizeVertices()); 
      System.out.println ("Expect 4 vertices abcd and no edges: " + g); 
      
      // Mark vertices, clear last mark, count marks, clear marks, count marks
      iter = g.vertices();
      while (iter.hasNext()){
        vertex = (Vertex)(iter.next());
        vertex.setMark();
      }
      vertex.clearMark();
      iter = g.vertices();
      count = 0;
      while (iter.hasNext()){
        vertex = (Vertex)(iter.next());
        if (vertex.isMarked())
            count++;
      }
      g.clearVertexMarks();
      while (iter.hasNext()){
        vertex = (Vertex)(iter.next());
        if (vertex.isMarked())
            count++;
      }
      System.out.println ("Expect 3: " + count);
      
      // Insert some edges
      g.addEdge("a","b",new Weight(1));
      g.addEdge("c","d",new Weight(1));
      
      // Mark edges, clear last mark, count marks, clear marks, count marks
      iter = g.edges();
      while (iter.hasNext()){
        edge = (Edge)(iter.next());
        edge.setMark();
      }
      edge.clearMark();
      iter = g.edges();
      count = 0;
      while (iter.hasNext()){
        edge = (Edge)(iter.next());
        if (edge.isMarked())
            count++;
      }
      g.clearEdgeMarks();
      while (iter.hasNext()){
        edge = (Edge)(iter.next());
        if (edge.isMarked())
            count++;
      }
      System.out.println ("Expect 1: " + count);
      
      // Clear graph
      g.clear();
        
      // Insert vertices
      g.addVertex("a");
      g.addVertex("b");
      g.addVertex("c");
      g.addVertex("d");
      g.addVertex("e");
      g.addVertex("f");
      g.addVertex("g");
      g.addVertex("h");
      g.addVertex("i");
      g.addVertex("j");
      
      // Test some vertex methods
      iter = g.vertices();
      str = "";
      while (iter.hasNext())
        str += iter.next() + " " ;   
      System.out.println ("Expect 10        : " + g.sizeVertices());
      System.out.println ("Expect a thru j  : " + str);
      System.out.println ("Expect truefalse : " + g.containsVertex("a")
                                                + g.containsVertex("x"));
      System.out.println ("Expect ab true false : " + g.getVertex("a")
                                                    + g.getVertex("b")    + " "
                                                    + g.removeVertex("j") + " "  
                                                    + g.containsVertex("j"));                                        
      
      // Test some edge methods
      System.out.println ("Expect falsefalse: " + g.containsEdge ("a", "b")
                                                + g.containsEdge ("a", "a"));
      g.addEdge("a","b", new Weight(1));
      System.out.println ("Expect truetruefalse : " + g.containsEdge ("a", "b")
                                                    + g.containsEdge ("b", "a")
                                                    + g.containsEdge ("a", "a"));
      g.addEdge("a","c", new Weight(2));
      g.addEdge("e","c", new Weight(4));
      g.addEdge("e","d", new Weight(5));
      g.addEdge("e","f", new Weight(6));
      g.addEdge("d","a", new Weight(7));
      
      System.out.println("Expect true false true : " +  g.removeEdge("a","c") + " "
                                                     +  g.containsEdge ("a","c") + " "
                                                     +  g.containsEdge ("d","a"));

      // Test incidentEdges
      iter = g.incidentEdges(g.getVertex("a"));
      str = "";
      while (iter.hasNext())
        str += iter.next() + " " ;   
      System.out.println ("Expect edges ab1 ad7 : " + str);

      // Test neighboringVertices
      iter = g.neighboringVertices(g.getVertex("a"));
      str = "";
      while (iter.hasNext())
        str += iter.next() + " " ;   
      System.out.println ("Expect vertices bd : " + str);
      
      GBFrame.pause();

      // Test size methods and toString for graph
      System.out.println ("Expect 9 and 5 : " + g.sizeVertices() + " "
                                              + g.sizeEdges());
      System.out.println ("Expect 9 vertices and 5 edges : \n" + g);
      g.removeVertex("a");
      System.out.println ("Expect 8 and 3 : " + g.sizeVertices() + " "
                                              + g.sizeEdges());

  }
}

