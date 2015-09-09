import java.io.FileNotFoundException;

import ds.util.Set;
import ds.util.Iterator;
import ds.util.DiGraph;

public class Program24_1
{
	public static void main(String[] args)
		throws FileNotFoundException
	{
		// construct graph with vertices of type String by reading
		// from the file "graphIO.dat"
		DiGraph<String> g = DiGraph.readGraph("graphIO.dat");
		String vtxName;
		// sets for vertexSet() and adjacent vertices (neighbors)
		Set<String> vtxSet, neighborSet;

		// output number of vertices and edges
		System.out.println("Number of vertices: " +
								 g.numberOfVertices());
		System.out.println("Number of edges: " +
								 g.numberOfEdges());

		// properties relative to vertex A
		System.out.println("inDegree for A: " + g.inDegree("A"));
		System.out.println("outDegree for A: " + g.outDegree("A"));
		System.out.println("Weight e(A,B): " +
								 g.getWeight("A","B"));

		// delete edge with weight 2
		g.removeEdge("B", "A");

		// delete vertex "E" and edges (E,C), (C,E) and (D,E)
		g.removeVertex("E");

		// add and update attributes of the graph
		g.setWeight("A","B",8);		// increase weight from 4 to 8
		g.addVertex("F");				// add vertex F
		g.addEdge("F","D",3);		// add edge (F,D) with weight 3

		// after all updates, output the graph and its properties
		System.out.println("After all the graph updates");
		System.out.println(g);

		// get the vertices as a Set and create set iterator
		vtxSet = g.vertexSet();
		Iterator vtxIter = vtxSet.iterator();

		// scan the vertices and display the set of neighbors
		while(vtxIter.hasNext())
		{
			vtxName = (String)vtxIter.next();
			neighborSet = g.getNeighbors(vtxName);
			System.out.println("   Neighbor set for vertex " +
									 vtxName + " is " + neighborSet);
		}
	}
}

/*
Run:

Number of vertices: 5
Number of edges: 8
inDegree for A: 1
outDegree for A: 3
Weight e(A,B): 4
After all the graph updates
A:  in-degree 0  out-degree 3
    Edges: B(8)  C(7)  D(6)  
B:  in-degree 2  out-degree 0
    Edges: 
C:  in-degree 1  out-degree 1
    Edges: B(3)  
D:  in-degree 2  out-degree 0
    Edges: 
F:  in-degree 0  out-degree 1
    Edges: D(3)  

   Neighbor set for vertex D is []
   Neighbor set for vertex F is [D]
   Neighbor set for vertex A is [D, B, C]
   Neighbor set for vertex B is []
   Neighbor set for vertex C is [B]
*/
