import java.io.FileNotFoundException;

import ds.util.DiGraph;
import ds.util.DiGraphs;

public class Program24_2
{
	public static void main(String[] args)
		throws FileNotFoundException
	{
		DiGraph<String> g = DiGraph.readGraph("cycle.dat");

		// determine if the graph is acyclic
		if (DiGraphs.acyclic(g))
			System.out.println("Graph is acyclic");
		else
			System.out.println("Graph is not acyclic");

		// add edge (E,B) to create a cycle
		System.out.print("   Adding edge (E,B): ");
		g.addEdge("E", "B", 1);

		// retest the graph to see if it is acyclic
		if (DiGraphs.acyclic(g))
			System.out.println("New graph is acyclic");
		else
			System.out.println("New graph is not acyclic");
	}
}

/*
Run:

Original graph is acyclic
   Adding edge (E,B): New graph is not acyclic

*/
