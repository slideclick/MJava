import java.util.*;

// NEED TO ADD TESTS FOR PRECONDITIONS

public class TestGraphPT{

   public static void main (String[] args){
      String str;
      Iterator iter;
      int count;
      
      LinkedEdgePT edge = null;
      LinkedVertexPT vertex = null;
      LinkedGraphPT g;
      List list = new LinkedList();
                                              
      // Create a graph from an Iterator
      list.clear();
      list.add("a");
      list.add("b");
      list.add("c");
      list.add("d");
      
      g = new LinkedGraphPT (list.iterator());
      System.out.println ("Expect 4 : " + g.sizeVertices()); 
      System.out.println ("Expect 4 vertices abcd and no edges: " + g); 
      
      // Mark vertices, clear last mark, count marks, clear marks, count marks
      iter = g.vertices();
      while (iter.hasNext()){
        vertex = (LinkedVertexPT)(iter.next());
        vertex.setMark();
      }
      vertex.clearMark();
      iter = g.vertices();
      count = 0;
      while (iter.hasNext()){
        vertex = (LinkedVertexPT)(iter.next());
        if (vertex.isMarked())
            count++;
      }
      g.clearVertexMarks();
      while (iter.hasNext()){
        vertex = (LinkedVertexPT)(iter.next());
        if (vertex.isMarked())
            count++;
      }
      System.out.println ("Expect 3: " + count);
      
      // Insert some edges
      g.addEdge("a","b",1);
      g.addEdge("c","d",2);
      
      // Mark edges, clear last mark, count marks, clear marks, count marks
      iter = g.edges();
      while (iter.hasNext()){
        edge = (LinkedEdgePT)(iter.next());
        edge.setMark();
      }
      edge.clearMark();
      iter = g.edges();
      count = 0;
      while (iter.hasNext()){
        edge = (LinkedEdgePT)(iter.next());
        if (edge.isMarked())
            count++;
      }
      g.clearEdgeMarks();
      while (iter.hasNext()){
        edge = (LinkedEdgePT)(iter.next());
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
      
      vertex = g.getVertex ("a");
      vertex.setLabel ("aaa", g);
      System.out.println ("Expect vertices aaa thru j : " + g);
      vertex.setLabel ("a", g);

      // Test some edge methods
      System.out.println ("Expect falsefalse: " + g.containsEdge ("a", "b")
                                                + g.containsEdge ("a", "a"));
      g.addEdge("a","b", 1);
      System.out.println ("Expect truetruefalse : " + g.containsEdge ("a", "b")
                                                    + g.containsEdge ("b", "a")
                                                    + g.containsEdge ("a", "a"));
      g.addEdge("a","c", 2);
      g.addEdge("e","c", 4);
      g.addEdge("e","d", 5);
      g.addEdge("e","f", 6);
      g.addEdge("d","a", 7);
      
      System.out.println("Expect true false true : " +  g.removeEdge("a","c") + " "
                                                     +  g.containsEdge ("a","c") + " "
                                                     +  g.containsEdge ("d","a"));

      edge = g.getEdge ("e", "d");
      edge.setWeight (55);
      System.out.println ("Expect 55 : " + edge.getWeight());
      edge.setWeight (5);

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

      // Test size methods and toString for graph
      System.out.println ("Expect 9 and 5 : " + g.sizeVertices() + " "     
                                              + g.sizeEdges());
      System.out.println ("Expect 9 vertices and 5 edges : \n" + g);
      g.removeVertex("a");
      System.out.println ("Expect 8 and 3 : " + g.sizeVertices() + " "
                                              + g.sizeEdges());
      System.out.println ("Expect vertices b thru i and  3 edges \n" + g);

  }
}

